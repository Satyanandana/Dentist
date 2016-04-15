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

import com.dentist.domain.Teeth;
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
 * @since Mar 27, 20161:39:33 PM
 * @git
 * 
 */

@RestController
@Transactional
@RequestMapping("/teeth")
public class PatientTeethController {

	private static final Logger LOGGER = Logger.getLogger(PatientTeethController.class);
	@Autowired
	private UserServiceInterface userServiceInterface;

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/{teethID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> getPatientTeethByID(@PathVariable("teethID") int teethID) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Treatment> treatments = userServiceInterface.getTreatmentsByPatientIDandTeethID(user.getUserID(), teethID);
		Teeth teeth = userServiceInterface.getTeethByID(teethID);
		String color = "Green";
		for (Treatment t : treatments) {
			if (t.getStatus().equals(TreatmentStatus.PENDING)) {
				color = "Red";
			}
		}
		map.put("teeth", teeth);
		map.put("treatments", treatments);
		map.put("color", color);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{teethID}/{patientID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> getPatientTeethByPatientIDandTeethID(
			@PathVariable("teethID") int teethID, @PathVariable("patientID") long patientID) {

		Map<String, Object> map = new HashMap<String, Object>();
		List<Treatment> treatments = userServiceInterface.getTreatmentsByPatientIDandTeethID(patientID, teethID);
		Teeth teeth = userServiceInterface.getTeethByID(teethID);
		String color = "Green";
		if (treatments != null) {
			for (Treatment t : treatments) {
				if (t.getStatus().equals(TreatmentStatus.PENDING)) {
					color = "Red";
				}
			}
		}
		map.put("teeth", teeth);
		map.put("treatments", treatments);
		map.put("color", color);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

}
