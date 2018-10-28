package com.cyber.service;

import java.util.List;

import com.cyber.domain.Job;

/**
 * @author Karthikeyan
 *
 */
public interface IJobService {

	public int createJob(Job job);

	public List<Job> getJob();

	public Job findById(int id);
	
	public void postBulkJobs(List<Job> jobs);

	public List<Job> findJobBySkill(String skill);

	public List<Job> findJobByAvailability(String[] availabiltyList);

	public List<Job> findJobByType(String type);

	public List<Job> findJobByPayRate(int low, int high);

	public List<Job> findJobByExp(String exp);

	public List<Job> findJobByCountry(String country);

	public List<Job> findJobByLang(String lang);	
}