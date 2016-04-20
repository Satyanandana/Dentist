package com.dentist.webapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dentist.domain.Address;
import com.dentist.domain.Appointment;
import com.dentist.domain.AppointmentRequest;
import com.dentist.domain.EmergencyContact;
import com.dentist.domain.Insurance;
import com.dentist.domain.Patient;
import com.dentist.domain.ReceivedMessage;
import com.dentist.domain.SentMessage;
import com.dentist.domain.Treatment;
import com.dentist.service.CustomUserDetails;
import com.dentist.service.UserServiceInterface;
import com.dentist.util.WebUtility;

/**
 * 
 *
 * @author Satyanandana Srikanthvarma Vadapalli
 * @email srikanthvarma.vadapalli@gmail.com
 * @version 1.0
 * @since Mar 17, 20161:10:28 AM
 * 
 */
@RestController
@Transactional
@RequestMapping("/patient")
public class PatientInfoController {

	private static final Logger LOGGER = Logger.getLogger(PatientInfoController.class);
	@Autowired
	private UserServiceInterface userServiceInterface;

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/info", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Patient> getPatientInfo(Model model) {
		LOGGER.debug("processing request to get personal info");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		Patient patient = userServiceInterface.getPatientInfoById(user.getUserID());
		if (patient != null) {
			patient.getAppointmentRequests().size();
			patient.getAppointments().size();
			patient.getReceivedMessages().size();
			patient.getSentMessages().size();
			patient.getInsurances().size();
			patient.getTreatments().size();
			patient.getPatientTeeth().size();
			patient.getUploadedDocs().size();
			patient.getReceivedDocs().size();
		}

		return new ResponseEntity<Patient>(patient, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/personalinfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> getPersonalInfo(Model model) {
		LOGGER.debug("processing request to get personal info");
		Map<String, Object> map = new HashMap<String, Object>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		Patient patient = userServiceInterface.getPatientInfoById(user.getUserID());

		map.put("userID", patient.getUserID());
		map.put("firstName", patient.getFirstName());
		map.put("lastName", patient.getLastName());
		map.put("middleName", patient.getMiddleName());
		map.put("dateOfBirth", patient.getDateOfBirth());
		map.put("phoneNumber", patient.getPhoneNumber());
		map.put("email", patient.getEmail());
		map.put("homeAddress", patient.getHomeAddress());
		map.put("EmergencyContact", patient.getEmergencyContact());

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/personalinfo/{patientID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> getPersonalInfoByID(Model model, @PathVariable("patientID") long patientID) {

		LOGGER.debug("processing GET request to personal info with patient ID ....");
		Map<String, Object> map = new HashMap<String, Object>();
		if (patientID != 0) {
			Patient patient = userServiceInterface.getPatientInfoById(patientID);
			if (patient != null) {
				map.put("userID", patient.getUserID());
				map.put("firstName", patient.getFirstName());
				map.put("lastName", patient.getLastName());
				map.put("middleName", patient.getMiddleName());
				map.put("dateOfBirth", patient.getDateOfBirth());
				map.put("phoneNumber", patient.getPhoneNumber());
				map.put("email", patient.getEmail());
				map.put("homeAddress", patient.getHomeAddress());
				map.put("EmergencyContact", patient.getEmergencyContact());
			} else {
				map.put("error", "unable to find patient with given ID");
			}
		} else {
			map.put("error", "Invalid patientID");
		}

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/receivedmessages", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReceivedMessage>> getPatientsReceivedMessages(Model model) {
		LOGGER.debug("processing request to get personal info");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		List<ReceivedMessage> received = userServiceInterface.getReceivedMessagesByPatientID(user.getUserID());

		return new ResponseEntity<List<ReceivedMessage>>(received, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/sentmessages", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SentMessage>> getPatientsSentMessages(Model model) {
		LOGGER.debug("processing request to get personal info");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		List<SentMessage> received = userServiceInterface.getSentMessagesByPatientID(user.getUserID());

		return new ResponseEntity<List<SentMessage>>(received, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/insurances", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Insurance>> getPatientsInsurances(Model model) {
		LOGGER.debug("processing request to get personal info");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		List<Insurance> insurances = userServiceInterface.getInsurancesByPatientID(user.getUserID());

		return new ResponseEntity<List<Insurance>>(insurances, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/appointmentrequests", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AppointmentRequest>> getPatientsAppointmentRequests(Model model) {
		LOGGER.debug("processing request to get personal info");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		List<AppointmentRequest> appointmentRequests = userServiceInterface.getAppointmentRequestsByPatientID(user.getUserID());

		return new ResponseEntity<List<AppointmentRequest>>(appointmentRequests, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/appointments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Appointment>> getPatientsAppointments(Model model) {
		LOGGER.debug("processing request to get personal info");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		List<Appointment> appointments = userServiceInterface.getAppointmentsByPatientID(user.getUserID());

		return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/treatments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Treatment>> getPatientsTreatments(Model model) {
		LOGGER.debug("processing request to get personal info");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		List<Treatment> treatments = userServiceInterface.getTreatmentsByPatientID(user.getUserID());

		return new ResponseEntity<List<Treatment>>(treatments, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/patientteethstatus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Integer, String>> getPatientTeethStatus(Model model) {
		LOGGER.debug("processing request to get personal info");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		Map<Integer, String> patientTeethStatus = userServiceInterface.getPatientTeethStatusMapByPatientID(user.getUserID());

		return new ResponseEntity<Map<Integer, String>>(patientTeethStatus, HttpStatus.OK);
	}

	/********************************************
	 * POST API END POINTS
	 ***************************************************/

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/update/personalinfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> updatePersonalInfo(@RequestParam(name = "firstName") String firstName,
			@RequestParam(name = "lastName") String lastName, @RequestParam(name = "middleName") String middleName,
			@RequestParam(name = "dob") String dob) {

		Map<String, String> map = new HashMap<>();
		boolean valid = false;

		boolean validFirstname = ServerSideValidations.validateName(firstName, null, map, "errorFirstName",
				"Firstname Should contain only alphabets");
		boolean validLastname = ServerSideValidations.validateName(lastName, null, map, "errorLastName", "Lasttname Should contain only alphabets");
		boolean validMiddlename = ServerSideValidations.validateName(middleName, null, map, "errorMiddleName",
				"Middletname Should contain only alphabets");

		if (validFirstname && validLastname && validMiddlename) {
			valid = true;
		}

		LocalDate dateOfBirth = WebUtility.getLocalDateFromHtmlDate(dob);
		if (dateOfBirth == null) {
			map.put("errorDate", "Please select a valid date");
			valid = false;
		}

		if (valid) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
			Patient patient = userServiceInterface.getPatientInfoById(user.getUserID());
			patient.setFirstName(firstName);
			patient.setMiddleName(middleName);
			patient.setLastName(lastName);
			patient.setDateOfBirth(dateOfBirth);
			map.put("success", "success");
		}

		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/update/addressinfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> updateAddressInfo(@RequestParam(name = "address1") String address1,
			@RequestParam(name = "address2") String address2, @RequestParam(name = "city") String city, @RequestParam(name = "state") String state,
			@RequestParam(name = "zipcode") String zipcode) {

		Map<String, String> map = new HashMap<>();
		boolean valid = false;

		boolean validAddress1 = ServerSideValidations.validateAddress(address1, null, map, "errorAddress1", "Invalid Address1 format");
		boolean validAddress2 = ServerSideValidations.validateName(address2, null, map, "errorAddress2", "Invalid Address1 format");
		boolean validCity = ServerSideValidations.validateCity(city, null, map, "errorCity", "Invalid city format");
		boolean validZipCode = ServerSideValidations.validateZipCode(zipcode, null, map, "errorZipCode", "Invalid zipcode");

		if (validAddress1 && validAddress2 && validCity && validZipCode) {
			valid = true;
		}

		if (valid) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
			Patient patient = userServiceInterface.getPatientInfoById(user.getUserID());
			Address address = patient.getHomeAddress();
			address.setAddress1(address1);
			address.setAddress2(address2);
			address.setCity(city);
			address.setState(state);
			address.setZipcode(zipcode);
			patient.setHomeAddress(address);
			map.put("success", "success");
		}

		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/update/contactinfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> updateContactInfo(@RequestParam(name = "phoneNumber") String phoneNumber) {

		Map<String, String> map = new HashMap<>();
		boolean valid = false;

		boolean validPhoneNumber = ServerSideValidations.validatePhoneNumber(phoneNumber, null, map, "errorPhoneNumber", "Invalid phone number");

		if (validPhoneNumber) {
			valid = true;
		}

		if (valid) {

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
			Patient patient = userServiceInterface.getPatientInfoById(user.getUserID());
			patient.setPhoneNumber(phoneNumber);
			map.put("success", "success");
		}

		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/update/emergencycontactinfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> updateEmergencyContactInfo(@RequestParam(name = "emergencyContactName") String emergencyContactName,
			@RequestParam(name = "emergencyContactNumber") String emergencyContactNumber,
			@RequestParam(name = "emergencyContactRelation") String emergencyContactRelation) {

		Map<String, String> map = new HashMap<>();
		boolean valid = false;

		boolean validEmergencyContactNumber = ServerSideValidations.validatePhoneNumber(emergencyContactNumber, null, map, "errorPhoneNumber",
				"Invalid phone number");

		if (validEmergencyContactNumber) {
			valid = true;
		}

		if (valid) {

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
			Patient patient = userServiceInterface.getPatientInfoById(user.getUserID());
			EmergencyContact emergencyContact = patient.getEmergencyContact();
			emergencyContact.setName(emergencyContactName);
			emergencyContact.setPhoneNumber(emergencyContactNumber);
			emergencyContact.setRelation(emergencyContactRelation);
			map.put("success", "success");
		}

		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);

	}

}
