package com.aditya.myProject.data;


import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class AlertMetric {

	@Id
	private long timeStamp;
	private String value;
	private String message;
	
	
	public AlertMetric() {
	}


	public AlertMetric(long timeStamp, String value, String message) {
		super();
		this.value = value;
		this.message = message;
		this.timeStamp=timeStamp;
	}

	public long getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	

}
