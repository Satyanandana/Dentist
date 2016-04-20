package com.dentist.webapp;

import java.math.BigDecimal;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dentist.domain.Patient;
import com.dentist.domain.Teeth;
import com.dentist.domain.Treatment;
import com.dentist.domain.TreatmentStatus;
import com.dentist.service.CustomUserDetails;
import com.dentist.service.UserServiceInterface;
import com.dentist.util.WebUtility;

/**
 * 
 *
 * @author Satyanandana Srikanthvarma Vadapalli
 * @email srikanthvarma.vadapalli@gmail.com
 * @version 1.0
 * @since Mar 27, 20161:37:09 PM
 * @git
 * 
 */
@RestController
@Transactional
@RequestMapping("/treatments")
public class TreatmentInfoController {

	private static final Logger LOGGER = Logger.getLogger(TreatmentInfoController.class);
	@Autowired
	private UserServiceInterface userServiceInterface;

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/status", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> getPatientTeethTreatmentStatus() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<Integer, String> treatmentStatus = new HashMap<Integer, String>();
		Map<Integer, String> patientTeethStatus = userServiceInterface.getPatientTeethStatusMapByPatientID(user.getUserID());
		BigDecimal total = new BigDecimal(0);
		for (int teethID = 1; teethID <= 32; teethID++) {
			List<Treatment> treatments = userServiceInterface.getTreatmentsByPatientIDandTeethID(user.getUserID(), teethID);
			String color = "Green";
			if (treatments != null) {
				for (Treatment t : treatments) {
					if (t.getStatus().equals(TreatmentStatus.PENDING)) {
						color = "Red";
					}
					if (t.getStatus().equals(TreatmentStatus.COMPLETED)) {
						total = total.add(t.getAmountPaid());
					}
				}
			}
			treatmentStatus.put(teethID, color);
		}
		map.put("total", total.toString());
		map.put("treatmentStatus", treatmentStatus);
		map.put("patientTeethStatus", patientTeethStatus);

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/status/{patientID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> getPatientTeethTreatmentStatusByPatientID(@PathVariable("patientID") long patientID) {

		Map<String, Object> map = new HashMap<String, Object>();
		Map<Integer, String> treatmentStatus = new HashMap<Integer, String>();
		Map<Integer, String> patientTeethStatus = userServiceInterface.getPatientTeethStatusMapByPatientID(patientID);
		BigDecimal total = new BigDecimal(0);
		for (int teethID = 1; teethID <= 32; teethID++) {
			List<Treatment> treatments = userServiceInterface.getTreatmentsByPatientIDandTeethID(patientID, teethID);
			String color = "Green";
			if (treatments != null) {
				for (Treatment t : treatments) {
					if (t.getStatus().equals(TreatmentStatus.PENDING)) {
						color = "Red";
					}
					if (t.getStatus().equals(TreatmentStatus.COMPLETED)) {
						total = total.add(t.getAmountPaid());
					}
				}
			}
			treatmentStatus.put(teethID, color);
		}
		map.put("total", total.toString());
		map.put("treatmentStatus", treatmentStatus);
		map.put("patientTeethStatus", patientTeethStatus);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{treatmentID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Treatment> getTreatmentByID(@PathVariable("treatmentID") long treatmentID) {
		LOGGER.info("processing get request to /treatments/{treatmentID}");
		Treatment Treatment = userServiceInterface.getTreatmentByID(treatmentID);
		return new ResponseEntity<Treatment>(Treatment, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/patient/{patientID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Treatment>> getTreatmentsByPatientID(@PathVariable("patientID") long patientID) {
		LOGGER.info("processing get request to /treatments/patient/{patientID}");
		List<Treatment> treatments = userServiceInterface.getTreatmentsByPatientID(patientID);
		return new ResponseEntity<List<Treatment>>(treatments, HttpStatus.OK);
	}

	/*******************************************************
	 * POST API END POINTS TO HANDLE TREATMENTS REQUESTS FROM PATIENT
	 ******************************************************/

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = {"status=COMPLETED"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> createTreatmentWithStatusCompleted(@RequestParam(name = "teethID") int teethID,
			@RequestParam(name = "note") String note, @RequestParam(name = "treatmentExpectedTime") String treatmentExpectedTime,
			@RequestParam(name = "treatmentDoneTime") String treatmentDoneTime, @RequestParam(name = "amountExpected") BigDecimal amountExpected,
			@RequestParam(name = "amountPaid") BigDecimal amountPaid) {

		Boolean valid = false;
		Map<String, Object> map = new HashMap<>();
		LocalDate doneDate = WebUtility.getLocalDateFromHtmlDate(treatmentDoneTime);
		LocalDate expectedDate = WebUtility.getLocalDateFromHtmlDate(treatmentExpectedTime);
		if (doneDate != null && expectedDate != null && teethID > 0 && teethID < 33 && !note.isEmpty()) {
			valid = true;
		}

		if (valid) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
			Patient patient = userServiceInterface.getPatientInfoById(user.getUserID());
			Teeth teeth = userServiceInterface.getTeethByID(teethID);
			Treatment treatment = new Treatment();
			treatment.setAmountExpected(amountExpected);
			treatment.setAmountPaid(amountPaid);
			treatment.setNote(note);
			treatment.setPatient(patient);
			treatment.setStatus(TreatmentStatus.COMPLETED);
			treatment.setTeeth(teeth);
			treatment.setTreatmentDoneTime(doneDate);
			treatment.setTreatmentExpectedTime(expectedDate);
			treatment.setTreatmentInsertedTime(new DateTime());
			userServiceInterface.setTreatment(treatment);
			map.put("success", "success");

		} else {
			map.put("error", "Unable to create a treatment .Please check all the fields and submit again");
		}

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = {"status=PENDING"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> createTreatmentWithStatusPending(@RequestParam(name = "teethID") int teethID,
			@RequestParam(name = "note") String note, @RequestParam(name = "treatmentExpectedTime") String treatmentExpectedTime,
			@RequestParam(name = "amountExpected") BigDecimal amountExpected) {

		Boolean valid = false;
		Map<String, Object> map = new HashMap<>();
		LocalDate expectedDate = WebUtility.getLocalDateFromHtmlDate(treatmentExpectedTime);
		if (expectedDate != null && teethID > 0 && teethID < 33 && !note.isEmpty()) {
			valid = true;
		}

		if (valid) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
			Patient patient = userServiceInterface.getPatientInfoById(user.getUserID());
			Teeth teeth = userServiceInterface.getTeethByID(teethID);
			Treatment treatment = new Treatment();
			treatment.setAmountExpected(amountExpected);
			treatment.setNote(note);
			treatment.setPatient(patient);
			treatment.setStatus(TreatmentStatus.PENDING);
			treatment.setTeeth(teeth);
			treatment.setTreatmentExpectedTime(expectedDate);
			treatment.setTreatmentInsertedTime(new DateTime());
			userServiceInterface.setTreatment(treatment);
			map.put("success", "success");
		} else {
			map.put("error", "Unable to create a treatment .Please check all the fields and submit again");
		}

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/update", method = RequestMethod.POST, params = {"status=COMPLETED"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> updateTreatmentWithStatusCompleted(@RequestParam(name = "treatmentID") int treatmentID,
			@RequestParam(name = "note") String note, @RequestParam(name = "treatmentDoneTime") String treatmentDoneTime,
			@RequestParam(name = "amountPaid") BigDecimal amountPaid) {

		Boolean valid = false;
		Map<String, Object> map = new HashMap<>();
		LocalDate doneDate = WebUtility.getLocalDateFromHtmlDate(treatmentDoneTime);

		if (doneDate != null && !note.isEmpty()) {
			valid = true;
		}

		if (valid) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();

			Treatment treatment = userServiceInterface.getTreatmentByIDandPatientID(treatmentID, user.getUserID());
			if (treatment != null) {
				treatment.setAmountPaid(amountPaid);
				treatment.setNote(note);
				treatment.setStatus(TreatmentStatus.COMPLETED);
				treatment.setTreatmentDoneTime(doneDate);
				map.put("success", "success");
			} else {
				map.put("error", "Invalid treatment ID");
			}

		} else {
			map.put("error", "Unable to create a treatment .Please check all the fields and submit again");
		}

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

}
