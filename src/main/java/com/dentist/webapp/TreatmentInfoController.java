package com.dentist.webapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
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
import org.springframework.web.bind.annotation.RestController;

import com.dentist.domain.Treatment;
import com.dentist.domain.TreatmentStatus;
import com.dentist.service.CustomUserDetails;
import com.dentist.service.UserServiceInterface;

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
	public ResponseEntity<Map<Integer, String>> getPatientTeethStatus() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		Map<Integer, String> map = new HashMap<Integer, String>();
		for (int teethID = 1; teethID <= 32; teethID++) {
			List<Treatment> treatments = userServiceInterface.getTreatmentsByPatientIDandTeethID(user.getUserID(),
					teethID);
			String color = "Green";
			if (treatments != null) {
				for (Treatment t : treatments) {
					if (t.getStatus().equals(TreatmentStatus.PENDING)) {
						color = "Red";
					}
				}
			}
			map.put(teethID, color);
		}

		return new ResponseEntity<Map<Integer, String>>(map, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/status/{patientID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Integer, String>> getPatientTeethStatusByPatientID(
			@PathVariable("patientID") long patientID) {

		Map<Integer, String> map = new HashMap<Integer, String>();
		for (int teethID = 1; teethID <= 32; teethID++) {
			List<Treatment> treatments = userServiceInterface.getTreatmentsByPatientIDandTeethID(patientID, teethID);
			String color = "Green";
			if (treatments != null) {
				for (Treatment t : treatments) {
					if (t.getStatus().equals(TreatmentStatus.PENDING)) {
						color = "Red";
					}
				}
			}
			map.put(teethID, color);
		}

		return new ResponseEntity<Map<Integer, String>>(map, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{messageID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Treatment> getTreatmentByID(@PathVariable("messageID") long messageID) {
		LOGGER.info("processing get request to /treatments/{messageID}");
		Treatment Treatment = userServiceInterface.getTreatmentByID(messageID);
		return new ResponseEntity<Treatment>(Treatment, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/patient/{patientID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Treatment>> getTreatmentsByPatientID(@PathVariable("patientID") long patientID) {
		LOGGER.info("processing get request to /treatments/patient/{patientID}");
		List<Treatment> treatments = userServiceInterface.getTreatmentsByPatientID(patientID);
		return new ResponseEntity<List<Treatment>>(treatments, HttpStatus.OK);
	}
}
