package com.aditya.myProject;


import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.annotations.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import com.mongodb.MongoClient;

//Morphia configuration 
@Configuration
public class MorphiaService {
	
	@Autowired
	private MongoClient mongoClient;
	
	private Datastore datastore;	
	//private String scanPackage;
	//private String databaseName;
	
	public MorphiaService(){	
		
	}
	
	//Configure Datastore
	@Bean
	public Datastore datastore() throws ClassNotFoundException {
		Morphia morphia = new Morphia();
		morphia.mapPackage("com.aditya.myProject.data");
        ClassPathScanningCandidateComponentProvider entityScanner = new ClassPathScanningCandidateComponentProvider(true);
        entityScanner.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
        for (BeanDefinition candidate : entityScanner.findCandidateComponents("com.aditya.myProject.data")) {
        	morphia.map(Class.forName(candidate.getBeanClassName()));
        }
        
        this.datastore=morphia.createDatastore(mongoClient, "sampleSensor3"); 
        //this.datastore=morphia.createDatastore(mongoClient, databaseName);
        return datastore;
    }


	public Datastore getDatastore() {
		return datastore;
	}

	public void setDatastore(Datastore datastore) {
		this.datastore = datastore;
	}

	public MongoClient getMongoClient() {
		return mongoClient;
	}

	public void setMongoClient(MongoClient mongoClient) {
		this.mongoClient = mongoClient;
	}



	/*public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}*/

	/*public String getScanPackage() {
		return scanPackage;
	}

	public void setScanPackage(String scanPackage) {
		this.scanPackage = scanPackage;
	}*/
	
	
}
