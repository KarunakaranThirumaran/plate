package com.volvo.plates.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.volvo.plates.domain.PlatesOrder;
import com.volvo.plates.dto.PlatesOrderDTO;

@Component
public class PlatesOrderDTOToPlatesOrder implements Converter<PlatesOrderDTO, PlatesOrder> {

    @Override
    public PlatesOrder convert(PlatesOrderDTO platesOrderDto) {
        PlatesOrder platesOrder = new PlatesOrder();
        platesOrder.setEmployeeId(platesOrderDto.getEmployeeId());
        platesOrder.setEmployeeName(platesOrderDto.getEmployeeName());
        platesOrder.setLastUsedDateAndTime(platesOrderDto.getLastUsedDateAndTime());
        platesOrder.setOrdersBreakFast(platesOrderDto.getOrdersBreakFast());
        platesOrder.setOrdersDinner(platesOrderDto.getOrdersDinner());
        platesOrder.setOrdersLunch(platesOrderDto.getOrdersLunch());
        platesOrder.setOrdersMidDayMeal(platesOrderDto.getOrdersMidDayMeal());
        platesOrder.setOrdersOtSnacks(platesOrderDto.getOrdersOtSnacks());
        return platesOrder;
    }
}
