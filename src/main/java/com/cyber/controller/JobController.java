
package com.cyber.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.cyber.helper.JobHelper;
import com.cyber.response.JobResponseMessage;
import com.cyber.response.JobsResponseMessage;
import com.cyber.response.ResponseStatus;
import com.cyber.response.ResponseStatusCode;
/**
 * @author Karthikeyan
 *
 */
@RestController
@RequestMapping(value = "/job")
public class JobController {

	private static final Logger log=LogManager.getLogger(JobController.class);

	@Autowired
	private JobHelper jobHelper;

	@PostMapping("/postjob")
	public JobResponseMessage<Job> createJob(@RequestBody Job job, UriComponentsBuilder ucBuilder){
		ResponseStatus status = null;

		int id=jobHelper.createJob(job);
		if (id>0) {
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK,"SUCCESS");
		} else {
			status = new ResponseStatus(ResponseStatusCode.STATUS_NORECORD,"No Record found");
		}
		return new JobResponseMessage<Job>(status, job);
	}

	@PostMapping("/processjobexcel")
	public String processExcel(@RequestParam("excelfile") MultipartFile excelfile) {
		return jobHelper.processExcel(excelfile);
	}

	@GetMapping("/getjob/{id}")
	public JobResponseMessage<Job> getJobById(@PathVariable("id") int id) {
		log.info("Fetching Job with id " + id);
		ResponseStatus status = null;
		Job job = jobHelper.getJobById(id);

		if (job != null) {
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK,"SUCCESS");
		} else {
			status = new ResponseStatus(ResponseStatusCode.STATUS_NORECORD,"No Record found");
		}
		return new JobResponseMessage<Job>(status, job);
	}

	@GetMapping("/getByType/{type}")
	public JobsResponseMessage getJobByType(@PathVariable("type") String type) {
		log.info("Fetching Job with type " + type);
		ResponseStatus status = null;
		List<Job> job = jobHelper.getJobByType(type);
		if (job != null) {
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK,"SUCCESS");
		} else {
			status = new ResponseStatus(ResponseStatusCode.STATUS_NORECORD,"No Record found");
		}
		return new JobsResponseMessage(status, job);
	}

	@GetMapping("/getByExp/{exp}")
	public JobsResponseMessage getJobByExp(@PathVariable("exp") String exp) {
		log.info("Fetching Job with experience " + exp);
		ResponseStatus status = null;
		List<Job> job = jobHelper.getJobByExp(exp);
		if (job != null) {
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK,"SUCCESS");
		} else {
			status = new ResponseStatus(ResponseStatusCode.STATUS_NORECORD,"No Record found");
		}
		return new JobsResponseMessage(status, job);
	}

	@GetMapping("/getByCountry/{country}")
	public JobsResponseMessage getJobByCountry(@PathVariable("country") String country) {
		log.info("Fetching Job with country " + country);
		ResponseStatus status = null;
		List<Job> job = jobHelper.getJobByCountry(country);
		if (job != null) {
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK,"SUCCESS");
		} else {
			status = new ResponseStatus(ResponseStatusCode.STATUS_NORECORD,"No Record found");
		}
		return new JobsResponseMessage(status, job);
	}

	@GetMapping("/getByAvailability/{availability}")
	public JobsResponseMessage getJobByAvailability(@PathVariable("availability") String availability) {
		log.info("Fetching Job with availability " + availability);
		ResponseStatus status = null;
		List<Job> job = jobHelper.getJobByAvailability(availability);
		if (job != null) {
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK,"SUCCESS");
		} else {
			status = new ResponseStatus(ResponseStatusCode.STATUS_NORECORD,"No Record found");
		}
		return new JobsResponseMessage(status, job);
	}

	@GetMapping("/getBySkills/{skills}")
	public JobsResponseMessage getJobBySkills(@PathVariable("skills") String skills) {
		log.info("Fetching Job with skills " + skills);
		ResponseStatus status = null;
		List<Job> job = jobHelper.getJobBySkills(skills);
		if (job != null) {
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK,"SUCCESS");
		} else {
			status = new ResponseStatus(ResponseStatusCode.STATUS_NORECORD,"No Record found");
		}
		return new JobsResponseMessage(status, job);
	}

	@GetMapping("/getByLanguage/{language}")
	public JobsResponseMessage getJobByLanguage(@PathVariable("language") String language) {
		log.info("Fetching Job with language " + language);
		ResponseStatus status = null;
		List<Job> job = jobHelper.getJobByLang(language);
		if (job != null) {
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK,"SUCCESS");
		} else {
			status = new ResponseStatus(ResponseStatusCode.STATUS_NORECORD,"No Record found");
		}
		return new JobsResponseMessage(status, job);
	}

	@GetMapping("/getByPayRate/{low}/{high}")
	public JobsResponseMessage getJobByPayrate(@PathVariable("low") int low,@PathVariable("high")  int high) {
		log.info("Fetching Job with Payrate");
		ResponseStatus status = null;
		List<Job> job = jobHelper.getJobByPayRate(low, high);
		if (job != null) {
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK,"SUCCESS");
		} else {
			status = new ResponseStatus(ResponseStatusCode.STATUS_NORECORD,"No Record found");
		}
		return new JobsResponseMessage(status, job);
	}

	@GetMapping("/getalljobs")
	public JobsResponseMessage getAllJobs() {
		ResponseStatus status = null;
		List<Job> jobs=jobHelper.getAllJobs();
		if (jobs != null) {
			status = new ResponseStatus(ResponseStatusCode.STATUS_OK,"SUCCESS");
		} else {
			status = new ResponseStatus(ResponseStatusCode.STATUS_NORECORD,"No Record found");
		}
		return new JobsResponseMessage(status, jobs);
	}
}