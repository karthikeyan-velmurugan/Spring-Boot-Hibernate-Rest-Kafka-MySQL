# Spring-Boot-Hibernate-Rest-Kafka-MySQL

#Technologies Used :
Java 8
Spring Boot
Hibernate
MySQL
Kafka
RestFul Webservice
Maven

#Deployment : 

– Start a ZooKeeper:
	C:\kafka_2.12-0.10.2.1>.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

– Start the Apache Kafka server:
	.\bin\windows\kafka-server-start.bat .\config\server.properties

- Build and Install the Application: 
	clean install 
	spring-boot:run

#Before Run The Project Need to Change configuration as per your details :
for Application.properties file

server.address=localhost
server.port=9090

spring.datasource.driver-class-name = com.mysql.jdbc.jdbc2.optional.MysqlDataSource
spring.datasource.url = jdbc:mysql://localhost:3306/cyber_db
spring.datasource.username = root
spring.datasource.password = root

- Create database "cyber_db"
- For Kafka configuration i have used localhost, so before run the project install kafka and make sure its running.

#API's

Note : For Bulk upload sample file("PostBulkJobs.xlsx") placed inside resource folder

-> Create New User:
-
	url : http://localhost:9090/user/createuser
	param type : raw - JSON(applivation/json) 
	sample Request : {"userName":"New Way Coders"}
	method : POST
	
-> Create New Job:
-
	url : http://localhost:9090/job/postjob
	param type : raw - JSON(applivation/json) 
	sample Request : 
	{
		"jobTitle":"Java Developer",
		"jobDescription":"Need POC for Ecommerce website using spring boot with micro service.",
		"country":"IND",
		"state":"TN",
		"availability":"Part-Time",
		"replyRate":0,
		"payRate":44,
		"experience":5,
		"skills":"Java, Spring Boot, Hibernate, Micro Service, Oracle, REST",
		"language":"English",
		"jobType":"Development",
		"userInfo":{
		"userName":"New Way Coders"
		}
	}
	method : POST
	
-> Upload Bulk Job using Excel input file:
-
	url : http://localhost:9090/job/processjobexcel
	param type : RequestParam - File
	sample Request : Key=excelfile,value=(File location :: ..\resources\PostBulkJobs.xlsx )
	method : POST

-> Get Job by ID:
-
	url : http://localhost:9090/job/getjob/{id}
	param type : PathVariable
	sample Request : http://localhost:9090/job/getjob/93
	method : GET
	
-> Filter Job by Type:
-
	url : http://localhost:9090/job/getByType/{type}
	param type : PathVariable
	sample Request : http://localhost:9090/job/getByType/Development
	method : GET

-> Filter Job by Experience:
-
	url : http://localhost:9090/job/getByExp/{exp}
	param type : PathVariable
	sample Request : http://localhost:9090/job/getByExp/5
	method : GET
	
-> Filter Job by Country:
-
	url : http://localhost:9090/job/getByCountry/{country}
	param type : PathVariable
	sample Request : http://localhost:9090/job/getByCountry/IND
	method : GET
	
-> Filter Job by Availability:
-
	url : http://localhost:9090/job/getByAvailability/{availability}
	param type : PathVariable
	sample Request : http://localhost:9090/job/getByAvailability/Full-Time,Hourly
	method : GET

-> Filter Job by Skills:
-
	url : http://localhost:9090/job/getBySkills/{skills}
	param type : PathVariable
	sample Request : http://localhost:9090/job/getBySkills/Java
	method : GET
	
-> Filter Job by Language:
-
	url : http://localhost:9090/job/getByLanguage/{language}
	param type : PathVariable
	sample Request : http://localhost:9090/job/getByLanguage/English
	method : GET
	
-> Filter Job by PayRate:
-
	url : http://localhost:9090/job/getByPayRate/{low}/{high}
	param type : PathVariable
	sample Request : http://localhost:9090/job/getByPayRate/20/40
	method : GET
	
-> Get All Jobs:
-
	url : http://localhost:9090/job//getalljobs
	param type : PathVariable
	sample Request : http://localhost:9090/job//getalljobs
	method : GET
