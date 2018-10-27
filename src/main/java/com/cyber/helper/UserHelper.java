/**
 * 
 */
package com.cyber.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyber.domain.UserInfo;
import com.cyber.service.IUserService;

/**
 * @author Karthikeyan
 *
 */
@Service
public class UserHelper {

	private static final Logger log=LogManager.getLogger(UserHelper.class);

	@Autowired
	private IUserService iUserService;

	public int createUser(UserInfo userInfo){
		log.info("Create User for UserHelper..!!");
		int id=iUserService.createUser(userInfo);	
		return id;
	}

	public UserInfo getUserById(int id) {
		UserInfo userInfo = iUserService.findById(id);
		return userInfo;
	}

	public UserInfo getUserByName(String name) {
		UserInfo userInfo = iUserService.findByName(name);
		return userInfo;
	}	
}
