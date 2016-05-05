package com.aditya.myProject;

//import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;

//import com.aditya.myProject.data.AlertMetric;
import com.aditya.myProject.data.Metric;

@ConfigurationProperties("com.aditya.myProject")
public class ServiceTest extends AbstractTest{

	@Autowired
	private SensorService sensorService;
	
	private String failedCreate;
	private String overweightCreate;
	private String underweightCreate;
	private String metricCreate;
	private String baseWeight;
	
	@Test
	public void createOverweight(){
		String response=sensorService.saveMetric(new Metric(123456789,"175"));
		Assert.assertNotNull(response);
		//logger.info("response is******************"+response);
		Assert.assertEquals( overweightCreate, response);	
	}
	
	@Test
	public void createUnderweight(){
		String response=sensorService.saveMetric(new Metric(987654321,"130"));
		Assert.assertNotNull(response);
		//logger.info("response is******************"+response);
		Assert.assertEquals( underweightCreate, response);	
	}
	
	
	@Test
	public void createNormalMetric(){
		String response=sensorService.saveMetric(new Metric(456789123,"155"));
		Assert.assertNotNull(response);
		//logger.info("response is******************"+response);
		Assert.assertEquals( metricCreate, response);	
	}
	

	public SensorService getSensorService() {
		return sensorService;
	}

	public void setSensorService(SensorService sensorService) {
		this.sensorService = sensorService;
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
	
	/*@Test
	public void readAllMetrics(){
		List<SensorData> metrics= sensorService.readAllMetrics();
		//logger.info("Printing metrics size------ "+metrics.size());
		Assert.assertNotNull(metrics);		
	}
	
	@Test
	public void readAllAlerts(){
		List<AlertMetric> alerts=sensorService.readAllAlerts();
		Assert.assertNotNull(alerts);
	}*/

}
