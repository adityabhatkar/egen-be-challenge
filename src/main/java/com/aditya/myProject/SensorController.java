package com.aditya.myProject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aditya.myProject.data.AlertMetric;
import com.aditya.myProject.data.Metric;

@RestController
public class SensorController {
	
	@Autowired
	private SensorService sensorService;	
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public String saveSensorData(@RequestBody Metric metric){		
		return sensorService.saveMetric(metric);
		
	}
	
	@RequestMapping(value="/readMetrics", method = RequestMethod.GET)
	public List<Metric> readAllMetrics(){
		return sensorService.readAllMetrics();		
	}
	
	@RequestMapping(value="/readMetricsByTimeRange", method = RequestMethod.GET)
	public List<Metric> readMetricsByTimeRange(@RequestParam("startTimeStamp") long startTimeStamp, @RequestParam("endTimeStamp") long endTimeStamp){
		//localhost:8080/readMetricsByTimeRange?startTimeStamp=100&endTimeStamp=100
		return sensorService.readMtricsByTimeRange(startTimeStamp, endTimeStamp);
	}
	
	@RequestMapping(value="/readAlert", method = RequestMethod.GET)
	public List<AlertMetric> readAllAlerts(){
		return sensorService.readAllAlerts();
	}
	
	@RequestMapping(value="/readAlertsByTimeRange", method = RequestMethod.GET)
	public List<AlertMetric> readAlertsByTimeRange(@RequestParam("startTimeStamp") long startTimeStamp, @RequestParam("endTimeStamp") long endTimeStamp){
		//localhost:8080/readAlertsByTimeRange?startTimeStamp=100&endTimeStamp=100
		return sensorService.readAlertsByTimeRange(startTimeStamp, endTimeStamp);
	}
	
	public SensorService getSensorService() {
		return sensorService;
	}

	public void setSensorService(SensorService sensorService) {
		this.sensorService = sensorService;
	}


}
