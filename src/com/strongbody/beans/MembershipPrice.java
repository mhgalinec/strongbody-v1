package com.strongbody.beans;

public class MembershipPrice {
	private int daily = 15;
	private double daysInMonth = 30.43D;

	public MembershipPrice() {
		
	}
	
	public int dailyFee() {
		return this.daily;
	}
	
	//89
	public int weeklyFee() {
		int weekly = (int) ((dailyFee() * 7) * 0.85);
		return weekly;
	}
	//319
	public int monthlyFee() {
		int monthly = (int) ((dailyFee() * daysInMonth) * 0.70);
		return monthly;
	}
	//861
	public int trimesterFee() {
		int trimester = (int) ((monthlyFee() *3) * 0.90);
		return trimester;
	}
	//1549
	public int biannualFee() {
		int halfYearly = (int) ((trimesterFee() * 2) * 0.90);
		return halfYearly;
	}
	//2478
	public int annualFee() {
		int yearly = (int) ((biannualFee() * 2) * 0.80);
		return yearly;
	}
}
