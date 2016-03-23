package com.dentist.domain;
/**
* 
*
* @author  Satyanandana Srikanthvarma Vadapalli
* @email srikanthvarma.vadapalli@gmail.com
* @version 1.0
* @since   Mar 17, 20161:10:28 AM
*       
*/
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@Transient
	private long patientID;
	@JsonIgnore
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
	private LocalDate dateOfBirth;
	@Enumerated(EnumType.STRING)
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
    
	@JsonGetter
	public long getPatientID() {
		if(this.insurancePatient!=null){
			this.patientID = this.insurancePatient.getUserID();
		}
		return patientID;
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

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (insuranceID ^ (insuranceID >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Insurance other = (Insurance) obj;
		if (insuranceID != other.insuranceID)
			return false;
		return true;
	}
	
	
		
}
