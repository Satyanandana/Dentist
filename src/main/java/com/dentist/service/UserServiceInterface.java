package com.dentist.service;

import java.util.List;

import com.dentist.domain.Appointment;
import com.dentist.domain.AppointmentRequest;
import com.dentist.domain.Insurance;
import com.dentist.domain.Patient;
import com.dentist.domain.ReceivedMessage;
import com.dentist.domain.SentMessage;
import com.dentist.domain.UserAuthentication;

public interface UserServiceInterface {

	/* DAO methods on UserAuthentication.class */
	public void setUserAuthenticationInfo(UserAuthentication userAuthentication);
	public void updateUserAuthenticationInfo(UserAuthentication userAuthentication);
	public UserAuthentication getUserAuthenticationInfoById(long id);
	public UserAuthentication getUserAuthenticationInfoByEmail(String email);
	
	/* DAO methods on Patient.class */
	public void setPatient(Patient patient);
	public void updatePatient(Patient patient);
	public Patient getPatientInfoById(long patientID);
	public Patient getPatientInfoByEmail(String patientEmail);
	
	/* DAO methods on AppointmentRequest.class */
	public void setAppointmentRequest(AppointmentRequest request);
	public void updateAppointmentRequest(AppointmentRequest request);
	public AppointmentRequest getAppointmentRequestByID(long appointmentRequestID);
	public List<AppointmentRequest> getAppointmentRequestsByPatientID(long patientID);
	
	/* DAO methods on Appointment.class */
	public void setAppointment(Appointment appointment);
	public void updateAppointment(Appointment appointment);
	public Appointment getAppointmentByID(long appointmentID);
	public List<Appointment> getAppointmentsByPatientID(long patientID);
	
	/* DAO methods on Insurance.class */
	public void setInsurance(Insurance insurance);
	public void updateInsurance(Insurance insurance);
	public Insurance getInsuranceByID(long insuranceID);
	public List<Insurance> getInsurancesByPatientID(long patientID);
	
	/* DAO methods on SentMessage.class */
	public void setSentMessage(SentMessage sentMessage);
	public void updateSentMessage(SentMessage sentMessage);
	public SentMessage getSentMessageByID(long sentMessageID);
	public List<SentMessage> getSentMessagesByPatientID(long patientID);
	
	/* DAO methods on ReceivedMessage.class */
	public void setReceivedMessage(ReceivedMessage receivedMessage);
	public void updateReceivedMessage(ReceivedMessage receivedMessage);
	public ReceivedMessage getReceivedMessageByID(long receivedMessageID);
	public List<ReceivedMessage> getReceivedMessagesByPatientID(long patientID);

}
