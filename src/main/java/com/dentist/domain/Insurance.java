package com.dentist.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name = "insurances")
public class Insurance implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4296479547234321823L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long insuranceID;
	@ManyToOne
	@JoinColumn(name = "patientID",nullable=false)
	private Patient insurancePatient;
	@Column(nullable = false)
	private long insuranceProviderID;
	@Column(nullable = false)
	private String insuranceProviderName;
	@Column(nullable = false)
	private long subscriberID;
	@Column
	private String subscriberFullName;
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private DateTime dateOfBirth;
	@Enumerated
	private InsuranceStatus status;
	@Column(nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime insertedDate;
	
	public Insurance() {

	}

	public long getInsuranceID() {
		return insuranceID;
	}

	public void setInsuranceID(long insuranceID) {
		this.insuranceID = insuranceID;
	}

	public Patient getInsurancePatient() {
		return insurancePatient;
	}

	public void setInsurancePatient(Patient insurancePatient) {
		this.insurancePatient = insurancePatient;
	}

	public long getInsuranceProviderID() {
		return insuranceProviderID;
	}

	public void setInsuranceProviderID(long insuranceProviderID) {
		this.insuranceProviderID = insuranceProviderID;
	}

	public String getInsuranceProviderName() {
		return insuranceProviderName;
	}

	public void setInsuranceProviderName(String insuranceProviderName) {
		this.insuranceProviderName = insuranceProviderName;
	}

	public long getSubscriberID() {
		return subscriberID;
	}

	public void setSubscriberID(long subscriberID) {
		this.subscriberID = subscriberID;
	}

	public String getSubscriberFullName() {
		return subscriberFullName;
	}

	public void setSubscriberFullName(String subscriberFullName) {
		this.subscriberFullName = subscriberFullName;
	}

	public DateTime getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(DateTime dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public InsuranceStatus getStatus() {
		return status;
	}

	public void setStatus(InsuranceStatus status) {
		this.status = status;
	}

	public DateTime getInsertedDate() {
		return insertedDate;
	}

	public void setInsertedDate(DateTime insertedDate) {
		this.insertedDate = insertedDate;
	}
		
}
