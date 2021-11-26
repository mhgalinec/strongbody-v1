package com.strongbody.beans;

import java.sql.Date;

public class Membership {
	private int id;
	private Date registrationDate;
	private Date validFrom;
	private Date validThrough;
	private String serviceLevel;
	private String membershipType;
	private String paymentStatus;
	private String memberName;
	private int memberID;
	
	public Membership() {
		
	}

	public Membership(int id, Date registrationDate, Date validFrom, Date validThrough, String serviceLevel, String membershipType) {
		super();
		this.id = id;
		this.registrationDate = registrationDate;
		this.validFrom = validFrom;
		this.validThrough = validThrough;
		this.serviceLevel = serviceLevel;
		this.membershipType = membershipType;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Date getRegistrationDate() {
		return registrationDate;
	}


	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}


	public Date getValidFrom() {
		return validFrom;
	}


	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}


	public Date getValidThrough() {
		return validThrough;
	}


	public void setValidThrough(Date validThrough) {
		this.validThrough = validThrough;
	}


	public String getServiceLevel() {
		return serviceLevel;
	}


	public void setServiceLevel(String serviceLevel) {
		this.serviceLevel = serviceLevel;
	}

	public String getMembershipType() {
		return membershipType;
	}

	public void setMembershipType(String membershipType) {
		this.membershipType = membershipType;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public int getMemberID() {
		return memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}
	
	
	

}
