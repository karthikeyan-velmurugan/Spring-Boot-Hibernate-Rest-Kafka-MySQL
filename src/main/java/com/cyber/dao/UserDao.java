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

import com.cyber.domain.UserInfo;

/**
 * @author Karthikeyan
 *
 */
@Repository
@Transactional
public class UserDao implements IUserDao{

	private static final Logger log=LogManager.getLogger(UserDao.class);

	@Autowired
	private SessionFactory sessionFactory; 

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public int createUser(UserInfo userInfo) {
		log.info("Entered createUser for UserDao::");
		int id=0;
		try {
			id=(int) getSession().save(userInfo);
			log.info("User Saved SuccessFully for UserDao.");
		}catch(Exception ex) {
			log.error("UserInfo Registration Failed for UserDao :: ",ex);
		}
		return id;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserInfo findById(int id) {
		log.info("Entered findById for UserDao::");
		List<UserInfo> list=null;
		try {
			list=getSession().createQuery("from UserInfo where userId=?").setParameter(0, id).list();
		}catch(Exception ex) {
			log.error("Getting UserInfo Failed for UserDao:: ",ex);
		}
		return (list!=null && list.size()>0)?list.get(0):null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserInfo findByName(String name) {
		log.info("Entered findByName for UserDao::");
		List<UserInfo> list=null;
		try {
			list=getSession().createQuery("from UserInfo where userName=?").setParameter(0, name).list();
		}catch(Exception ex) {
			log.error("Getting UserInfo by Name Failed for UserDao:: ",ex);
		}
		return (list!=null && list.size()>0)?list.get(0):null;
	}
}