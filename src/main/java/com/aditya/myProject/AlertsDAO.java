package com.aditya.myProject;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.DAO;

import com.aditya.myProject.data.AlertMetric;


public interface AlertsDAO extends DAO<AlertMetric, ObjectId>{
	
	public List<AlertMetric> getAllAlerts();
	
	public List<AlertMetric> readAlertsByTimeRange(long startTimeStamp, long endTimeStamp);
	
}
