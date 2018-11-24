package com.volvo.plates.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.volvo.plates.domain.PlatesOrder;
import com.volvo.plates.dto.PlatesDayOrderReport;
import com.volvo.plates.dto.PlatesMonthOrderReport;
import com.volvo.plates.dto.PlatesOrderDTO;
import com.volvo.plates.dto.PlatesOrderReport;
import com.volvo.plates.dto.PlatesWeekOrderReport;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlatesOrderServiceTest {
	
	@Autowired
	PlatesOrderService platesOrderService;
	
	@Test
	public void test_fetchAllUsers() {
		List<PlatesOrderDTO> allUsers = platesOrderService.listAll();
		assertTrue(allUsers.size()>0);
	}
	
	@Test
	public void test_saveUser() {
		PlatesOrder platesOrder = new PlatesOrder();
		platesOrder.setEmployeeId("A123456");
		platesOrder.setEmployeeName("Emp"+platesOrder.getEmployeeId());		
		platesOrderService.saveOrUpdate(platesOrder);
	}
	
	@Test
	public void test_deleteUser() {
		PlatesOrder platesOrder = new PlatesOrder();
		platesOrder.setEmployeeId("A123456");
		platesOrderService.delete(platesOrder.getEmployeeId());
	}
	
	@Test
	public void test_showDateRangeReport() {
		PlatesOrderReport platesOrderReport = new PlatesOrderReport();
		platesOrderReport = platesOrderService.getPlatesDateRangeReport("2018-10-30", "2018-11-02");
		assertNotNull(platesOrderReport.getTotalDaysCount());		
	}
	
	@Test
	public void test_showDayTotalCountsReport() {
		PlatesDayOrderReport platesOrderReport = new PlatesDayOrderReport();
		platesOrderReport = platesOrderService.getPlatesDayTotalCountReport();
		assertNotNull(platesOrderReport.getTotalDayCount());
	}
	
	@Test
	public void test_showWeekTotalCountsReport() {
		PlatesWeekOrderReport platesOrderReport = new PlatesWeekOrderReport();
		platesOrderReport = platesOrderService.getPlatesWeekTotalCountReport();
		assertNotNull(platesOrderReport.getTotalWeekCount());
	}
	
	@Test
	public void test_showMonthTotalCountsReport() {
		PlatesMonthOrderReport platesOrderReport = new PlatesMonthOrderReport();
		platesOrderReport = platesOrderService.getPlatesMonthTotalCountReport();
		assertNotNull(platesOrderReport.getTotalMonthCount());
	}
}
