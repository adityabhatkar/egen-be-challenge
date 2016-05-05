package com.aditya.myProject;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aditya.myProject.data.AlertMetric;

/*Class to handle db operations
 * */
@Repository
public class AlertsDAOImpl extends BasicDAO<AlertMetric, ObjectId> implements AlertsDAO{

	@Autowired
	public AlertsDAOImpl(Datastore ds) {
		super(AlertMetric.class, ds);
	}

	public List<AlertMetric> getAllAlerts() {
		Query<AlertMetric> query=createQuery();
		return query.asList();
	}

	public List<AlertMetric> readAlertsByTimeRange(long startTimeStamp, long endTimeStamp) {		
		Query<AlertMetric> query=createQuery()
				.field("timeStamp").lessThan(endTimeStamp)
				.field("timeStamp").greaterThan(startTimeStamp);		
		return query.asList();
	}

}
