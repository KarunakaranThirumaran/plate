package com.volvo.plates.dto;

import java.util.HashMap;
import java.util.Map;

public class PlatesDayOrderReport {
	
	private int totalDayCount;
	private Map<String, Integer> dayDetails = new HashMap<>();
	
	public int getTotalDayCount() {
		return totalDayCount;
	}
	public void setTotalDayCount(int totalDayCount) {
		this.totalDayCount = totalDayCount;
	}
	public Map<String, Integer> getDayDetails() {
		return dayDetails;
	}
	public void setDayDetails(Map<String, Integer> dayDetails) {
		this.dayDetails = dayDetails;
	}
}
