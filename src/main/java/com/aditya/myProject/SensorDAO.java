package com.aditya.myProject;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.DAO;

import com.aditya.myProject.data.Metric;

public interface SensorDAO extends DAO<Metric, ObjectId>{
	
	public List<Metric> readAllMetrics();
	
	public List<Metric> readMtricsByTimeRange(long startTimeStamp, long endTimeStamp);
	

}
