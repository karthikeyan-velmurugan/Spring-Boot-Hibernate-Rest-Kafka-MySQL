package com.cyber.controller;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.cyber.domain.Job;
import com.cyber.service.IJobService;
/**
 * @author Karthikeyan
 *
 */
@RestController
@RequestMapping(value = "/job")
public class JobController {

	@Autowired
	private IJobService iJobService;

	@GetMapping(value = "/getjob/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Job> getJobById(@PathVariable("id") int id) {
		System.out.println("Fetching Job with id " + id);
		Job job = iJobService.findById(id);
		if (job == null) {
			return new ResponseEntity<Job>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Job>(job, HttpStatus.OK);
	}

	@PostMapping(value="/postjob",headers="Accept=application/json")
	public ResponseEntity<Job> createJob(@RequestBody Job job, UriComponentsBuilder ucBuilder){
		iJobService.createJob(job);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/job/getjob/{id}").buildAndExpand(job.getJobId()).toUri());
		return new ResponseEntity<Job>(headers, HttpStatus.CREATED);
	}

	@GetMapping(value="/getalljobs", headers="Accept=application/json")
	public List<Job> getAllUser() {
		List<Job> jobs=iJobService.getJob();
		return jobs;
	}

	@PostMapping(value = "/processjobexcel")
	public String processExcel(@RequestParam("excelfile") MultipartFile excelfile) {		
		List<Job> jobList = null;
		try {
			jobList = new ArrayList<>();
			int i = 1;
			XSSFWorkbook workbook = new XSSFWorkbook (excelfile.getInputStream());
			XSSFSheet worksheet = workbook.getSheetAt(0);
			while (i <= worksheet.getLastRowNum()) {
				Job job = new Job();
				XSSFRow row = worksheet.getRow(i++);

				job.setJobTitle(row.getCell(0).getStringCellValue());
				job.setJobDescription(row.getCell(1).getStringCellValue());
				job.setUserName(row.getCell(2).getStringCellValue());
				job.setCountry(row.getCell(3).getStringCellValue());
				job.setState(row.getCell(4).getStringCellValue());
				job.setAvailability(row.getCell(5).getStringCellValue());
				job.setReplyRate(row.getCell(6).getStringCellValue());				
				job.setPayRate(row.getCell(7).getStringCellValue());
				job.setExperience(row.getCell(8).getStringCellValue());
				job.setSkills(row.getCell(9).getStringCellValue());
				job.setLanguage(row.getCell(10).getStringCellValue());

				jobList.add(job);
			}			
			workbook.close();
			iJobService.postBulkJobs(jobList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Uploded Sucessfully..!!";
	}

}