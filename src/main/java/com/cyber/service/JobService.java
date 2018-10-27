/**
 * 
 */
package com.cyber.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyber.dao.IJobDao;
import com.cyber.domain.Job;

/**
 * @author Karthikeyan
 *
 */
@Service
public class JobService implements IJobService{

	@Autowired
	IJobDao iJobDao;

	@Override
	public int createJob(Job job) {
		return iJobDao.createJob(job);
	}

	@Override
	public List<Job> getJob() {
		return iJobDao.getJob();
	}

	@Override
	public Job findById(int id) {
		return iJobDao.findById(id);
	}

	@Override
	public void postBulkJobs(List<Job> jobs) {
		iJobDao.postBulkJobs(jobs);		
	}
}