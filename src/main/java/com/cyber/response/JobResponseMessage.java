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
@XmlRootElement(name="job")
public class JobResponseMessage<T> {

	@XmlElement(name="status")
	private ResponseStatus status;

	@XmlElement(name="jobEntity")
	private T entity;

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public JobResponseMessage() {
		super();
	}

	public JobResponseMessage(ResponseStatus status, T entity) {
		super();
		this.status = status;
		this.entity = entity;
	}

	public JobResponseMessage(T entity) {
		super();
		setEntity(entity);
	}	
}