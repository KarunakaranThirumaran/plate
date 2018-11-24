package com.volvo.plates.services;

import java.util.List;

import com.volvo.plates.domain.PlatesOrder;
import com.volvo.plates.dto.PlatesDayOrderReport;
import com.volvo.plates.dto.PlatesMonthOrderReport;
import com.volvo.plates.dto.PlatesOrderDTO;
import com.volvo.plates.dto.PlatesOrderReport;
import com.volvo.plates.dto.PlatesWeekOrderReport;

public interface PlatesOrderService {

    List<PlatesOrderDTO> listAll();
    PlatesDayOrderReport getPlatesDayTotalCountReport();
    PlatesWeekOrderReport getPlatesWeekTotalCountReport();
    PlatesMonthOrderReport getPlatesMonthTotalCountReport();
	void saveOrUpdate(PlatesOrder platesOrder);
	PlatesOrderDTO getByEmployeeId(String empId);
	void delete(String employeeId);
	PlatesOrderReport getPlatesDateRangeReport(String fromDate, String toDate);
}
