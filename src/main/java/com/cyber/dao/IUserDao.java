/**
 * 
 */
package com.cyber.dao;

import com.cyber.domain.UserInfo;

/**
 * @author Karthikeyan
 *
 */
public interface IUserDao {

	public int createUser(UserInfo userInfo);

	public UserInfo findById(int id);

	public UserInfo findByName(String name);
}