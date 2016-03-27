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

import javax.servlet.http.HttpServletRequest;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.dentist.dao.UserDaoInterface;
import com.dentist.domain.AccountStatus;
import com.dentist.domain.Appointment;
import com.dentist.domain.AppointmentRequest;
import com.dentist.domain.Insurance;
import com.dentist.domain.Patient;
import com.dentist.domain.ReceivedMessage;
import com.dentist.domain.Role;
import com.dentist.domain.SentMessage;
import com.dentist.domain.Treatment;
import com.dentist.domain.UserAuthentication;

@Service
@Transactional
public class UserServiceImpl implements UserServiceInterface {
	@Autowired
	private UserDaoInterface userDaoInterface;
	@Autowired
	private PooledPBEStringEncryptor encryptor;

/* 
 * DAO methods on UserAuthentication.class
 */
	public void setUserAuthenticationInfo(UserAuthentication userAuthentication) {
		userDaoInterface.setUserAuthenticationInfo(userAuthentication);		
	}

	public void updateUserAuthenticationInfo(UserAuthentication userAuthentication) {
		userDaoInterface.updateUserAuthenticationInfo(userAuthentication);
	}

	public UserAuthentication getUserAuthenticationInfoById(long id) {
			return userDaoInterface.getUserAuthenticationInfoById(id);
	}

	public UserAuthentication getUserAuthenticationInfoByEmail(String email) {
			return userDaoInterface.getUserAuthenticationInfoByEmail(email);
	}

/* 
 * DAO methods on Patient.class
 */
	public void setPatient(Patient patient) {
		userDaoInterface.setPatient(patient);
	}

	public void updatePatient(Patient patient) {
		userDaoInterface.updatePatient(patient);
	}

	public Patient getPatientInfoById(long patientID) {
		Patient patient =  userDaoInterface.getPatientInfoById(patientID);
		
		return patient;
	}

	public Patient getPatientInfoByEmail(String patientEmail) {
		return userDaoInterface.getPatientInfoByEmail(patientEmail);
	}

/* 
 * DAO methods on AppointmentRequest.class
 */
	public void setAppointmentRequest(AppointmentRequest request) {
		userDaoInterface.setAppointmentRequest(request);
	}

	public void updateAppointmentRequest(AppointmentRequest request) {
		userDaoInterface.updateAppointmentRequest(request);
	}

	public AppointmentRequest getAppointmentRequestByID(long appointmentRequestID) {
		return userDaoInterface.getAppointmentRequestByID(appointmentRequestID);
	}

	public List<AppointmentRequest> getAppointmentRequestsByPatientID(long patientID) {
		return userDaoInterface.getAppointmentRequestsByPatientID(patientID);
	}

/* 
 * DAO methods on Appointment.class
 */
	public void setAppointment(Appointment appointment) {
		userDaoInterface.setAppointment(appointment);
	}

	public void updateAppointment(Appointment appointment) {
		userDaoInterface.updateAppointment(appointment);
	}

	public Appointment getAppointmentByID(long appointmentID) {
		return userDaoInterface.getAppointmentByID(appointmentID);
	}

	public List<Appointment> getAppointmentsByPatientID(long patientID) {
		return userDaoInterface.getAppointmentsByPatientID(patientID);
	}

/* 
 * DAO methods on Insurance.class
 */
	public void setInsurance(Insurance insurance) {
		userDaoInterface.setInsurance(insurance);
	}

	public void updateInsurance(Insurance insurance) {
		userDaoInterface.updateInsurance(insurance);
	}

	public Insurance getInsuranceByID(long insuranceID) {
		return userDaoInterface.getInsuranceByID(insuranceID);
	}

	public List<Insurance> getInsurancesByPatientID(long patientID) {
		return userDaoInterface.getInsurancesByPatientID(patientID);
	}
	
/* 
 * DAO methods on SentMessage.class
 */

	public void setSentMessage(SentMessage sentMessage) {
		userDaoInterface.setSentMessage(sentMessage);
	}

	public void updateSentMessage(SentMessage sentMessage) {
		userDaoInterface.updateSentMessage(sentMessage);
	}

	public SentMessage getSentMessageByID(long sentMessageID) {
		return userDaoInterface.getSentMessageByID(sentMessageID);
	}

	public List<SentMessage> getSentMessagesByPatientID(long patientID) {
		return userDaoInterface.getSentMessagesByPatientID(patientID);
	}

/* 
 * DAO methods on ReceivedMessage.class
 */
	public void setReceivedMessage(ReceivedMessage receivedMessage) {
		userDaoInterface.setReceivedMessage(receivedMessage);
	}

	public void updateReceivedMessage(ReceivedMessage receivedMessage) {
		userDaoInterface.updateReceivedMessage(receivedMessage);
	}

	public ReceivedMessage getReceivedMessageByID(long receivedMessageID) {
		return userDaoInterface.getReceivedMessageByID(receivedMessageID);
	}

	public List<ReceivedMessage> getReceivedMessagesByPatientID(long patientID) {
		return userDaoInterface.getReceivedMessagesByPatientID(patientID);
	}
	

/* 
 * DAO methods on Treatment.class
 */
	@Override
	public void setTreatment(Treatment treatment) {
		 userDaoInterface.setTreatment(treatment);
	}

	@Override
	public void updateTreatment(Treatment treatment) {
		userDaoInterface.updateTreatment(treatment);
	}

	@Override
	public Treatment getTreatmentByID(long treatmentID) {
		return userDaoInterface.getTreatmentByID(treatmentID);
	}
	
	@Override
	public List<Treatment> getTreatmentsByPatientID(long userID) {
		return  userDaoInterface.getTreatmentsByPatientID(userID);
	}

	@Override
	public List<Treatment> getTreatmentsByPatientIDandTeethID(long patientID, int teethID) {
	    return userDaoInterface.getTreatmentsByPatientIDandTeethID(patientID, teethID);
	}

	

	public Patient signUp(Patient patient,HttpServletRequest request,Model model) {
		Patient patientCreated = null;
		UserAuthentication userAuth= getUserAuthenticationInfoByEmail(patient.getUserAuth().getUserEmail());
		if(userAuth == null){
			userAuth = patient.getUserAuth();
			userAuth.setAccountStatus(AccountStatus.NOT_ACTIVATED_YET);
			userAuth.setCreationTime(new DateTime());
			userAuth.setLastLoginTime(new DateTime());
			userAuth.setUserRole(Role.ROLE_USER);
			 String ipAddress = WebUtility.getIpAddress(request);
			userAuth.setUserIp(ipAddress);
			String verifyKey = encryptor.encrypt(userAuth.getUserEmail()+userAuth.getCreationTime());
			userAuth.setVerifyKey(verifyKey);
			
			
			patient.setEmail(userAuth.getUserEmail());
			patient.setUserAuth(userAuth);
			patientCreated = patient;
			
		}else{
			model.addAttribute("error", "Another account exists with this email.Try another email");
		}
		return patientCreated;
	}

}
