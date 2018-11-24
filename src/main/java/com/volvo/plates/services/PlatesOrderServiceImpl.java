package com.volvo.plates.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volvo.plates.converters.PlatesOrderDTOToPlatesOrder;
import com.volvo.plates.converters.PlatesOrderToPlatesOrderDTO;
import com.volvo.plates.domain.PlatesOrder;
import com.volvo.plates.dto.PlatesDayOrderReport;
import com.volvo.plates.dto.PlatesMonthOrderReport;
import com.volvo.plates.dto.PlatesOrderDTO;
import com.volvo.plates.dto.PlatesOrderReport;
import com.volvo.plates.dto.PlatesWeekOrderReport;
import com.volvo.plates.repositories.PlatesOrderRepository;
import com.volvo.plates.utils.PlatesOrderTimeTable;

@Service
public class PlatesOrderServiceImpl implements PlatesOrderService {

	private static final Logger logger = LoggerFactory.getLogger(PlatesOrderServiceImpl.class);

	@Autowired
	private PlatesOrderRepository platesOrderRepository;
	
	@Autowired
	PlatesOrderToPlatesOrderDTO platesOrderToPlatesOrderDTO;
	
	@Autowired
	PlatesOrderDTOToPlatesOrder platesOrderDTOToPlatesOrder;

	@Override
	public List<PlatesOrderDTO> listAll() {
		List<PlatesOrder> platesOrders = new ArrayList<>();
		platesOrderRepository.findAll().forEach(platesOrders::add);
		List<PlatesOrderDTO> platesOrdersDTO = new ArrayList<>();
		for( PlatesOrder platesOrder : platesOrders) {
			platesOrdersDTO.add(platesOrderToPlatesOrderDTO.convert(platesOrder));
		}
		return platesOrdersDTO; 
	}

	@Override
	public PlatesOrderDTO getByEmployeeId(String empId) {
		return platesOrderRepository.findByEmployeeId(empId);
	}

	@Override
	public void saveOrUpdate(PlatesOrder platesNewOrder) {
		
		PlatesOrderDTO platesExistingorder = platesOrderRepository.findByEmployeeId(platesNewOrder.getEmployeeId());
		PlatesOrderDTO platesFinalOrder = platesExistingorder != null ? platesExistingorder : platesOrderToPlatesOrderDTO.convert(platesNewOrder);
		platesFinalOrder.setLastUsedDateAndTime(LocalDateTime.now());
		platesFinalOrder = getMealTime(platesFinalOrder.getLastUsedDateAndTime().getHour(), platesFinalOrder);
		platesOrderRepository.save(platesFinalOrder);
	}

	@Override
	public void delete(String employeeId) {
		platesOrderRepository.deleteByEmployeeId(employeeId);
	}

	@Override
	public PlatesDayOrderReport getPlatesDayTotalCountReport() {
		PlatesDayOrderReport platesOrderReport = new PlatesDayOrderReport();
		LocalDate today = LocalDate.now();

		int[] platesStartTimeStampArr = getPlatesStartTimeStamp();
		int[] platesEndTimeStampArr = getPlatesEndTimeStamp();

		LocalDateTime fromTime = today.atTime(platesStartTimeStampArr[0], platesStartTimeStampArr[1]);
		LocalDateTime toTime = today.atTime(platesEndTimeStampArr[0], platesEndTimeStampArr[1]);
		
		int totalDayCount = platesOrderRepository.getCountByLastUsedDateTimeBetween(fromTime, toTime);
		
		if(totalDayCount > 0) {
			
			Map<String, Integer> dayDetails = new HashMap<>();
			int breakFastCount = platesOrderRepository.getBreakFastSumByLastUsedDateTimeBetween(fromTime, toTime);
			int lunchCount = platesOrderRepository.getLunchSumByLastUsedDateTimeBetween(fromTime, toTime);
			int midDayMealCount = platesOrderRepository.getMidDayMealSumByLastUsedDateTimeBetween(fromTime, toTime);
			int otSnacksCount = platesOrderRepository.getOtSnacksSumByLastUsedDateTimeBetween(fromTime, toTime);
			int dinnerCount = platesOrderRepository.getDinnerSumByLastUsedDateTimeBetween(fromTime, toTime);
			
			dayDetails.put("BreakFast", breakFastCount);
			dayDetails.put("Lunch", lunchCount);
			dayDetails.put("MidDayMeal", midDayMealCount);
			dayDetails.put("OtSnacks", otSnacksCount);
			dayDetails.put("Dinner", dinnerCount);
			
			platesOrderReport.setDayDetails(dayDetails);
			platesOrderReport.setTotalDayCount(totalDayCount);			
		}
		return platesOrderReport;
	}

	@Override
	public PlatesWeekOrderReport getPlatesWeekTotalCountReport() {
		PlatesWeekOrderReport platesOrderReport = new PlatesWeekOrderReport();
		LocalDate weekStartDay = LocalDate.now().with(TemporalAdjusters.previous(DayOfWeek.SUNDAY));
		LocalDate weekEndDay = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));

		int[] platesStartTimeStampArr = getPlatesStartTimeStamp();
		int[] platesEndTimeStampArr = getPlatesEndTimeStamp();

		LocalDateTime fromTime = weekStartDay.atTime(platesStartTimeStampArr[0], platesStartTimeStampArr[1]);
		LocalDateTime toTime = weekEndDay.atTime(platesEndTimeStampArr[0], platesEndTimeStampArr[1]);
		
		int totalWeekCount = platesOrderRepository.getCountByLastUsedDateTimeBetween(fromTime, toTime);
		
		if(totalWeekCount > 0) {
			
			Map<String, Integer> weekDetails = new HashMap<>();
			int breakFastCount = platesOrderRepository.getBreakFastSumByLastUsedDateTimeBetween(fromTime, toTime);
			int lunchCount = platesOrderRepository.getLunchSumByLastUsedDateTimeBetween(fromTime, toTime);
			int midDayMealCount = platesOrderRepository.getMidDayMealSumByLastUsedDateTimeBetween(fromTime, toTime);
			int otSnacksCount = platesOrderRepository.getOtSnacksSumByLastUsedDateTimeBetween(fromTime, toTime);
			int dinnerCount = platesOrderRepository.getDinnerSumByLastUsedDateTimeBetween(fromTime, toTime);
			
			weekDetails.put("BreakFast", breakFastCount);
			weekDetails.put("Lunch", lunchCount);
			weekDetails.put("MidDayMeal", midDayMealCount);
			weekDetails.put("OtSnacks", otSnacksCount);
			weekDetails.put("Dinner", dinnerCount);
			
			platesOrderReport.setWeekDetails(weekDetails);
			platesOrderReport.setTotalWeekCount(totalWeekCount);			
		}
		
		return platesOrderReport;
	}

	@Override
	public PlatesMonthOrderReport getPlatesMonthTotalCountReport() {
		PlatesMonthOrderReport platesOrderReport = new PlatesMonthOrderReport();
		LocalDate monthStartDay = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
		LocalDate monthEndDay = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());

		int[] platesStartTimeStampArr = getPlatesStartTimeStamp();
		int[] platesEndTimeStampArr = getPlatesEndTimeStamp();

		LocalDateTime fromTime = monthStartDay.atTime(platesStartTimeStampArr[0], platesStartTimeStampArr[1]);
		LocalDateTime toTime = monthEndDay.atTime(platesEndTimeStampArr[0], platesEndTimeStampArr[1]);
		
		int totalMonthCount = platesOrderRepository.getCountByLastUsedDateTimeBetween(fromTime, toTime);
		
		if(totalMonthCount > 0) {
			
			Map<String, Integer> monthDetails = new HashMap<>();
			int breakFastCount = platesOrderRepository.getBreakFastSumByLastUsedDateTimeBetween(fromTime, toTime);
			int lunchCount = platesOrderRepository.getLunchSumByLastUsedDateTimeBetween(fromTime, toTime);
			int midDayMealCount = platesOrderRepository.getMidDayMealSumByLastUsedDateTimeBetween(fromTime, toTime);
			int otSnacksCount = platesOrderRepository.getOtSnacksSumByLastUsedDateTimeBetween(fromTime, toTime);
			int dinnerCount = platesOrderRepository.getDinnerSumByLastUsedDateTimeBetween(fromTime, toTime);
			
			monthDetails.put("BreakFast", breakFastCount);
			monthDetails.put("Lunch", lunchCount);
			monthDetails.put("MidDayMeal", midDayMealCount);
			monthDetails.put("OtSnacks", otSnacksCount);
			monthDetails.put("Dinner", dinnerCount);
			
			platesOrderReport.setMonthDetails(monthDetails);
			platesOrderReport.setTotalMonthCount(totalMonthCount);
		}
		
		return platesOrderReport;
	}

	@Override
	public PlatesOrderReport getPlatesDateRangeReport(String fromDate, String toDate) {

		LocalDate fromDateObj = LocalDate.parse(fromDate);
		LocalDate toDateObj = LocalDate.parse(toDate);

		PlatesOrderReport platesOrderReport = new PlatesOrderReport();

		int[] platesStartTimeStampArr = getPlatesStartTimeStamp();
		int[] platesEndTimeStampArr = getPlatesEndTimeStamp();

		LocalDateTime fromTime = fromDateObj.atTime(platesStartTimeStampArr[0], platesStartTimeStampArr[1]);
		LocalDateTime toTime = toDateObj.atTime(platesEndTimeStampArr[0], platesEndTimeStampArr[1]);

		platesOrderReport.setBreakFastCount(platesOrderRepository.getBreakFastSumByLastUsedDateTimeBetween(fromTime, toTime));
		platesOrderReport.setLunchCount(platesOrderRepository.getLunchSumByLastUsedDateTimeBetween(fromTime, toTime));
		platesOrderReport.setMidDayMealCount(platesOrderRepository.getMidDayMealSumByLastUsedDateTimeBetween(fromTime, toTime));
		platesOrderReport.setOtSnacksCount(platesOrderRepository.getOtSnacksSumByLastUsedDateTimeBetween(fromTime, toTime));
		platesOrderReport.setDinnerCount(platesOrderRepository.getDinnerSumByLastUsedDateTimeBetween(fromTime, toTime));
		platesOrderReport.setTotalDaysCount(platesOrderRepository.getCountByLastUsedDateTimeBetween(fromTime, toTime));
		return platesOrderReport;
	}

	private int[] getPlatesStartTimeStamp() {
		int[] startArr = new int[2];
		startArr[0] = (int) PlatesOrderTimeTable.BREAKFAST.getTimefrom();
		startArr[1] = (int) Math.round((PlatesOrderTimeTable.BREAKFAST.getTimefrom() - startArr[0]) * 100);
		return startArr;
	}

	private int[] getPlatesEndTimeStamp() {
		int[] endArr = new int[2];
		endArr[0] = (int) PlatesOrderTimeTable.DINNER.getTimeto();
		endArr[1] = (int) Math.round((PlatesOrderTimeTable.DINNER.getTimeto() - endArr[0]) * 100);
		return endArr;
	}

	private PlatesOrderDTO getMealTime(int currentHour, PlatesOrderDTO order) {

		if (currentHour >= PlatesOrderTimeTable.BREAKFAST.getTimefrom()
				&& currentHour <= PlatesOrderTimeTable.BREAKFAST.getTimeto()) {
			order.setOrdersBreakFast(order.getOrdersBreakFast() != null ? order.getOrdersBreakFast() + 1 : 1);
			order.setOrdersLunch(0);
			order.setOrdersMidDayMeal(0);
			order.setOrdersOtSnacks(0);
			order.setOrdersDinner(0);
			return order;
		} else if (currentHour >= PlatesOrderTimeTable.LUNCH.getTimefrom()
				&& currentHour <= PlatesOrderTimeTable.LUNCH.getTimeto()) {
			order.setOrdersBreakFast(0);
			order.setOrdersLunch(order.getOrdersLunch() != null ? order.getOrdersLunch() + 1 : 1);
			order.setOrdersMidDayMeal(0);
			order.setOrdersOtSnacks(0);
			order.setOrdersDinner(0);
			return order;
		} else if (currentHour >= PlatesOrderTimeTable.MIDDAYMEAL.getTimefrom()
				&& currentHour <= PlatesOrderTimeTable.MIDDAYMEAL.getTimeto()) {
			order.setOrdersBreakFast(0);
			order.setOrdersLunch(0);
			order.setOrdersMidDayMeal(order.getOrdersMidDayMeal() != null ? order.getOrdersMidDayMeal() + 1 : 1);
			order.setOrdersOtSnacks(0);
			order.setOrdersDinner(0);
			return order;
		} else if (currentHour >= PlatesOrderTimeTable.OTSNACKS.getTimefrom()
				&& currentHour <= PlatesOrderTimeTable.OTSNACKS.getTimeto()) {
			order.setOrdersBreakFast(0);
			order.setOrdersLunch(0);
			order.setOrdersMidDayMeal(0);
			order.setOrdersOtSnacks(order.getOrdersOtSnacks() != null ? order.getOrdersOtSnacks() + 1 : 1);
			order.setOrdersDinner(0);
			return order;
		} else if (currentHour >= PlatesOrderTimeTable.DINNER.getTimefrom()
				&& currentHour <= PlatesOrderTimeTable.DINNER.getTimeto()) {
			order.setOrdersBreakFast(0);
			order.setOrdersLunch(0);
			order.setOrdersMidDayMeal(0);
			order.setOrdersOtSnacks(0);
			order.setOrdersDinner(order.getOrdersDinner() != null ? order.getOrdersDinner() + 1 : 1);
			return order;
		}

		return null;
	}
}
