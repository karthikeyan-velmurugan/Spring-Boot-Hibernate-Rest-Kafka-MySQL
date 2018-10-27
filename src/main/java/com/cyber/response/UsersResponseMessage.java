/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cyber.response;

/**
 *
 * @author Karthikeyan
 */

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.Map;

@XmlRootElement(name = "userInfo")
public class UsersResponseMessage {
	@XmlElement(name = "status")
	public ResponseStatus status;

	private Iterable<Map<String, Object>> entities;

	public UsersResponseMessage() {
		super();
	}

	public UsersResponseMessage(ResponseStatus status,
			Iterable<Map<String, Object>> entities) {
		super();
		this.status = status;
		this.entities = entities;
	}

	@XmlElement(name = "userInfo")
	public Iterable<Map<String, Object>> getUsersMessage() {
		return entities;
	}

}
