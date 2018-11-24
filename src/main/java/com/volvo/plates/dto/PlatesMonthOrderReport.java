package com.volvo.plates.dto;

import java.util.HashMap;
import java.util.Map;

public class PlatesMonthOrderReport {
	
    private int totalMonthCount;
    private Map<String, Integer> monthDetails = new HashMap<>();
    
	public int getTotalMonthCount() {
		return totalMonthCount;
	}
	public void setTotalMonthCount(int totalMonthCount) {
		this.totalMonthCount = totalMonthCount;
	}
	public Map<String, Integer> getMonthDetails() {
		return monthDetails;
	}
	public void setMonthDetails(Map<String, Integer> monthDetails) {
		this.monthDetails = monthDetails;
	}

}
