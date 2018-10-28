/**
 * 
 */
package com.cyber.helper;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cyber.domain.Job;
import com.cyber.domain.UserInfo;
import com.cyber.service.IJobService;
import com.cyber.service.IUserService;
import com.cyber.service.KafkaConsumer;

/**
 * @author Karthikeyan
 *
 */
@Service
public class JobHelper {

	private static final Logger log=LogManager.getLogger(UserHelper.class);

	@Autowired
	private IJobService iJobService;

	@Autowired
	private IUserService iUserService;

	@Autowired
	private UserHelper userHelper;
	
	@Autowired
	private KafkaTemplate<String, Job> kafkaTemplate;

	@Autowired
	private KafkaConsumer consumer;
	
	private static final String TOPIC = "job-kafka-topic";

	public int createJob(Job job){
		int id=0;
		Job jobEntity =null;
		UserInfo userInfo=iUserService.findByName(job.getUserInfo().userName);
		if(userInfo!=null) {
			UserInfo user=iUserService.findById(userInfo.getUserId());
			jobEntity = insertJob(job, user);
			id=iJobService.createJob(jobEntity);	
			return id;
		}else {
			UserInfo entity=new UserInfo();
			entity.setUserName(job.getUserInfo().userName);
			int usrId=	userHelper.createUser(entity);
			UserInfo user=iUserService.findById(usrId);
			jobEntity = insertJob(job, user);
			id=iJobService.createJob(jobEntity);
			return id;
		}		
	}

	public String processExcel(MultipartFile excelfile) {		
		try {
			int i = 1;
			XSSFWorkbook workbook = new XSSFWorkbook (excelfile.getInputStream());
			XSSFSheet worksheet = workbook.getSheetAt(0);
			while (i <= worksheet.getLastRowNum()) {
				XSSFRow row = worksheet.getRow(i++);
				Job job =null;
				UserInfo userInfo=iUserService.findByName(row.getCell(2).getStringCellValue());
				if(userInfo!=null) {
					job=inserBulkJob(job, row, userInfo);
					//iJobService.createJob(job);
					produceJob(job);
					consumeMsg(job);
				}else {
					UserInfo entity=new UserInfo();
					entity.setUserName(row.getCell(2).getStringCellValue());
					int id=	userHelper.createUser(entity);
					UserInfo user=iUserService.findById(id);
					job=inserBulkJob(job, row, user);
					//iJobService.createJob(job);
					produceJob(job);
					consumeMsg(job);
				}
			}			
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Uploded Sucessfully..!!";
	}

	public void produceJob(Job job) {
		kafkaTemplate.send(TOPIC, job);
	}
	public void consumeMsg(Job job) {
		log.info("Info for Consuming and Insert Data to DB..!!");
		Job entity=consumer.consumeJob(job);
		iJobService.createJob(entity);
	}

	public Job getJobById(int id) {
		log.info("Fetching Job with id " + id);
		Job job = iJobService.findById(id);		
		return job;
	}

	public List<Job> getAllJobs() {
		List<Job> jobs=iJobService.getJob();
		return jobs;
	}

	public List<Job> getJobByType(String type) {
		log.info("Fetching Job with type " + type);
		List<Job> job = iJobService.findJobByType(type);		
		return job;
	}

	public List<Job> getJobByExp(String exp) {
		log.info("Fetching Job with exp " + exp);
		List<Job> job = iJobService.findJobByExp(exp);		
		return job;
	}

	public List<Job> getJobByCountry(String country) {
		log.info("Fetching Job with country " + country);
		List<Job> job = iJobService.findJobByCountry(country);		
		return job;
	}

	public List<Job> getJobByAvailability(String[] availabiltyList) {
		log.info("Fetching Job with availabiltyList " + availabiltyList);		
		List<Job> job = iJobService.findJobByAvailability(availabiltyList);
		return job;
	}

	public List<Job> getJobBySkills(String skills) {
		log.info("Fetching Job with skills " + skills);
		List<Job> job = iJobService.findJobBySkill(skills);		
		return job;
	}

	public List<Job> getJobByLang(String language) {
		log.info("Fetching Job with language " + language);
		List<Job> job = iJobService.findJobByLang(language);		
		return job;
	}

	public List<Job> getJobByPayRate(int low, int high) {
		log.info("Fetching Job with PayRate ");
		List<Job> job = iJobService.findJobByPayRate(low, high);		
		return job;
	}

	public Job insertJob(Job job,UserInfo user){
		Job jobEntity = new Job();
		jobEntity.setJobTitle(job.getJobTitle());
		jobEntity.setJobDescription(job.getJobDescription());
		jobEntity.setCountry(job.getCountry());
		jobEntity.setState(job.getState());
		jobEntity.setAvailability(job.getAvailability());
		jobEntity.setReplyRate(job.getReplyRate());				
		jobEntity.setPayRate(job.getPayRate());
		jobEntity.setExperience(job.getExperience());
		jobEntity.setSkills(job.getSkills());
		jobEntity.setLanguage(job.getLanguage());
		jobEntity.setJobType(job.getJobType());
		jobEntity.setUserInfo(user);
		return jobEntity;
	}

	public Job inserBulkJob(Job job,XSSFRow row,UserInfo user) {
		job = new Job();
		job.setJobTitle(row.getCell(0).getStringCellValue());
		job.setJobDescription(row.getCell(1).getStringCellValue());
		job.setCountry(row.getCell(3).getStringCellValue());
		job.setState(row.getCell(4).getStringCellValue());
		job.setAvailability(row.getCell(5).getStringCellValue());
		job.setReplyRate(Integer.parseInt(row.getCell(6).getStringCellValue()));				
		job.setPayRate(Integer.parseInt(row.getCell(7).getStringCellValue()));
		job.setExperience(Integer.parseInt(row.getCell(8).getStringCellValue()));
		job.setSkills(row.getCell(9).getStringCellValue());
		job.setLanguage(row.getCell(10).getStringCellValue());
		job.setJobType(row.getCell(11).getStringCellValue());
		job.setUserInfo(user);
		return job;
	}
}