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
}