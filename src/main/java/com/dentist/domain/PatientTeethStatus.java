package com.dentist.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table
public class PatientTeethStatus implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7658490316069224796L;
	
	@EmbeddedId
	private TeethStatusPK TeethStatusPK;
	@Column(nullable=false)
	@Enumerated
	private TeethStatus teethStatus;
	@Column(nullable=false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime lastModified;
	
	public PatientTeethStatus() {
		
	}

	public TeethStatusPK getTeethStatusPK() {
		return TeethStatusPK;
	}

	public void setTeethStatusPK(TeethStatusPK teethStatusPK) {
		TeethStatusPK = teethStatusPK;
	}

	public TeethStatus getTeethStatus() {
		return teethStatus;
	}

	public void setTeethStatus(TeethStatus teethStatus) {
		this.teethStatus = teethStatus;
	}

	public DateTime getLastModified() {
		return lastModified;
	}

	public void setLastModified(DateTime lastModified) {
		this.lastModified = lastModified;
	}
	
	

}
