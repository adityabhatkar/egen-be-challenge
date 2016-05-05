package com.aditya.myProject.data;


import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class Metric {

	/*@Id
	private ObjectId id;*/
	
	@Id
	private long timeStamp;
	private String value;
	
	public Metric() {
		
	}
	
	public Metric(long timeStamp, String value) {
		super();
		this.timeStamp = timeStamp;
		this.value = value;
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

	/*public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}*/	

}
