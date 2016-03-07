package com.dentist.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Teeth implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6175288134531677286L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int teethID;
	@Column(nullable=false)
	private String teethName;
	@Column
	private String Description;
	
	public Teeth() {
		
	}

	public int getTeethID() {
		return teethID;
	}

	public void setTeethID(int teethID) {
		this.teethID = teethID;
	}

	public String getTeethName() {
		return teethName;
	}

	public void setTeethName(String teethName) {
		this.teethName = teethName;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}
	
	
	
	
	
	

}
