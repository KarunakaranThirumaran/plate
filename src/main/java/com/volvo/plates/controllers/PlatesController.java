package com.volvo.plates.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.volvo.plates.domain.PlatesOrder;
import com.volvo.plates.dto.PlatesDayOrderReport;
import com.volvo.plates.dto.PlatesMonthOrderReport;
import com.volvo.plates.dto.PlatesOrderDTO;
import com.volvo.plates.dto.PlatesOrderReport;
import com.volvo.plates.dto.PlatesWeekOrderReport;
import com.volvo.plates.services.PlatesOrderService;

@CrossOrigin
@RestController
@RequestMapping("/platesOrder")
public class PlatesController {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private PlatesOrderService platesOrderService;

    @RequestMapping(value = "/saveUserData", method = RequestMethod.POST)
    public void saveOrUpdatePlatesOrder(@RequestBody PlatesOrder platesOrder){
    	platesOrder.setLastUsedDateAndTime(LocalDateTime.now());
        platesOrderService.saveOrUpdate(platesOrder);
    }
    
    @RequestMapping(value = "/deleteUserData", method = RequestMethod.POST)
    public void deletePlatesOrder(@RequestBody PlatesOrder platesOrder){
        platesOrderService.delete(platesOrder.getEmployeeId());
    }
    
    @RequestMapping(value = "/readUserData", method = RequestMethod.GET)
    public List<PlatesOrderDTO> readUserData(){
		return platesOrderService.listAll();
    } 
    
    @RequestMapping(value = "/showDayReport", method = RequestMethod.GET)
    public PlatesDayOrderReport showDayTotalCountReport(){
        return platesOrderService.getPlatesDayTotalCountReport();
    }
    
    @RequestMapping(value = "/showWeekReport", method = RequestMethod.GET)
    public PlatesWeekOrderReport showWeekTotalCountReport(){
        return platesOrderService.getPlatesWeekTotalCountReport();
    }
    
    @RequestMapping(value = "/showMonthReport", method = RequestMethod.GET)
    public PlatesMonthOrderReport showMonthTotalCountReport(){
        return platesOrderService.getPlatesMonthTotalCountReport();
    }
    
    @RequestMapping(value = "/dateRangeReport", method = RequestMethod.GET)
    public PlatesOrderReport dateRangeReport(@RequestParam String fromDate, @RequestParam String toDate){
        return platesOrderService.getPlatesDateRangeReport(fromDate,toDate);
    }
}
