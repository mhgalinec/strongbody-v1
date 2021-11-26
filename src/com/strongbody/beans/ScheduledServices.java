package com.strongbody.beans;

import java.sql.Date;

public class ScheduledServices {
	
	private int id;
	private String serviceType;
	private Date serviceDate;
	private String warranty;
	private float price;
	private String serviceCompany;
	private int equipmentID;
	
	public ScheduledServices() {
		
	}

	public ScheduledServices(int id, String serviceType, Date serviceDate, String warranty, float price,
			String serviceCompany) {
		super();
		this.id = id;
		this.serviceType = serviceType;
		this.serviceDate = serviceDate;
		this.warranty = warranty;
		this.price = price;
		this.serviceCompany = serviceCompany;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public Date getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}

	public String getWarranty() {
		return warranty;
	}

	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getServiceCompany() {
		return serviceCompany;
	}

	public void setServiceCompany(String serviceCompany) {
		this.serviceCompany = serviceCompany;
	}

	public int getEquipmentID() {
		return equipmentID;
	}

	public void setEquipmentID(int equipmentID) {
		this.equipmentID = equipmentID;
	}
	
	

}
