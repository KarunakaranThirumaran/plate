package com.volvo.plates.dto;

public class PlatesOrderReport {
	
    private int breakFastCount;
    private int lunchCount;
    private int midDayMealCount;
    private int otSnacksCount;
    private int dinnerCount;
    private int totalDaysCount;   
    

	public int getBreakFastCount() {
        return breakFastCount;
    }

    public void setBreakFastCount(int breakFastCount) {
        this.breakFastCount = breakFastCount;
    }

    public int getLunchCount() {
        return lunchCount;
    }

    public void setLunchCount(int lunchCount) {
        this.lunchCount = lunchCount;
    }

    public int getDinnerCount() {
        return dinnerCount;
    }

    public void setDinnerCount(int dinnerCount) {
        this.dinnerCount = dinnerCount;
    }

	public int getTotalDaysCount() {
		return totalDaysCount;
	}

	public void setTotalDaysCount(int totalDaysCount) {
		this.totalDaysCount = totalDaysCount;
	}

	public int getMidDayMealCount() {
		return midDayMealCount;
	}

	public void setMidDayMealCount(int midDayMealCount) {
		this.midDayMealCount = midDayMealCount;
	}

	public int getOtSnacksCount() {
		return otSnacksCount;
	}

	public void setOtSnacksCount(int otSnacksCount) {
		this.otSnacksCount = otSnacksCount;
	}
}
