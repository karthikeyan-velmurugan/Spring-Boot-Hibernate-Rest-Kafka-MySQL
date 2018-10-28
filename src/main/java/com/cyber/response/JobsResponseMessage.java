/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cyber.response;

import java.util.List;

/**
 *
 * @author Karthikeyan
 */

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.cyber.domain.Job;
/**
 * @author Karthikeyan
 *
 */
@XmlRootElement(name = "job")
public class JobsResponseMessage {
	@XmlElement(name = "status")
	public ResponseStatus status;

	private List<Job> entities;

	public JobsResponseMessage() {
		super();
	}

	public JobsResponseMessage(ResponseStatus status,
			List<Job> entities) {
		super();
		this.status = status;
		this.entities = entities;
	}

	@XmlElement(name = "job")
	public List<Job> getJobsMessage() {
		return entities;
	}

}
