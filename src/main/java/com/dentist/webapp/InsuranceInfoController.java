package com.dentist.webapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dentist.domain.Insurance;
import com.dentist.domain.Patient;
import com.dentist.service.CustomUserDetails;
import com.dentist.service.UserServiceInterface;
import com.dentist.util.WebUtility;

/**
 * 
 *
 * @author Satyanandana Srikanthvarma Vadapalli
 * @email srikanthvarma.vadapalli@gmail.com
 * @version 1.0
 * @since Mar 27, 20161:39:33 PM
 * @git
 * 
 */

@RestController
@RequestMapping("/insurances")
public class InsuranceInfoController {

	private static final Logger LOGGER = Logger.getLogger(InsuranceInfoController.class);
	@Autowired
	private UserServiceInterface userServiceInterface;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{insuranceID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Insurance> getInsurancesByID(@PathVariable("insuranceID") long insuranceID) {
		LOGGER.info("processing get request to /insurances/{insuranceID}");
		Insurance insurance = userServiceInterface.getInsuranceByID(insuranceID);
		return new ResponseEntity<Insurance>(insurance, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_USER')") // change to ROLE_ADMIN
	@RequestMapping(value = "/patient/{patientID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Insurance>> getInsurancesByPatientID(@PathVariable("patientID") long patientID) {
		LOGGER.info("processing get request to /insurances/patient/{patientID}");
		List<Insurance> insurances = userServiceInterface.getInsurancesByPatientID(patientID);
		return new ResponseEntity<List<Insurance>>(insurances, HttpStatus.OK);
	}

	/*******************************************************
	 * POST API END POINTS TO HANDLE INSURANCE REQUESTS FROM PATIENT
	 ******************************************************/

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = {"status=Cancel"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> createInsurance(@ModelAttribute("insurance") Insurance insurance,
			@RequestParam(name = "dob") String dob) {
		Map<String, String> map = new HashMap<>();
		LocalDate dateOfBirth = WebUtility.getLocalDateFromHtmlDate(dob);
		if (dateOfBirth != null && insurance.getInsuranceProviderID() != null && insurance.getInsuranceProviderName() != null
				&& insurance.getSubscriberID() != null && insurance.getSubscriberFullName() != null && insurance.getStatus() != null) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
			Patient patient = userServiceInterface.getPatientInfoById(user.getUserID());
			insurance.setDateOfBirth(dateOfBirth);
			insurance.setInsertedDate(new DateTime());
			insurance.setInsurancePatient(patient);
			userServiceInterface.setInsurance(insurance);
			map.put("Success", "Success");

		} else {
			map.put("error", "Please check the fields and submit valid data");
		}

		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/update", method = RequestMethod.POST, params = {"status=Cancel"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> updateInsurance(@ModelAttribute("insurance") Insurance insurance,
			@RequestParam(name = "dob") String dob, @RequestParam(name = "insuranceID") long insuranceID) {
		Map<String, String> map = new HashMap<>();
		LocalDate dateOfBirth = WebUtility.getLocalDateFromHtmlDate(dob);
		if (dateOfBirth != null && insurance.getInsuranceProviderID() != null && insurance.getInsuranceProviderName() != null
				&& insurance.getSubscriberID() != null && insurance.getSubscriberFullName() != null && insurance.getStatus() != null) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
			Insurance insFromDb = userServiceInterface.getInsuranceByIDandPatientID(insuranceID, user.getUserID());
			insFromDb.setDateOfBirth(dateOfBirth);
			insFromDb.setInsuranceProviderID(insurance.getInsuranceProviderID());
			insFromDb.setInsuranceProviderName(insurance.getInsuranceProviderName());
			insFromDb.setSubscriberFullName(insurance.getSubscriberFullName());
			insFromDb.setSubscriberID(insurance.getSubscriberID());
			insFromDb.setStatus(insurance.getStatus());
			map.put("Success", "Success");

		} else {
			map.put("error", "Please check the fields and submit valid data");
		}

		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
	}

	/*******************************************************
	 * POST API END POINTS TO HANDLE INSURANCE REQUESTS FROM ADMIN
	 ******************************************************/

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/create/{patientID}", method = RequestMethod.POST, params = {
			"status=Cancel"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> createInsuranceByDoc(@PathVariable("patientID") long patientID,
			@ModelAttribute("insurance") Insurance insurance, @RequestParam(name = "dob") String dob) {
		Map<String, String> map = new HashMap<>();
		LocalDate dateOfBirth = WebUtility.getLocalDateFromHtmlDate(dob);
		if (dateOfBirth != null && insurance.getInsuranceProviderID() != null && insurance.getInsuranceProviderName() != null
				&& insurance.getSubscriberID() != null && insurance.getSubscriberFullName() != null && insurance.getStatus() != null) {
			Patient patient = userServiceInterface.getPatientInfoById(patientID);
			insurance.setDateOfBirth(dateOfBirth);
			insurance.setInsertedDate(new DateTime());
			insurance.setInsurancePatient(patient);
			userServiceInterface.setInsurance(insurance);
			map.put("Success", "Success");

		} else {
			map.put("error", "Please check the fields and submit valid data");
		}

		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ROLE_ADMIN')")
	@RequestMapping(value = "/update/{patientID}", method = RequestMethod.POST, params = {
			"status=Cancel"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> updateInsuranceByDoc(@PathVariable("patientID") long patientID,
			@ModelAttribute("insurance") Insurance insurance, @RequestParam(name = "dob") String dob,
			@RequestParam(name = "insuranceID") long insuranceID) {
		Map<String, String> map = new HashMap<>();
		LocalDate dateOfBirth = WebUtility.getLocalDateFromHtmlDate(dob);
		if (dateOfBirth != null && insurance.getInsuranceProviderID() != null && insurance.getInsuranceProviderName() != null
				&& insurance.getSubscriberID() != null && insurance.getSubscriberFullName() != null && insurance.getStatus() != null) {

			Insurance insFromDb = userServiceInterface.getInsuranceByIDandPatientID(insuranceID, patientID);
			if (insFromDb != null) {
				insFromDb.setDateOfBirth(dateOfBirth);
				insFromDb.setInsuranceProviderID(insurance.getInsuranceProviderID());
				insFromDb.setInsuranceProviderName(insurance.getInsuranceProviderName());
				insFromDb.setSubscriberFullName(insurance.getSubscriberFullName());
				insFromDb.setSubscriberID(insurance.getSubscriberID());
				insFromDb.setStatus(insurance.getStatus());
				map.put("Success", "Success");
			} else {
				map.put("error", "Invalid insurance ID or patient ID");
			}

		} else {
			map.put("error", "Please check the fields and submit valid data");
		}

		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
	}
}
