package com.volvo.plates.dto;

import java.util.HashMap;
import java.util.Map;

public class PlatesWeekOrderReport {
	
	private int totalWeekCount;
	private Map<String, Integer> weekDetails = new HashMap<>();
	
	public int getTotalWeekCount() {
		return totalWeekCount;
	}
	public void setTotalWeekCount(int totalWeekCount) {
		this.totalWeekCount = totalWeekCount;
	}
	public Map<String, Integer> getWeekDetails() {
		return weekDetails;
	}
	public void setWeekDetails(Map<String, Integer> weekDetails) {
		this.weekDetails = weekDetails;
	}
}
