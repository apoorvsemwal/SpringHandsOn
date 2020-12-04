package com.springdemo.models;

public class CovidStatsByLocation {

	private String state;
	private String country;
	private int latestCaseCount;
	private int diffFromPrevDay;
	
	public int getDiffFromPrevDay() {
		return diffFromPrevDay;
	}
	public void setDiffFromPrevDay(int diffFromPrevDay) {
		this.diffFromPrevDay = diffFromPrevDay;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLatestCaseCount() {
		return latestCaseCount;
	}
	public void setLatestCaseCount(int latestCaseCount) {
		this.latestCaseCount = latestCaseCount;
	}
	
}
