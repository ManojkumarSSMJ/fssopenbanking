package com.fss.openbanking.bean;

public class Frequency {
	
	private String frequencyType;
	private String intervalWeek;
	private String executionDay;
	
	public String getExecutionDay() {
		return executionDay;
	}
	public void setExecutionDay(String executionDay) {
		this.executionDay = executionDay;
	}
	public String getIntervalWeek() {
		return intervalWeek;
	}
	public void setIntervalWeek(String intervalWeek) {
		this.intervalWeek = intervalWeek;
	}
	public String getFrequencyType() {
		return frequencyType;
	}
	public void setFrequencyType(String frequencyType) {
		this.frequencyType = frequencyType;
	}

}
