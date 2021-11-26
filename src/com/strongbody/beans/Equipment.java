package com.strongbody.beans;

public class Equipment {
	private int id;
	private String name;
	private String serialNo;
	private String category;
	private String manufacturer;
	private String width;
	private String length;
	private String heigth;
	private String weight;
	private int scheduledServicesID;
	private int unplannedServicesID;
	
	public Equipment() {
		
	}
	

	public Equipment(int id, String name, String serialNo, String category, String manufacturer, String width,
			String length, String heigth, String weight) {
		super();
		this.id = id;
		this.name = name;
		this.serialNo = serialNo;
		this.category = category;
		this.manufacturer = manufacturer;
		this.width = width;
		this.length = length;
		this.heigth = heigth;
		this.weight = weight;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getHeigth() {
		return heigth;
	}

	public void setHeigth(String heigth) {
		this.heigth = heigth;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}


	public int getScheduledServicesID() {
		return scheduledServicesID;
	}


	public void setScheduledServicesID(int scheduledServicesID) {
		this.scheduledServicesID = scheduledServicesID;
	}


	public int getUnplannedServicesID() {
		return unplannedServicesID;
	}


	public void setUnplannedServicesID(int unplannedServicesID) {
		this.unplannedServicesID = unplannedServicesID;
	}
	

}
