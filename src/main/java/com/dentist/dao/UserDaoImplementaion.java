package com.dentist.dao;

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

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

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

@Repository
public class UserDaoImplementaion extends DbDao implements UserDaoInterface {

	/*
	 * DAO methods on UserAuthentication.class
	 */

	@Override
	public void setUserAuthenticationInfo(UserAuthentication userAuthentication) {
		persist(userAuthentication);
	}

	@Override
	public void updateUserAuthenticationInfo(UserAuthentication userAuthentication) {
		update(userAuthentication);
	}

	@Override
	public UserAuthentication getUserAuthenticationInfoById(long id) {
		Criteria criteria = getSession().createCriteria(UserAuthentication.class).add(Restrictions.idEq(id));
		return (UserAuthentication) criteria.uniqueResult();
	}

	@Override
	public UserAuthentication getUserAuthenticationInfoByEmail(String email) {
		Criteria criteria = getSession().createCriteria(UserAuthentication.class).add(Restrictions.eq("userEmail", email));
		return (UserAuthentication) criteria.uniqueResult();
	}

	/*
	 * DAO methods on Patient.class
	 */

	@Override
	public void setPatient(Patient patient) {
		persist(patient);
	}

	@Override
	public void updatePatient(Patient patient) {
		update(patient);
	}

	@Override
	public Patient getPatientInfoById(long patientID) {
		Criteria criteria = getSession().createCriteria(Patient.class).add(Restrictions.idEq(patientID));
		return (Patient) criteria.uniqueResult();
	}

	@Override
	public Patient getBasicPatientDetails(long patientID) {

		Criteria criteria = getSession().createCriteria(Patient.class).add(Restrictions.idEq(patientID))
				.setProjection(Projections.projectionList().add(Projections.property("userID"), "userID")
						.add(Projections.property("firstName"), "firstName").add(Projections.property("lastName"), "lastName")
						.add(Projections.property("middleName"), "middleName").add(Projections.property("dateOfBirth"), "dateOfBirth")
						.add(Projections.property("phoneNumber"), "phoneNumber").add(Projections.property("email"), "email"))
				.setResultTransformer(Transformers.aliasToBean(Patient.class));

		return (Patient) criteria.uniqueResult();
	}

	@Override
	public Patient getPatientInfoByEmail(String patientEmail) {
		Criteria criteria = getSession().createCriteria(Patient.class).add(Restrictions.eq("email", patientEmail));
		return (Patient) criteria.uniqueResult();
	}

	/*
	 * DAO methods on AppointmentRequest.class
	 */
	@Override
	public void setAppointmentRequest(AppointmentRequest request) {
		persist(request);
	}

	@Override
	public void updateAppointmentRequest(AppointmentRequest request) {
		update(request);
	}

	@Override
	public AppointmentRequest getAppointmentRequestByID(long appointmentRequestID) {
		Criteria criteria = getSession().createCriteria(AppointmentRequest.class).add(Restrictions.idEq(appointmentRequestID));
		return (AppointmentRequest) criteria.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<AppointmentRequest> getAppointmentRequestsByPatientID(long patientID) {
		Criteria criteria = getSession().createCriteria(AppointmentRequest.class).add(Restrictions.eq("appointmentPatient.userID", patientID));
		return criteria.list();
	}

	@Override
	public AppointmentRequest getAppointmentRequestByIDandPatientID(long appointmentRequestID, long patientID) {
		Criteria criteria = getSession().createCriteria(AppointmentRequest.class).add(Restrictions.idEq(appointmentRequestID))
				.add(Restrictions.eq("appointmentPatient.userID", patientID));
		return (AppointmentRequest) criteria.uniqueResult();
	}

	/*
	 * DAO methods on Appointment.class
	 */

	@Override
	public void setAppointment(Appointment appointment) {
		persist(appointment);
	}

	@Override
	public void updateAppointment(Appointment appointment) {
		update(appointment);
	}

	@Override
	public Appointment getAppointmentByID(long appointmentID) {
		Criteria criteria = getSession().createCriteria(Appointment.class).add(Restrictions.idEq(appointmentID));
		return (Appointment) criteria.uniqueResult();
	}

	@Override
	public Appointment getAppointmentByIDandPatientID(long appointmentID, long patientID) {
		Criteria criteria = getSession().createCriteria(Appointment.class).add(Restrictions.idEq(appointmentID))
				.add(Restrictions.eq("appointmentPatient.userID", patientID));
		return (Appointment) criteria.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Appointment> getAppointmentsByPatientID(long patientID) {
		Criteria criteria = getSession().createCriteria(Appointment.class).add(Restrictions.eq("appointmentPatient.userID", patientID));
		return criteria.list();
	}

	/*
	 * DAO methods on Insurance.class
	 */
	@Override
	public void setInsurance(Insurance insurance) {
		persist(insurance);
	}

	@Override
	public void updateInsurance(Insurance insurance) {
		update(insurance);
	}

	@Override
	public Insurance getInsuranceByID(long insuranceID) {
		Criteria criteria = getSession().createCriteria(Insurance.class).add(Restrictions.idEq(insuranceID));
		return (Insurance) criteria.uniqueResult();
	}

	@Override
	public Insurance getInsuranceByIDandPatientID(long insuranceID, long patientID) {
		Criteria criteria = getSession().createCriteria(Insurance.class).add(Restrictions.idEq(insuranceID))
				.add(Restrictions.eq("insurancePatient.userID", patientID));
		return (Insurance) criteria.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Insurance> getInsurancesByPatientID(long patientID) {
		Criteria criteria = getSession().createCriteria(Insurance.class).add(Restrictions.eq("insurancePatient.userID", patientID));
		return criteria.list();
	}

	/*
	 * DAO methods on SentMessage.class
	 */

	@Override
	public void setSentMessage(SentMessage sentMessage) {
		persist(sentMessage);
	}

	@Override
	public void updateSentMessage(SentMessage sentMessage) {
		update(sentMessage);
	}

	@Override
	public SentMessage getSentMessageByID(long sentMessageID) {
		Criteria criteria = getSession().createCriteria(SentMessage.class).add(Restrictions.idEq(sentMessageID));
		return (SentMessage) criteria.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<SentMessage> getSentMessagesByPatientID(long patientID) {
		Criteria criteria = getSession().createCriteria(SentMessage.class).add(Restrictions.eq("sender.userID", patientID));
		return criteria.list();
	}

	/*
	 * DAO methods on ReceivedMessage.class
	 */
	@Override
	public void setReceivedMessage(ReceivedMessage receivedMessage) {
		persist(receivedMessage);
	}

	@Override
	public void updateReceivedMessage(ReceivedMessage receivedMessage) {
		update(receivedMessage);
	}

	@Override
	public ReceivedMessage getReceivedMessageByID(long receivedMessageID) {
		Criteria criteria = getSession().createCriteria(ReceivedMessage.class).add(Restrictions.idEq(receivedMessageID));
		return (ReceivedMessage) criteria.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ReceivedMessage> getReceivedMessagesByPatientID(long patientID) {
		Criteria criteria = getSession().createCriteria(ReceivedMessage.class).add(Restrictions.eq("receiver.userID", patientID));
		return criteria.list();
	}

	/*
	 * DAO methods on Treatment.class
	 */

	@Override
	public void setTreatment(Treatment treatment) {
		persist(treatment);
	}

	@Override
	public void updateTreatment(Treatment treatment) {
		update(treatment);
	}

	@Override
	public Treatment getTreatmentByID(long treatmentID) {
		Criteria criteria = getSession().createCriteria(Treatment.class).add(Restrictions.idEq(treatmentID));
		return (Treatment) criteria.uniqueResult();
	}

	@Override
	public Treatment getTreatmentByIDandPatientID(long treatmentID, long patientID) {
		Criteria criteria = getSession().createCriteria(Treatment.class).add(Restrictions.idEq(treatmentID))
				.add(Restrictions.eq("patient.userID", patientID));
		return (Treatment) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Treatment> getTreatmentsByPatientID(long patientID) {
		Criteria criteria = getSession().createCriteria(Treatment.class).add(Restrictions.eq("patient.userID", patientID));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Treatment> getTreatmentsByPatientIDandTeethID(long patientID, int teethID) {
		Criteria criteria = getSession().createCriteria(Treatment.class).add(Restrictions.eq("patient.userID", patientID))
				.add(Restrictions.eq("teeth.teethID", teethID));
		return criteria.list();
	}

	/*
	 * DAO methods on PatientTeethStatus.class
	 */
	@Override
	public void setPatientTeethStatus(PatientTeethStatus patientTeethStatus) {
		persist(patientTeethStatus);
	}

	@Override
	public void updatePatientTeethStatus(PatientTeethStatus patientTeethStatus) {
		update(patientTeethStatus);
	}

	@Override
	public PatientTeethStatus getPatientTeethStatusByPatientIDandTeethID(long patientID, int teethID) {
		Criteria criteria = getSession().createCriteria(PatientTeethStatus.class).add(Restrictions.eq("TeethStatusPK.patient.userID", patientID))
				.add(Restrictions.eq("TeethStatusPK.teeth.teethID", teethID));
		return (PatientTeethStatus) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PatientTeethStatus> getPatientTeethStatusByPatientID(long patientID) {
		Criteria criteria = getSession().createCriteria(PatientTeethStatus.class).add(Restrictions.eq("TeethStatusPK.patient.userID", patientID));
		return criteria.list();
	}

	/*
	 * DAO methods on Teeth.class
	 */
	@Override
	public Teeth getTeethByID(int teethID) {
		Criteria criteria = getSession().createCriteria(Teeth.class).add(Restrictions.idEq(teethID));
		return (Teeth) criteria.uniqueResult();
	}

	@Override
	public Object mergeEntity(Object entity) {

		return merge(entity);
	}

	@Override
	public Session getHibernateSession() {

		return getSession();
	}

}
