package com.volvo.plates.utils;

import java.util.HashMap;
import java.util.Map;

public enum PlatesOrderTimeTable {

	BREAKFAST("breakfast", 5.30, 8), 
	LUNCH("lunch", 11.30, 13.30), 
	MIDDAYMEAL("middaymeal",14.30, 15), 
	OTSNACKS("otsnacks",15.30, 18.30), 
	DINNER("dinner", 19, 21);

	private static Map<String, PlatesOrderTimeTable> platesOrderTime = new HashMap<>();

	private double timefrom;
	private double timeto;
	private String mealtime;

	PlatesOrderTimeTable(String mealtime, double timefrom, double timeto) {
		this.mealtime = mealtime;
		this.timefrom = timefrom;
		this.timeto = timeto;
	}

	public String getMealtime() {
		return mealtime;
	}

	public double getTimefrom() {
		return timefrom;
	}

	public double getTimeto() {
		return timeto;
	}

	static {
		for (PlatesOrderTimeTable platesOrderTimeValues : PlatesOrderTimeTable.values()) {
			platesOrderTime.put(platesOrderTimeValues.getMealtime(), platesOrderTimeValues);
		}
	}
}
