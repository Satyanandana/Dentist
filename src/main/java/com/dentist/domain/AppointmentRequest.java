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
@Table(name = "appointmentrequests")
public class AppointmentRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4417500461064293514L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long appointmentRequestID;
	@Transient
	private long patientID;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "patientID", nullable = false, updatable = false)
	private Patient appointmentPatient;
	@Column(nullable = false, length = 6000)
	@Type(type = "encryptedString")
	private String note;
	@Column(nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime requestInsertedTime;
	@Column(nullable = false)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime appointmentStartTime;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private AppointmentRequestStatus status;
	@OneToOne
	@JoinColumn(name = "appointmentID", nullable = true)
	private Appointment appointment;

	public AppointmentRequest() {

	}

	public long getAppointmentRequestID() {
		return appointmentRequestID;
	}

	public void setAppointmentRequestID(long appointmentRequestID) {
		this.appointmentRequestID = appointmentRequestID;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public DateTime getRequestInsertedTime() {
		return requestInsertedTime;
	}

	public void setRequestInsertedTime(DateTime requestInsertedTime) {
		this.requestInsertedTime = requestInsertedTime;
	}

	public DateTime getAppointmentStartTime() {
		return appointmentStartTime;
	}

	public void setAppointmentStartTime(DateTime appointmentStartTime) {
		this.appointmentStartTime = appointmentStartTime;
	}

	public AppointmentRequestStatus getStatus() {
		return status;
	}

	public void setStatus(AppointmentRequestStatus status) {
		this.status = status;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (appointmentRequestID ^ (appointmentRequestID >>> 32));
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
		AppointmentRequest other = (AppointmentRequest) obj;
		if (appointmentRequestID != other.appointmentRequestID)
			return false;
		return true;
	}

}
