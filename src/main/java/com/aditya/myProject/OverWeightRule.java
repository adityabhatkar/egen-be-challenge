package com.aditya.myProject;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aditya.myProject.data.AlertMetric;

//Rule to check if metric is overweight
@Component
@Rule
public class OverWeightRule {

	//flag to check if rule is executed
	private boolean executed;
	private float baseWeight;
	private float newWeight;	
	private long timeStamp;
	private AlertsDAO alertsDAO;
	private MorphiaService morphiaService;
	
	@Autowired
	public OverWeightRule(MorphiaService morphiaService, AlertsDAOImpl alertsDAOImpl) {
		super();	
		this.morphiaService=morphiaService;
		this.alertsDAO = alertsDAOImpl;
	}
	

	@Condition
	public boolean isOverWeight(){		
		executed=false;
		float weight=baseWeight+(baseWeight/10);
		System.out.println("new weight "+newWeight+" weight "+weight);
		return (newWeight>weight);		
	}

	//if found overweight then save the Alert
	@Action
	public void then() throws Exception{
		AlertMetric alertMetric=new AlertMetric(timeStamp, newWeight+"", "Overweight");
		alertsDAO.save(alertMetric);
		executed=true;		
	}
	

	
	public float getBaseWeight() {
		return baseWeight;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public AlertsDAO getAlertsDAO() {
		return alertsDAO;
	}

	public void setAlertsDAO(AlertsDAO alertsDAO) {
		this.alertsDAO = alertsDAO;
	}

	public MorphiaService getMorphiaService() {
		return morphiaService;
	}

	public void setMorphiaService(MorphiaService morphiaService) {
		this.morphiaService = morphiaService;
	}

	public void setBaseWeight(float baseWeight) {
		this.baseWeight = baseWeight;
	}

	public float getNewWeight() {
		return newWeight;
	}

	public void setNewWeight(float newWeight) {
		this.newWeight = newWeight;
	}

	public boolean isExecuted() {
		return executed;
	}

	public void setExecuted(boolean executed) {
		this.executed = executed;
	}
	
	


}
