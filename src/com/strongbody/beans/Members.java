package com.strongbody.beans;

import java.sql.Date;

public class Members {

	private int id;
	private String fullName;
	private Date dateOfBirth;
	private String sex;
	private String contactNumber;
	private String email;
	private String diet;
	private int membershipID;
	private int measurementID;

	public Members() {
		
	}
	public Members(int id, String fullName, Date dateOfBirth, String sex, String contactNumber, String email,
			String diet,int membershipID,int measurementID) {
		this.id = id;
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.sex = sex;
		this.contactNumber = contactNumber;
		this.email = email;
		this.diet = diet;
		this.membershipID = membershipID;
		this.measurementID = measurementID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDiet() {
		return diet;
	}

	public void setDiet(String diet) {
		this.diet = diet;
	}
	
	public int getMembershipID() {
		return membershipID;
	}
	public void setMembershipID(int membershipID) {
		this.membershipID = membershipID;
	}
	
	
	public int getMeasurementID() {
		return this.measurementID;
	}
	
	public void setMeasurementID(int measurementID) {
		this.measurementID = measurementID;
	}
	

}
