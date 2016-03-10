package com.dentist.dao;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dentist.domain.Appointment;
import com.dentist.domain.AppointmentRequest;
import com.dentist.domain.Insurance;
import com.dentist.domain.Patient;
import com.dentist.domain.ReceivedMessage;
import com.dentist.domain.SentMessage;
import com.dentist.domain.UserAuthentication;

@Repository
public class UserDaoImplementaion extends DbDao implements UserDaoInterface {

/* 
 * DAO methods on UserAuthentication.class
 */
	
	public void setUserAuthenticationInfo(UserAuthentication userAuthentication) {
		persist(userAuthentication);
	}
	
	public void updateUserAuthenticationInfo(UserAuthentication userAuthentication) {
		update(userAuthentication);
	}

	public UserAuthentication getUserAuthenticationInfoById(long id) {
		Criteria criteria = getSession().createCriteria(UserAuthentication.class).add(Restrictions.idEq(id));
		return (UserAuthentication) criteria.uniqueResult();
	}

	public UserAuthentication getUserAuthenticationInfoByEmail(String email) {
		Criteria criteria = getSession().createCriteria(UserAuthentication.class)
				.add(Restrictions.eq("userEmail", email));
		return (UserAuthentication) criteria.uniqueResult();
	}

/*
 *  DAO methods on Patient.class 
 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void setPatient(Patient patient) {
		persist(patient);
	}
	
	public void updatePatient(Patient patient) {
		update(patient);
	}

	public Patient getPatientInfoById(long patientID) {
		Criteria criteria = getSession().createCriteria(Patient.class).add(Restrictions.idEq(patientID));
		return (Patient) criteria.uniqueResult();
	}

	public Patient getPatientInfoByEmail(String patientEmail) {
		Criteria criteria = getSession().createCriteria(Patient.class).add(Restrictions.eq("email", patientEmail));
		return (Patient) criteria.uniqueResult();
	}

/*
 *  DAO methods on AppointmentRequest.class
 */
	public void setAppointmentRequest(AppointmentRequest request) {
		persist(request);		
	}
	
	public void updateAppointmentRequest(AppointmentRequest request) {
		update(request);		
	}

	public AppointmentRequest getAppointmentRequestByID(long appointmentRequestID) {
		Criteria criteria = getSession().createCriteria(AppointmentRequest.class).add(Restrictions.idEq(appointmentRequestID));
		return (AppointmentRequest) criteria.uniqueResult();		
	}

	@SuppressWarnings("unchecked")
	public List<AppointmentRequest> getAppointmentRequestsByPatientID(long patientID) {
		Criteria criteria = getSession().createCriteria(AppointmentRequest.class).add(Restrictions.eq("appointmentPatient.userID", patientID));
		return   (List<AppointmentRequest>) criteria.list();
	}
	
/* 
 * DAO methods on Appointment.class 
 */

	public void setAppointment(Appointment appointment) {
		persist(appointment);
	}
	
	public void updateAppointment(Appointment appointment) {
		update(appointment);
	}

	public Appointment getAppointmentByID(long appointmentID) {
		Criteria criteria = getSession().createCriteria(Appointment.class).add(Restrictions.idEq(appointmentID));
		return (Appointment) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Appointment> getAppointmentsByPatientID(long patientID) {
		Criteria criteria = getSession().createCriteria(Appointment.class).add(Restrictions.eq("appointmentPatient.userID", patientID));
		return   (List<Appointment>) criteria.list();
	}

/*
 *  DAO methods on Insurance.class 
 */
	public void setInsurance(Insurance insurance) {
		persist(insurance);
	}
	
	public void updateInsurance(Insurance insurance) {
		update(insurance);
	}

	public Insurance getInsuranceByID(long insuranceID) {
		Criteria criteria = getSession().createCriteria(Insurance.class).add(Restrictions.idEq(insuranceID));
		return (Insurance) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Insurance> getInsurancesByPatientID(long patientID) {
		Criteria criteria = getSession().createCriteria(Insurance.class).add(Restrictions.eq("insurancePatient.userID", patientID));
		return   (List<Insurance>) criteria.list();
	}
	
/*
 *  DAO methods on SentMessage.class 
 */

	public void setSentMessage(SentMessage sentMessage) {
		persist(sentMessage);		
	}
	
	public void updateSentMessage(SentMessage sentMessage) {
		update(sentMessage);		
	}

	public SentMessage getSentMessageByID(long sentMessageID) {
		Criteria criteria = getSession().createCriteria(SentMessage.class).add(Restrictions.idEq(sentMessageID));
		return (SentMessage) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<SentMessage> getSentMessagesByPatientID(long patientID) {
		Criteria criteria = getSession().createCriteria(SentMessage.class).add(Restrictions.eq("sender.userID", patientID));
		return   (List<SentMessage>) criteria.list();
	}

/*
 *  DAO methods on SentMessage.class 
 */
	public void setReceivedMessage(ReceivedMessage receivedMessage) {
		persist(receivedMessage);
	}
	
	public void updateReceivedMessage(ReceivedMessage receivedMessage) {
		update(receivedMessage);
	}

	public ReceivedMessage getReceivedMessageByID(long receivedMessageID) {
		Criteria criteria = getSession().createCriteria(ReceivedMessage.class).add(Restrictions.idEq(receivedMessageID));
		return (ReceivedMessage) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<ReceivedMessage> getReceivedMessagesByPatientID(long patientID) {
		Criteria criteria = getSession().createCriteria(ReceivedMessage.class).add(Restrictions.eq("receiver.userID", patientID));
		return   (List<ReceivedMessage>) criteria.list();
	}

	

	public Object mergeEntity(Object entity) {
		
		return merge(entity);
	}

	public Session getHibernateSession() {
		
		return getSession();
	}


	

}
