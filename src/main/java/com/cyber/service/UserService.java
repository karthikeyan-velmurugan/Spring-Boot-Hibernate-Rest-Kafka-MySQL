/**
 * 
 */
package com.cyber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyber.dao.IUserDao;
import com.cyber.domain.UserInfo;

/**
 * @author Karthikeyan
 *
 */
@Service
public class UserService implements IUserService{

	@Autowired
	IUserDao iUserDao;

	@Override
	public int createUser(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return iUserDao.createUser(userInfo);
	}

	@Override
	public UserInfo findById(int id) {
		// TODO Auto-generated method stub
		return iUserDao.findById(id);
	}

	@Override
	public UserInfo findByName(String name) {
		return iUserDao.findByName(name);
	}	
}