/**
 * 
 */
package com.cyber.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cyber.domain.Job;

/**
 * @author Karthikeyan
 *
 */
@Repository
@Transactional
public class JobDao implements IJobDao{

	private static final Logger log=LogManager.getLogger(JobDao.class);

	@Autowired
	private SessionFactory sessionFactory; 

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public int createJob(Job job) {
		log.info("Entered createJob for JobDao::");
		int id=0;
		try {
			id=(int) getSession().save(job);
			log.info("Job Saved SuccessFully for JobDao.");
		}catch(Exception ex) {
			log.error("Job Registration Failed for JobDao :: ",ex);
		}
		return id;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Job> getJob() {
		log.info("Entered getJob for JobDao::");
		List<Job> list=null;
		try {
			list=getSession().createQuery("from Job").list();
		}catch(Exception ex) {
			log.error("Getting Job List Failed for JobDao:: ",ex);
		}
		return (list!=null && list.size()>0)?list:null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Job findById(int id) {
		log.info("Entered findById for JobDao::");
		List<Job> list=null;
		try {
			list=getSession().createQuery("from Job where jobId=?").setParameter(0, id).list();
		}catch(Exception ex) {
			log.error("Getting Job List Failed for JobDao:: ",ex);
		}
		return (list!=null && list.size()>0)?list.get(0):null;
	}

	@Override
	public void postBulkJobs(List<Job> jobs) {
		log.info("Entered postBulkJobs for JobDao::");
		try{
			if (jobs != null && jobs.size() > 0) {
				for (Job job: jobs) {                
					getSession().persist(job);
				}
			}	
		}catch(Exception ex) {
			log.error("Failed adding Jobs List for JobDao:: ",ex);
		}		
	}	
}