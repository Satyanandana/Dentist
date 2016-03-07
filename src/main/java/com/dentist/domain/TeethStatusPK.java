package com.dentist.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class TeethStatusPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1955230820541889286L;
	
	@OneToOne
	@JoinColumn(name="patientID",nullable=false)
	private Patient patient;
	@OneToOne
	@JoinColumn(name="teethID",nullable=false)
	private Teeth teeth;
	
	public TeethStatusPK() {
		
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Teeth getTeeth() {
		return teeth;
	}

	public void setTeeth(Teeth teeth) {
		this.teeth = teeth;
	}
	
}
