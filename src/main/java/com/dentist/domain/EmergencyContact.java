package com.dentist.domain;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmergencyContact implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3266512225087243884L;
	
	@Column
	private String name;
	@Column
	private long phoneNumber;
	@Column
	private String relation;
	
	public EmergencyContact() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long number) {
		this.phoneNumber = number;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}
		
	
}
