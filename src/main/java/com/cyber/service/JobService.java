/**
 * 
 */
package com.cyber.service;

import java.util.List;

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
		// TODO Auto-generated method stub
		return iJobDao.createJob(job);
	}

	@Override
	public List<Job> getJob() {
		// TODO Auto-generated method stub
		return iJobDao.getJob();
	}

	@Override
	public Job findById(int id) {
		// TODO Auto-generated method stub
		return iJobDao.findById(id);
	}

	@Override
	public void postBulkJobs(List<Job> jobs) {
		// TODO Auto-generated method stub
		iJobDao.postBulkJobs(jobs);		
	}

	@Override
	public List<Job> findJobByType(String type) {
		// TODO Auto-generated method stub
		return iJobDao.findJobByType(type);
	}

	@Override
	public List<Job> findJobByExp(String exp) {
		// TODO Auto-generated method stub
		return iJobDao.findJobByExp(exp);
	}

	@Override
	public List<Job> findJobByCountry(String country) {
		// TODO Auto-generated method stub
		return iJobDao.findJobByCountry(country);
	}

	@Override
	public List<Job> findJobByAvailability(List<String> availabiltyList) {
		// TODO Auto-generated method stub
		return iJobDao.findJobByAvailability(availabiltyList);
	}

	@Override
	public List<Job> findJobByPayRate(int low, int high) {
		// TODO Auto-generated method stub
		return iJobDao.findJobByPayRate(low, high);
	}

	@Override
	public List<Job> findJobBySkill(List<String> skill) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Job> findJobByLang(List<String> lang) {
		// TODO Auto-generated method stub
		return null;
	}
}