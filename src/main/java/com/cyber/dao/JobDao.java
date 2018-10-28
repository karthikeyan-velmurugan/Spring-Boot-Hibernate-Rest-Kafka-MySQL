/**
 * 
 */
package com.cyber.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
@SuppressWarnings("unchecked")
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

	@Override
	public List<Job> findJobByType(String type) {
		log.info("Entered findJobByType for JobDao::");
		List<Job> list=null;
		try {
			list=getSession().createQuery("from Job where jobType=?").setParameter(0, type).list();
		}catch(Exception ex) {
			log.error("Getting Job List Failed for JobDao:: ",ex);
		}
		return (list!=null && list.size()>0)?list:null;
	}

	@Override
	public List<Job> findJobByExp(String exp) {
		log.info("Entered findJobByExp for JobDao::");
		List<Job> list=null;
		try {
			list=getSession().createQuery("from Job where experience=?").setParameter(0, exp).list();
		}catch(Exception ex) {
			log.error("Getting Job List Failed for JobDao:: ",ex);
		}
		return (list!=null && list.size()>0)?list:null;	}

	@Override
	public List<Job> findJobByCountry(String country) {
		log.info("Entered findJobByCountry for JobDao::");
		List<Job> list=null;
		try {
			list=getSession().createQuery("from Job where country=?").setParameter(0, country).list();
		}catch(Exception ex) {
			log.error("Getting Job List Failed for JobDao:: ",ex);
		}
		return (list!=null && list.size()>0)?list:null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Job> findJobByAvailability(String[] availabiltyList) {
		log.info("AvailabiltyList for Job DAO :::: "+availabiltyList);
		List<Job> list=null;
		try {			
			list=getSession().createCriteria(Job.class).add(Restrictions.in("availability", availabiltyList)).list();
		}catch(Exception ex) {
			log.error("Getting Job List Failed for JobDao:: ",ex);
		}
		return (list!=null && list.size()>0)?list:null;
	}

	@Override
	public List<Job> findJobByPayRate(int low, int high) {
		log.info("AvailabiltyList for Job DAO :::: "+low + " --- " +high);
		List<Job> list = null;
		try {
			@SuppressWarnings("deprecation")
			Criteria criteria = getSession().createCriteria(Job.class);
			criteria.add(Restrictions.between("payRate", low, high));
			list = criteria.list();
		}catch(Exception ex) {
			log.error("Getting Job List Failed for JobDao:: ",ex);
		}
		return (list!=null && list.size()>0)?list:null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Job> findJobBySkill(String skill) {
		log.info("AvailabiltyList for Job DAO :::: "+skill);
		List<Job> list=null;
		try {			
			list=getSession().createCriteria(Job.class).add(Restrictions.like("skills", "%"+skill+"%")).list();
		}catch(Exception ex) {
			log.error("Getting Job List Failed for JobDao:: ",ex);
		}
		return (list!=null && list.size()>0)?list:null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Job> findJobByLang(String lang) {
		log.info("AvailabiltyList for Job DAO :::: "+lang);
		List<Job> list=null;
		try {			
			list=getSession().createCriteria(Job.class).add(Restrictions.like("language", "%"+lang+"%")).list();
		}catch(Exception ex) {
			log.error("Getting Job List Failed for JobDao:: ",ex);
		}
		return (list!=null && list.size()>0)?list:null;
	}
}