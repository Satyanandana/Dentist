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
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
@Scope("prototype")
@Entity
@Table(name = "appointments")
public class Appointment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8124924629297771668L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long appointmentID;
	@Transient
	private long patientID;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "patientID", nullable = false, updatable = false)
	private Patient appointmentPatient;
	@OneToOne
	@JoinColumn(name = "appointmentRequestID", nullable = false, updatable = false)
	private AppointmentRequest AppointmentRequest;
	@Column(nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime appointmentInsertedTime;
	@Column(nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime appointmentStartTime;
	@Column(nullable = false)
	private String fakeCalEventID;
	@Column(nullable = false)
	private String actualCalEventID;
	@Column(nullable = false, length = 6000)
	@Type(type = "encryptedString")
	private String note;
	@Column
	private BigDecimal expectedAmount;
	@Column
	private BigDecimal amountPaid;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private AppointmentStatus status;

	public Appointment() {

	}

	public long getAppointmentID() {
		return appointmentID;
	}

	public void setAppointmentID(long appointmentID) {
		this.appointmentID = appointmentID;
	}

	@JsonGetter
	public long getPatientID() {
		if (this.appointmentPatient != null) {
			this.patientID = this.appointmentPatient.getUserID();
		}
		return patientID;
	}

	public Patient getAppointmentPatient() {
		return appointmentPatient;
	}

	public void setAppointmentPatient(Patient appointmentPatient) {
		this.appointmentPatient = appointmentPatient;
	}

	public AppointmentRequest getAppointmentRequest() {
		return AppointmentRequest;
	}

	public void setAppointmentRequest(AppointmentRequest appointmentRequest) {
		AppointmentRequest = appointmentRequest;
	}

	public DateTime getAppointmentInsertedTime() {
		return appointmentInsertedTime;
	}

	public void setAppointmentInsertedTime(DateTime appointmentInsertedTime) {
		this.appointmentInsertedTime = appointmentInsertedTime;
	}

	public DateTime getAppointmentStartTime() {
		return appointmentStartTime;
	}

	public void setAppointmentStartTime(DateTime appointmentStartTime) {
		this.appointmentStartTime = appointmentStartTime;
	}

	public String getFakeCalEventID() {
		return fakeCalEventID;
	}

	public void setFakeCalEventID(String fakeCalEventID) {
		this.fakeCalEventID = fakeCalEventID;
	}

	public String getActualCalEventID() {
		return actualCalEventID;
	}

	public void setActualCalEventID(String actualCalEventID) {
		this.actualCalEventID = actualCalEventID;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public BigDecimal getExpectedAmount() {
		return expectedAmount;
	}

	public void setExpectedAmount(BigDecimal expectedAmount) {
		this.expectedAmount = expectedAmount;
	}

	public BigDecimal getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(BigDecimal amountPaid) {
		this.amountPaid = amountPaid;
	}

	public AppointmentStatus getStatus() {
		return status;
	}

	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (appointmentID ^ (appointmentID >>> 32));
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
		Appointment other = (Appointment) obj;
		if (appointmentID != other.appointmentID)
			return false;
		return true;
	}

}
