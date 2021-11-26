package com.strongbody.beans;

import java.sql.Date;

public class UnplannedServices {
	
	private int id;
	private String faultType;
	private Date dateOfFault;
	private String warranty;
	private float price;
	private String serviceCompany;
	private int equipmentID;
	
	public UnplannedServices() {
		
	}

	public UnplannedServices(int id, String faultType, Date dateOfFault, String warranty, float price,
			String serviceCompany) {
		super();
		this.id = id;
		this.faultType = faultType;
		this.dateOfFault = dateOfFault;
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

	public String getFaultType() {
		return faultType;
	}

	public void setFaultType(String faultType) {
		this.faultType = faultType;
	}

	public Date getDateOfFault() {
		return dateOfFault;
	}

	public void setDateOfFault(Date dateOfFault) {
		this.dateOfFault = dateOfFault;
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
