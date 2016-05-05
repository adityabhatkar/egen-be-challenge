package com.aditya.myProject;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aditya.myProject.data.Metric;

/*Class to handle db operations
 * */
@Repository
public class SensorDAOImpl extends BasicDAO<com.aditya.myProject.data.Metric, ObjectId> implements SensorDAO{

	@Autowired
	public SensorDAOImpl(Datastore ds) {
		super(Metric.class, ds);		
	}


	public List<Metric> readMtricsByTimeRange(long startTimeStamp, long endTimeStamp) {
		Query<Metric> query=createQuery()
				.field("timeStamp").lessThan(endTimeStamp)
				.field("timeStamp").greaterThan(startTimeStamp);		
		return query.asList();
	}


	public List<Metric> readAllMetrics() {
		Query<Metric> query=createQuery();
		return query.asList();
	}

}
