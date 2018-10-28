/**
 * 
 */
package com.cyber.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cyber.domain.UserInfo;
import com.cyber.helper.UserHelper;

/**
 * @author Karthikeyan
 *
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

	private static final Logger log=LogManager.getLogger(UserController.class);

	@Autowired
	private UserHelper userHelper;

	@PostMapping("/createuser")
	public ResponseEntity<UserInfo> createUser(@RequestBody UserInfo userInfo, UriComponentsBuilder ucBuilder){		
		log.info("Create User..!!");
		int userId= userHelper.createUser(userInfo);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/getuser/{id}").buildAndExpand(userId).toUri());
		return new ResponseEntity<UserInfo>(headers, HttpStatus.CREATED);
	}	
}