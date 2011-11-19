package com.dev52.model;

import java.util.Date;


public class CarLocationVO implements IEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3768191539457368033L;
	
	private int id;
	
	private double latitude;

	private double longitude;
	
	private double altitude;
	
	private Date createdAt;
	
	private String address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	public double getAltitude() {
		return altitude;
	}

}
