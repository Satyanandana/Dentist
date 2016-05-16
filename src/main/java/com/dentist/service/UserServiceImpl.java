package com.dentist.service;

import java.util.HashMap;
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
import com.dentist.domain.PatientTeethStatus;
import com.dentist.domain.ReceivedDocument;
import com.dentist.domain.ReceivedMessage;
import com.dentist.domain.Role;
import com.dentist.domain.SentDocument;
import com.dentist.domain.SentMessage;
import com.dentist.domain.Teeth;
import com.dentist.domain.TeethStatus;
import com.dentist.domain.Treatment;
import com.dentist.domain.UserAuthentication;
import com.dentist.util.WebUtility;

@Service
@Transactional
public class UserServiceImpl implements UserServiceInterface {

	@Autowired
	private UserDaoInterface userDaoInterface;

	/*
	 * DAO methods on UserAuthentication.class
	 */
	@Override
	public void setUserAuthenticationInfo(UserAuthentication userAuthentication) {
		userDaoInterface.setUserAuthenticationInfo(userAuthentication);
	}

	@Override
	public void updateUserAuthenticationInfo(UserAuthentication userAuthentication) {
		userDaoInterface.updateUserAuthenticationInfo(userAuthentication);
	}

	@Override
	public UserAuthentication getUserAuthenticationInfoById(long id) {
		return userDaoInterface.getUserAuthenticationInfoById(id);
	}

	@Override
	public UserAuthentication getUserAuthenticationInfoByEmail(String email) {
		return userDaoInterface.getUserAuthenticationInfoByEmail(email);
	}

	/*
	 * DAO methods on Patient.class
	 */
	@Override
	public void setPatient(Patient patient) {
		userDaoInterface.setPatient(patient);
	}

	@Override
	public void updatePatient(Patient patient) {
		userDaoInterface.updatePatient(patient);
	}

	@Override
	public Patient getPatientInfoById(long patientID) {
		Patient patient = userDaoInterface.getPatientInfoById(patientID);

		return patient;
	}

	@Override
	public Patient getBasicPatientDetails(long patientID) {

		return userDaoInterface.getBasicPatientDetails(patientID);
	}

	@Override
	public Patient getPatientInfoByEmail(String patientEmail) {
		return userDaoInterface.getPatientInfoByEmail(patientEmail);
	}

	@Override
	public List<Patient> getAllPatients() {
		return userDaoInterface.getAllPatients();
	}

	/*
	 * DAO methods on AppointmentRequest.class
	 */
	@Override
	public void setAppointmentRequest(AppointmentRequest request) {
		userDaoInterface.setAppointmentRequest(request);
	}

	@Override
	public void updateAppointmentRequest(AppointmentRequest request) {
		userDaoInterface.updateAppointmentRequest(request);
	}

	@Override
	public AppointmentRequest getAppointmentRequestByID(long appointmentRequestID) {
		return userDaoInterface.getAppointmentRequestByID(appointmentRequestID);
	}

	@Override
	public List<AppointmentRequest> getAppointmentRequestsByPatientID(long patientID) {
		return userDaoInterface.getAppointmentRequestsByPatientID(patientID);
	}

	@Override
	public AppointmentRequest getAppointmentRequestByIDandPatientID(long appointmentRequestID, long patientID) {
		return userDaoInterface.getAppointmentRequestByIDandPatientID(appointmentRequestID, patientID);

	}

	@Override
	public List<AppointmentRequest> getAllAppointmentRequests() {
		return userDaoInterface.getAllAppointmentRequests();
	}

	@Override
	public List<AppointmentRequest> getAllPendingAppointmentRequests() {
		return userDaoInterface.getAllPendingAppointmentRequests();
	}

	/*
	 * DAO methods on Appointment.class
	 */
	@Override
	public void setAppointment(Appointment appointment) {
		userDaoInterface.setAppointment(appointment);
	}

	@Override
	public void updateAppointment(Appointment appointment) {
		userDaoInterface.updateAppointment(appointment);
	}

	@Override
	public Appointment getAppointmentByID(long appointmentID) {
		return userDaoInterface.getAppointmentByID(appointmentID);
	}

	@Override
	public Appointment getAppointmentByIDandPatientID(long appointmentID, long patientID) {

		return userDaoInterface.getAppointmentByIDandPatientID(appointmentID, patientID);
	}

	@Override
	public List<Appointment> getAppointmentsByPatientID(long patientID) {
		return userDaoInterface.getAppointmentsByPatientID(patientID);
	}

	@Override
	public List<Appointment> getAllAppointments() {

		return userDaoInterface.getAllAppointments();
	}

	/*
	 * DAO methods on Insurance.class
	 */
	@Override
	public void setInsurance(Insurance insurance) {
		userDaoInterface.setInsurance(insurance);
	}

	@Override
	public void updateInsurance(Insurance insurance) {
		userDaoInterface.updateInsurance(insurance);
	}

	@Override
	public Insurance getInsuranceByID(long insuranceID) {
		return userDaoInterface.getInsuranceByID(insuranceID);
	}

	@Override
	public Insurance getInsuranceByIDandPatientID(long insuranceID, long patientID) {
		return userDaoInterface.getInsuranceByIDandPatientID(insuranceID, patientID);
	}

	@Override
	public List<Insurance> getInsurancesByPatientID(long patientID) {
		return userDaoInterface.getInsurancesByPatientID(patientID);
	}

	/*
	 * DAO methods on SentMessage.class
	 */

	@Override
	public void setSentMessage(SentMessage sentMessage) {
		userDaoInterface.setSentMessage(sentMessage);
	}

	@Override
	public void updateSentMessage(SentMessage sentMessage) {
		userDaoInterface.updateSentMessage(sentMessage);
	}

	@Override
	public SentMessage getSentMessageByID(long sentMessageID) {
		return userDaoInterface.getSentMessageByID(sentMessageID);
	}

	@Override
	public List<SentMessage> getSentMessagesByPatientID(long patientID) {
		return userDaoInterface.getSentMessagesByPatientID(patientID);
	}

	@Override
	public List<SentMessage> getAllSentMessages() {
		return userDaoInterface.getAllSentMessages();
	}

	/*
	 * DAO methods on ReceivedMessage.class
	 */
	@Override
	public void setReceivedMessage(ReceivedMessage receivedMessage) {
		userDaoInterface.setReceivedMessage(receivedMessage);
	}

	@Override
	public void updateReceivedMessage(ReceivedMessage receivedMessage) {
		userDaoInterface.updateReceivedMessage(receivedMessage);
	}

	@Override
	public ReceivedMessage getReceivedMessageByID(long receivedMessageID) {
		return userDaoInterface.getReceivedMessageByID(receivedMessageID);
	}

	@Override
	public List<ReceivedMessage> getReceivedMessagesByPatientID(long patientID) {
		return userDaoInterface.getReceivedMessagesByPatientID(patientID);
	}

	@Override
	public List<ReceivedMessage> getAllReceivedMessages() {
		return userDaoInterface.getAllReceivedMessages();
	}

	/**
	 * call DAO methods on SentDocument.class
	 **/

	@Override
	public void setSentDocument(SentDocument sentDocument) {
		userDaoInterface.setSentDocument(sentDocument);
	}

	@Override
	public void updateSentDocument(SentDocument sentDocument) {
		userDaoInterface.updateSentDocument(sentDocument);
	}

	@Override
	public SentDocument getSentDocumentByID(long sentDocumentID) {
		return userDaoInterface.getSentDocumentByID(sentDocumentID);
	}

	@Override
	public SentDocument getSentDocumentByIDandPatientID(long sentDocumentID, long patientID) {
		return userDaoInterface.getSentDocumentByIDandPatientID(sentDocumentID, patientID);
	}

	@Override
	public List<SentDocument> getSentDocumentsByPatientID(long patientID) {
		return userDaoInterface.getSentDocumentsByPatientID(patientID);
	}

	@Override
	public List<SentDocument> getAllSentDocuments() {
		return userDaoInterface.getAllSentDocuments();
	}

	/**
	 * call DAO methods on ReceivedDocument.class
	 **/
	@Override
	public void setReceivedDocument(ReceivedDocument receivedDocument) {
		userDaoInterface.setReceivedDocument(receivedDocument);
	}

	@Override
	public void updateReceivedDocument(ReceivedDocument receivedDocument) {
		userDaoInterface.updateReceivedDocument(receivedDocument);
	}

	@Override
	public ReceivedDocument getReceivedDocumentByID(long receivedDocumentID) {
		return userDaoInterface.getReceivedDocumentByID(receivedDocumentID);
	}

	@Override
	public ReceivedDocument getReceivedDocumentByIDandPatientID(long receivedDocumentID, long patientID) {

		return userDaoInterface.getReceivedDocumentByIDandPatientID(receivedDocumentID, patientID);
	}

	@Override
	public List<ReceivedDocument> getReceivedDocumentsByPatientID(long patientID) {
		return userDaoInterface.getReceivedDocumentsByPatientID(patientID);
	}

	@Override
	public List<ReceivedDocument> getAllreceivedDocuments() {
		return userDaoInterface.getAllreceivedDocuments();
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
	public Treatment getTreatmentByIDandPatientID(long treatmentID, long patientID) {

		return userDaoInterface.getTreatmentByIDandPatientID(treatmentID, patientID);
	}

	@Override
	public List<Treatment> getTreatmentsByPatientID(long userID) {
		return userDaoInterface.getTreatmentsByPatientID(userID);
	}

	@Override
	public List<Treatment> getTreatmentsByPatientIDandTeethID(long patientID, int teethID) {
		return userDaoInterface.getTreatmentsByPatientIDandTeethID(patientID, teethID);
	}

	/*
	 * DAO methods on PatientTeethStatus.class
	 */
	@Override
	public void setPatientTeethStatus(PatientTeethStatus patientTeethStatus) {
		userDaoInterface.setPatientTeethStatus(patientTeethStatus);
	}

	@Override
	public void updatePatientTeethStatus(PatientTeethStatus patientTeethStatus) {
		userDaoInterface.updatePatientTeethStatus(patientTeethStatus);
	}

	@Override
	public PatientTeethStatus getPatientTeethStatusByPatientIDandTeethID(long patientID, int teethID) {
		return userDaoInterface.getPatientTeethStatusByPatientIDandTeethID(patientID, teethID);
	}

	@Override
	public List<PatientTeethStatus> getPatientTeethStatusByPatientID(long patientID) {
		List<PatientTeethStatus> patientTeethStatuses = userDaoInterface.getPatientTeethStatusByPatientID(patientID);

		return patientTeethStatuses;
	}

	@Override
	public Map<Integer, String> getPatientTeethStatusMapByPatientID(long patientID) {
		List<PatientTeethStatus> patientTeethStatuses = getPatientTeethStatusByPatientID(patientID);
		PatientTeethStatus[] arrayOfPatientTeethStatus = new PatientTeethStatus[32];
		Map<Integer, String> teethStatuses = new HashMap<Integer, String>();
		if (patientTeethStatuses.size() < 32) {

			for (PatientTeethStatus item : patientTeethStatuses) {
				arrayOfPatientTeethStatus[item.getTeethStatusPK().getTeeth().getTeethID() - 1] = item;
			}
			int i = 1;
			for (PatientTeethStatus teeth : arrayOfPatientTeethStatus) {
				if (teeth == null) {
					teethStatuses.put(i, TeethStatus.NORMAL.getStatus());
				} else {
					teethStatuses.put(i, teeth.getTeethStatus().getStatus());
				}
				i++;
			}
		}
		return teethStatuses;
	}

	/*
	 * DAO methods on Teeth.class
	 */

	@Override
	public Teeth getTeethByID(int teethID) {
		return userDaoInterface.getTeethByID(teethID);
	}

	/*
	 * user sign up mechanism
	 */
	@Override
	public Patient signUp(Patient patient, HttpServletRequest request, Model model) {
		Patient patientCreated = null;
		UserAuthentication userAuth = getUserAuthenticationInfoByEmail(patient.getUserAuth().getUserEmail());
		if (userAuth == null) {
			userAuth = patient.getUserAuth();
			userAuth.setAccountStatus(AccountStatus.NOT_ACTIVATED_YET);
			userAuth.setCreationTime(new DateTime());
			userAuth.setLastLoginTime(new DateTime());
			userAuth.setUserRole(Role.ROLE_USER);
			String ipAddress = WebUtility.getIpAddress(request);
			userAuth.setUserIp(ipAddress);
			String verifyKey = userAuth.getUserEmail() + userAuth.getCreationTime();
			userAuth.setVerifyKey(verifyKey);
			userAuth.setPrevSessionID(request.getSession().getId());

			patient.setEmail(userAuth.getUserEmail());
			patient.setUserAuth(userAuth);
			patientCreated = patient;

		} else {
			model.addAttribute("error", "Another account exists with this email.Try another email");
		}
		return patientCreated;
	}

}
