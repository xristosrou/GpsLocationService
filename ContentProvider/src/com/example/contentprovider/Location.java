package com.example.contentprovider;

import android.R.integer;

public class Location {
	private int _id;
	private double Longitude; 
	private double Latitude;
	private int Timestamp;  
       
	public Location(double longitude, double latitude, int timestamp) {
		super();
		Longitude = longitude;
		Latitude = latitude;
		Timestamp = timestamp;
	}
	
	public Location(int _id, double longitude, double latitude, int timestamp) {
		super();
		this._id = _id;
		Longitude = longitude;
		Latitude = latitude;
		Timestamp = timestamp;
	}

	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public double getLongitude() {
		return Longitude;
	}
	public void setLongitude(double longitude) {
		Longitude = longitude;
	}
	public double getLatitude() {
		return Latitude;
	}
	public void setLatitude(double latitude) {
		Latitude = latitude;
	}
	public int getTimestamp() {
		return Timestamp;
	}
	public void setTimestamp(int timestamp) {
		Timestamp = timestamp;
	}
     
     
     
	
     
     
}
