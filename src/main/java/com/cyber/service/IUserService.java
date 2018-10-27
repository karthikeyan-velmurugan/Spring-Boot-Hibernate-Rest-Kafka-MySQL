package com.cyber.service;

import com.cyber.domain.UserInfo;

/**
 * @author Karthikeyan
 *
 */
public interface IUserService {

	public int createUser(UserInfo userInfo);

	public UserInfo findById(int id);

	public UserInfo findByName(String name);
}