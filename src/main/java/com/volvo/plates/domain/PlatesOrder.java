package com.volvo.plates.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PlatesOrder {

	@Id
	private String employeeId;
	private String employeeName;
	private Integer ordersBreakFast;
	private Integer ordersLunch;
	private Integer ordersDinner;
	private Integer ordersMidDayMeal;
	private Integer ordersOtSnacks;	
	private LocalDateTime lastUsedDateAndTime;


	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public LocalDateTime getLastUsedDateAndTime() {
		return lastUsedDateAndTime;
	}

	public void setLastUsedDateAndTime(LocalDateTime lastUsedDateAndTime) {
		this.lastUsedDateAndTime = lastUsedDateAndTime;
	}

	public Integer getOrdersBreakFast() {
		return ordersBreakFast;
	}

	public void setOrdersBreakFast(Integer ordersBreakFast) {
		this.ordersBreakFast = ordersBreakFast;
	}

	public Integer getOrdersLunch() {
		return ordersLunch;
	}

	public void setOrdersLunch(Integer ordersLunch) {
		this.ordersLunch = ordersLunch;
	}

	public Integer getOrdersDinner() {
		return ordersDinner;
	}

	public void setOrdersDinner(Integer ordersDinner) {
		this.ordersDinner = ordersDinner;
	}

	public Integer getOrdersMidDayMeal() {
		return ordersMidDayMeal;
	}

	public void setOrdersMidDayMeal(Integer ordersMidDayMeal) {
		this.ordersMidDayMeal = ordersMidDayMeal;
	}

	public Integer getOrdersOtSnacks() {
		return ordersOtSnacks;
	}

	public void setOrdersOtSnacks(Integer ordersOtSnacks) {
		this.ordersOtSnacks = ordersOtSnacks;
	}

}
