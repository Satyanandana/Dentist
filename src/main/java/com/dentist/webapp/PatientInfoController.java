package com.dentist.webapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.SerializationUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dentist.domain.Appointment;
import com.dentist.domain.AppointmentRequest;
import com.dentist.domain.Insurance;
import com.dentist.domain.Patient;
import com.dentist.domain.PatientTeethStatus;
import com.dentist.domain.ReceivedMessage;
import com.dentist.domain.SentMessage;
import com.dentist.domain.Treatment;
import com.dentist.service.CustomUserDetails;
import com.dentist.service.UserServiceInterface;
/**
* 
*
* @author  Satyanandana Srikanthvarma Vadapalli
* @email srikanthvarma.vadapalli@gmail.com
* @version 1.0
* @since   Mar 17, 20161:10:28 AM
*       
*/
@RestController
@RequestMapping("/patient")
public class PatientInfoController {
	
	private static final Logger logger = Logger.getLogger(PatientInfoController.class);
	@Autowired
	private UserServiceInterface userServiceInterface;
	
	@Transactional
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/info", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Patient> getPatientInfo(Model model){
		logger.debug("processing request to get personal info");
				
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		Patient patient = userServiceInterface.getPatientInfoById(user.getUserID());
		if(patient!=null){
		patient.getAppointmentRequests().size();
		patient.getAppointments().size();
		patient.getReceivedMessages().size();
		patient.getSentMessages().size();
		patient.getInsurances().size();
		patient.getTreatments().size();
		patient.getPatientTeeth().size();
		}
				
		return new ResponseEntity<Patient>(patient,HttpStatus.OK);	
	}
	
	@Transactional
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/personalinfo", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> getPersonalInfo(Model model){
		logger.debug("processing request to get personal info");
		Map<String, Object> map = new HashMap<String, Object>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		Patient patient = userServiceInterface.getPatientInfoById(user.getUserID());
	
		map.put("userID",patient.getUserID());
		map.put("firstName", patient.getFirstName());
		map.put("lastName",patient.getLastName());
		map.put("middleName",patient.getMiddleName());
		map.put("dateOfBirth",patient.getDateOfBirth());
        map.put("phoneNumber",patient.getPhoneNumber());
		map.put("email",patient.getEmail());
		map.put("homeAddress",patient.getHomeAddress());
		map.put("EmergencyContact",patient.getEmergencyContact());
		
		return new ResponseEntity<Map<String, Object>>(map,HttpStatus.OK);
			
	}
	

	
	@Transactional
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/receivedmessages", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReceivedMessage>> getPatientsReceivedMessages(Model model){
		logger.debug("processing request to get personal info");
			
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		List<ReceivedMessage> received = userServiceInterface.getReceivedMessagesByPatientID(user.getUserID());
				
		return new ResponseEntity<List<ReceivedMessage>>(received, HttpStatus.OK);
	}
	
	@Transactional
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/sentmessages", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SentMessage>> getPatientsSentMessages(Model model){
		logger.debug("processing request to get personal info");
			
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		List<SentMessage> received = userServiceInterface.getSentMessagesByPatientID(user.getUserID());
				
		return new ResponseEntity<List<SentMessage>>(received, HttpStatus.OK);
	}
	
	@Transactional
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/insurances", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Insurance>> getPatientsInsurances(Model model){
		logger.debug("processing request to get personal info");
			
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		List<Insurance> insurances = userServiceInterface.getInsurancesByPatientID(user.getUserID());
				
		return new ResponseEntity<List<Insurance>>(insurances, HttpStatus.OK);
	}
	
	@Transactional
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/appointmentrequests", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AppointmentRequest>> getPatientsAppointmentRequests(Model model){
		logger.debug("processing request to get personal info");
			
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		List<AppointmentRequest> appointmentRequests = userServiceInterface.getAppointmentRequestsByPatientID(user.getUserID());
				
		return new ResponseEntity<List<AppointmentRequest>>(appointmentRequests, HttpStatus.OK);
	}
	
	@Transactional
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/appointments", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Appointment>> getPatientsAppointments(Model model){
		logger.debug("processing request to get personal info");
			
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		List<Appointment> appointments = userServiceInterface.getAppointmentsByPatientID(user.getUserID());
				
		return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);
	}
	
	@Transactional
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/treatments", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Treatment>> getPatientsTreatments(Model model){
		logger.debug("processing request to get personal info");
			
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		List<Treatment> treatments = userServiceInterface.getTreatmentsByPatientID(user.getUserID());
				
		return new ResponseEntity<List<Treatment>>(treatments, HttpStatus.OK);
	}
	
	@Transactional
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/patientteethstatus", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Integer,String>> getPatientTeethStatus(Model model){
		logger.debug("processing request to get personal info");
			
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		Map<Integer,String> patientTeethStatus = userServiceInterface.getPatientTeethStatusMapByPatientID(user.getUserID());
				
		return new ResponseEntity<Map<Integer,String>>(patientTeethStatus, HttpStatus.OK);
	}

}
