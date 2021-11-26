package com.strongbody.beans;

import java.sql.Date;

public class Measurements {
	private int id;
	private Date measurementDate;
	private String height;
	private String weight;
	private String bodyFat;
	private String shoulders;
	private String torso;
	private String waist;
	private String upperArm;
	private String lowerArm;
	private String upperLeg;
	private String lowerLeg;
	private String restingHeartRate;
	private int memberID;
	
	public Measurements() {
		
	}
	public Measurements(int id, Date measurementDate, String height, String weight, String bodyFat, String shoulders,
			String torso, String waist, String upperArm, String lowerArm, String upperLeg, String lowerLeg,
			String restingHeartRate) {
		this.id = id;
		this.measurementDate = measurementDate;
		this.height = height;
		this.weight = weight;
		this.bodyFat = bodyFat;
		this.shoulders = shoulders;
		this.torso = torso;
		this.waist = waist;
		this.upperArm = upperArm;
		this.lowerArm = lowerArm;
		this.upperLeg = upperLeg;
		this.lowerLeg = lowerLeg;
		this.restingHeartRate = restingHeartRate;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Date getMeasurementDate() {
		return measurementDate;
	}


	public void setMeasurementDate(Date measurementDate) {
		this.measurementDate = measurementDate;
	}


	public String getHeight() {
		return height;
	}


	public void setHeight(String height) {
		this.height = height;
	}


	public String getWeight() {
		return weight;
	}


	public void setWeight(String weight) {
		this.weight = weight;
	}


	public String getBodyFat() {
		return bodyFat;
	}


	public void setBodyFat(String bodyFat) {
		this.bodyFat = bodyFat;
	}


	public String getShoulders() {
		return shoulders;
	}


	public void setShoulders(String shoulders) {
		this.shoulders = shoulders;
	}


	public String getTorso() {
		return torso;
	}


	public void setTorso(String torso) {
		this.torso = torso;
	}


	public String getWaist() {
		return waist;
	}


	public void setWaist(String waist) {
		this.waist = waist;
	}


	public String getUpperArm() {
		return upperArm;
	}


	public void setUpperArm(String upperArm) {
		this.upperArm = upperArm;
	}


	public String getLowerArm() {
		return lowerArm;
	}


	public void setLowerArm(String lowerArm) {
		this.lowerArm = lowerArm;
	}


	public String getUpperLeg() {
		return upperLeg;
	}


	public void setUpperLeg(String upperLeg) {
		this.upperLeg = upperLeg;
	}


	public String getLowerLeg() {
		return lowerLeg;
	}


	public void setLowerLeg(String lowerLeg) {
		this.lowerLeg = lowerLeg;
	}

	public String getRestingHeartRate() {
		return restingHeartRate;
	}
	
	public void setRestingHeartRate(String restingHeartRate) {
		this.restingHeartRate = restingHeartRate;
	}
	
	public int getMemberID() {
		return memberID;
	}
	
	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	
	
	
	

}
