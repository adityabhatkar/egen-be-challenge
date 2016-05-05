package com.aditya.myProject;

import java.util.List;

import org.easyrules.api.RulesEngine;
import org.easyrules.core.RulesEngineBuilder;
import org.mongodb.morphia.Key;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import com.aditya.myProject.data.AlertMetric;
import com.aditya.myProject.data.Metric;

/*Service class holds business logic for operations*/
@ConfigurationProperties("com.aditya.myProject")
@Service
public class SensorService {		
	
	private SensorDAO sensorDAO;
	private AlertsDAO alertDAO;
	private UnderWeightRule underWeightRule;
	private OverWeightRule overWeightRule;
	private static RulesEngine rulesEngine=RulesEngineBuilder.aNewRulesEngine().build();
	
	private String failedCreate;
	private String overweightCreate;
	private String underweightCreate;
	private String metricCreate;
	private String baseWeight;
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public SensorService(SensorDAO sensorDAO, AlertsDAO alertsDAO, UnderWeightRule underWeightRule, OverWeightRule overWeightRule){
		
		this.sensorDAO=sensorDAO;
		this.alertDAO=alertsDAO;
		
		this.underWeightRule=underWeightRule;
		this.overWeightRule=overWeightRule;
		
		rulesEngine.registerRule(underWeightRule);
		rulesEngine.registerRule(overWeightRule);
              
	}
	
	
	/*Save incoming data
	 * Check if over or under weight
	 * return appropriate response message code
	 * */
	public String saveMetric(Metric metric){		
		
		logger.info("SensorService:saveMetric::Start");
		logger.info("SensorService:saveMetric:: timeStamp "+metric.getTimeStamp()+" value "+metric.getValue());

		//save every incoming metric in db
		Key<Metric> key=sensorDAO.save(metric);
		if(key==null){
			return failedCreate;
		}
		
		underWeightRule.setBaseWeight(Float.parseFloat(baseWeight));
		underWeightRule.setNewWeight(Float.parseFloat(metric.getValue()));
		underWeightRule.setTimeStamp(metric.getTimeStamp());		
		
		overWeightRule.setBaseWeight(Float.parseFloat(baseWeight));		
		overWeightRule.setNewWeight(Float.parseFloat(metric.getValue()));
		overWeightRule.setTimeStamp(metric.getTimeStamp());
		
		//fire rules to determine if over or under weight metric found
		rulesEngine.fireRules();
		
		logger.info("SensorService:saveMetric::End");
		
		//depending on rule executed, return the appropriate response message code
		if(overWeightRule.isExecuted()){
			return overweightCreate;
		}
		else if(underWeightRule.isExecuted()){
			return underweightCreate;
		}		
		return metricCreate;
	}
	
	
	/*Return all metrics
	 * */
	public List<Metric> readAllMetrics(){
		logger.info("SensorService:readAllMtrics::Start");
		//read from db
		List<Metric> MetricList = sensorDAO.readAllMetrics();
		logger.info("SensorService:readAllMtrics::End");		
		return MetricList;
	}
	
	
	/*Return metrics in given rage of timestamps
	 * */
	public List<Metric> readMtricsByTimeRange(long startTimeStamp, long endTimeStamp){		
		logger.info("SensorService:readMtricsByTimeRange::Start");
		//read from db
		List<Metric> MetricList=sensorDAO.readMtricsByTimeRange(startTimeStamp, endTimeStamp);
		logger.info("SensorService:readMtricsByTimeRange::End");			
		return MetricList;
	}
	
	
	/*Return all Alerts
	 * */
	public List<AlertMetric> readAllAlerts(){
		logger.info("SensorService:readAllAlerts::Start");
		//read from db
		List<AlertMetric> alertList = alertDAO.getAllAlerts();
		logger.info("SensorService:readAllAlerts::End");
		return alertList;
	}
	
	
	/*Return alerts in given rage of timestamps
	 * */
	public List<AlertMetric> readAlertsByTimeRange(long startTimeStamp, long endTimeStamp){
		logger.info("SensorService:readAlertByTimeRange::Start");
		//read from db
		List<AlertMetric> alertList = alertDAO.readAlertsByTimeRange(startTimeStamp, endTimeStamp);
		logger.info("SensorService:readAlertByTimeRange::End");
		return alertList;
	}

	//setters and getters
	
	public SensorDAO getSensorDAO() {
		return sensorDAO;
	}


	public void setSensorDAO(SensorDAO sensorDAO) {
		this.sensorDAO = sensorDAO;
	}


	public AlertsDAO getAlertDAO() {
		return alertDAO;
	}


	public void setAlertDAO(AlertsDAO alertDAO) {
		this.alertDAO = alertDAO;
	}


	public UnderWeightRule getUnderWeightRule() {
		return underWeightRule;
	}


	public void setUnderWeightRule(UnderWeightRule underWeightRule) {
		this.underWeightRule = underWeightRule;
	}


	public OverWeightRule getOverWeightRule() {
		return overWeightRule;
	}


	public void setOverWeightRule(OverWeightRule overWeightRule) {
		this.overWeightRule = overWeightRule;
	}


	public RulesEngine getRulesEngine() {
		return rulesEngine;
	}

	public String getFailedCreate() {
		return failedCreate;
	}

	public void setFailedCreate(String failedCreate) {
		this.failedCreate = failedCreate;
	}

	public String getOverweightCreate() {
		return overweightCreate;
	}

	public void setOverweightCreate(String overweightCreate) {
		this.overweightCreate = overweightCreate;
	}

	public String getUnderweightCreate() {
		return underweightCreate;
	}

	public void setUnderweightCreate(String underweightCreate) {
		this.underweightCreate = underweightCreate;
	}

	public String getMetricCreate() {
		return metricCreate;
	}

	public void setMetricCreate(String metricCreate) {
		this.metricCreate = metricCreate;
	}

	public String getBaseWeight() {
		return baseWeight;
	}

	public void setBaseWeight(String baseWeight) {
		this.baseWeight = baseWeight;
	}

	
	

}
