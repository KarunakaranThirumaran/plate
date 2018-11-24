package com.volvo.plates.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.volvo.plates.domain.PlatesOrder;
import com.volvo.plates.dto.PlatesOrderDTO;

@Component
public class PlatesOrderToPlatesOrderDTO implements Converter<PlatesOrder, PlatesOrderDTO> {
    @Override
    public PlatesOrderDTO convert(PlatesOrder platesOrder) {
    	PlatesOrderDTO platesOrderDto = new PlatesOrderDTO();
    	platesOrderDto.setEmployeeId(platesOrder.getEmployeeId());
    	platesOrderDto.setEmployeeName(platesOrder.getEmployeeName());
    	platesOrderDto.setLastUsedDateAndTime(platesOrder.getLastUsedDateAndTime());
    	platesOrderDto.setOrdersBreakFast(platesOrder.getOrdersBreakFast());
    	platesOrderDto.setOrdersDinner(platesOrder.getOrdersDinner());
    	platesOrderDto.setOrdersLunch(platesOrder.getOrdersLunch());
    	platesOrderDto.setOrdersMidDayMeal(platesOrder.getOrdersMidDayMeal());
    	platesOrderDto.setOrdersOtSnacks(platesOrder.getOrdersOtSnacks());
        return platesOrderDto;
    }
}
