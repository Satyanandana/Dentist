package com.dentist.service;
/**
* 
*
* @author  Satyanandana Srikanthvarma Vadapalli
* @email srikanthvarma.vadapalli@gmail.com
* @version 1.0
* @since   Mar 17, 20161:10:28 AM
*       
*/
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.dentist.domain.Appointment;
import com.dentist.domain.AppointmentRequest;
import com.dentist.domain.Insurance;
import com.dentist.domain.Patient;
import com.dentist.domain.PatientTeethStatus;
import com.dentist.domain.ReceivedMessage;
import com.dentist.domain.SentMessage;
import com.dentist.domain.Teeth;
import com.dentist.domain.Treatment;
import com.dentist.domain.UserAuthentication;

public interface UserServiceInterface {
	
		
	/* Utility service method to handle signup and return Patient object*/
	
	public Patient signUp(Patient patient,HttpServletRequest request,Model model);
	
	/* Call DAO methods on UserAuthentication.class */
	
	public void setUserAuthenticationInfo(UserAuthentication userAuthentication);
	public void updateUserAuthenticationInfo(UserAuthentication userAuthentication);
	public UserAuthentication getUserAuthenticationInfoById(long id);
	public UserAuthentication getUserAuthenticationInfoByEmail(String email);
	
	/* Call DAO methods on Patient.class */
	public void setPatient(Patient patient);
	public void updatePatient(Patient patient);
	public Patient getPatientInfoById(long patientID);
	public Patient getPatientInfoByEmail(String patientEmail);
	
	/* Call DAO methods on AppointmentRequest.class */
	public void setAppointmentRequest(AppointmentRequest request);
	public void updateAppointmentRequest(AppointmentRequest request);
	public AppointmentRequest getAppointmentRequestByID(long appointmentRequestID);
	public List<AppointmentRequest> getAppointmentRequestsByPatientID(long patientID);
	
	/* Call DAO methods on Appointment.class */
	public void setAppointment(Appointment appointment);
	public void updateAppointment(Appointment appointment);
	public Appointment getAppointmentByID(long appointmentID);
	public List<Appointment> getAppointmentsByPatientID(long patientID);
	
	/* Call DAO methods on Insurance.class */
	public void setInsurance(Insurance insurance);
	public void updateInsurance(Insurance insurance);
	public Insurance getInsuranceByID(long insuranceID);
	public List<Insurance> getInsurancesByPatientID(long patientID);
	
	/* Call DAO methods on SentMessage.class */
	public void setSentMessage(SentMessage sentMessage);
	public void updateSentMessage(SentMessage sentMessage);
	public SentMessage getSentMessageByID(long sentMessageID);
	public List<SentMessage> getSentMessagesByPatientID(long patientID);
	
	/* Call DAO methods on ReceivedMessage.class */
	public void setReceivedMessage(ReceivedMessage receivedMessage);
	public void updateReceivedMessage(ReceivedMessage receivedMessage);
	public ReceivedMessage getReceivedMessageByID(long receivedMessageID);
	public List<ReceivedMessage> getReceivedMessagesByPatientID(long patientID);

	/* DAO methods on Treatment.class */
	public void setTreatment(Treatment treatment);
	public void updateTreatment(Treatment treatment);
	public Treatment getTreatmentByID(long treatmentID);
	public List<Treatment> getTreatmentsByPatientID(long userID);
	public List<Treatment> getTreatmentsByPatientIDandTeethID(long patientID,int teethID);

	/* DAO methods on PatientTeethStatus.class */
	public void setPatientTeethStatus(PatientTeethStatus patientTeethStatus);
	public void updatePatientTeethStatus(PatientTeethStatus patientTeethStatus);
	public PatientTeethStatus getPatientTeethStatusByPatientIDandTeethID(long patientID,int teethID );
	public List<PatientTeethStatus> getPatientTeethStatusByPatientID(long patientID);
	public Map<Integer, String> getPatientTeethStatusMapByPatientID(long patientID);
	
	/* DAO methods on Teeth.class */
	public Teeth getTeethByID(int teethID);
	
}
