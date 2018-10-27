/**
 * 
 */
package com.cyber.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Karthikeyan
 *
 */
@XmlRootElement(name="userInfo")
public class UserResponseMessage<T> {

	@XmlElement(name="status")
	private ResponseStatus status;

	@XmlElement(name="userInfoEntity")
	private T entity;

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public UserResponseMessage() {
		super();
	}

	public UserResponseMessage(ResponseStatus status, T entity) {
		super();
		this.status = status;
		this.entity = entity;
	}

	public UserResponseMessage(T entity) {
		super();
		setEntity(entity);
	}	
}