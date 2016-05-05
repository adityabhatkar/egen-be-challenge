This project provides a consumer for <a href="https://github.com/egen/sensor-emulator">sensor-emulator</a>
<h1>REST APIs</h1>

<h2>1. Create</h2>
Consumes the data sent by POST from sensor-emulator<br>
Save the metrics<br>
If Overweight or Underweight found, stores the corresponding alert.<br>
Execution Instruction<br>
run the sensor-emulator as<br> 
java -jar -Dbase.value=150 -Dapi.url=http://localhost:8080/create sensor-emulator-0.0.1-SNAPSHOT.jar

<h2>2. Read Metrics </h2>
Returns all the metrics stored in db<br>
Execution Instruction<br>
localhost:8080/readMetrics<br>
<br>
<br>
<h2>3. Read Metrics By TimeRange </h2>
Returns all the metrics stored in db in the given time range<br>
Execution Instruction<br>
localhost:8080/readMetricsByTimeRange?startTimeStamp=100&endTimeStamp=100<br>
<br>
<br>
<h2>4. Read Alerts </h2>
Returns all the Alerts stored in db<br>
Execution Instruction<br>
localhost:8080/readAlert<br>
<br>
<br>
<h2>5. Read Alerts By TimeRange </h2>
Returns all the alerts stored in db with their message in the given time range<br>
Execution Instruction<br>
localhost:8080/readAlertsByTimeRange?startTimeStamp=100&endTimeStamp=100<br>
<br>
<br>
<h1>Building Instrictions</h1>
mvn clean package<br>
This creates a jar file in target directory 'myProject-0.0.1-SNAPSHOT.jar'<br>
<br>
<br>
<h1>Running Instructions</h1>
Start tomcat<br>
Start mongoDB<br>
Run the com.aditya.myProject.ApplicationLoader class<br>
To run APIs please follow above instructions for each API<br>
<br>
<br>
<h1>Implementation </h1>
Value of base weight is consumed from the application.properties file<br>
Check for under and over weight metrics is achieved using EasyRules.<br>
MongoDB is integrated using Morphia<br>
Unites cases are written for different scenarios<br>

	

