This project provides a consumer for sensor-emulator

Following REST APIs are developed

1. Create
Consumes the data sent by POST from sensor-emulator<br>
Save the metrics<br>
If Overweight or Underweight found, stores the corresponding alert.<br>
Execution Instruction<br>
run the sensor-emulator as<br> 
java -jar -Dbase.value=150 -Dapi.url=http://localhost:8080/create sensor-emulator-0.0.1-SNAPSHOT.jar

2. Read Metrics 
Returns all the metrics stored in db
Execution Instruction
localhost:8080/readMetrics


3. Read Metrics By TimeRange 
Returns all the metrics stored in db in the given time range
Execution Instruction
localhost:8080/readMetricsByTimeRange?startTimeStamp=100&endTimeStamp=100

4. Read Alerts 
Returns all the Alerts stored in db
Execution Instruction
localhost:8080/readAlert


5. Read Alerts By TimeRange 
Returns all the alerts stored in db with their message in the given time range
Execution Instruction
localhost:8080/readAlertsByTimeRange?startTimeStamp=100&endTimeStamp=100

Building Instrictions
mvn clean package
This creates a jar file in target directory 'myProject-0.0.1-SNAPSHOT.jar'

Running Instructions
Start tomcat
Start mongoDB
Run the com.aditya.myProject.ApplicationLoader class
To run APIs please follow above instructions for each API

Implementation 
Value of base weight is consumed from the application.properties file
Check for under and over weight metrics is achieved using EasyRules.
MongoDB is integrated using Morphia
Unites cases are written for different scenarios

	

