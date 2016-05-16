package com.dentist.webapp;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
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

import com.dentist.domain.Patient;
import com.dentist.domain.PatientTeethStatus;
import com.dentist.domain.Teeth;
import com.dentist.domain.TeethStatus;
import com.dentist.domain.TeethStatusPK;
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
@EnableAsync
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
		PatientTeethStatus patientTeethStatus = userServiceInterface.getPatientTeethStatusByPatientIDandTeethID(user.getUserID(), teethID);
		String status;
		if (patientTeethStatus == null) {
			status = "NORMAL";
		} else {
			status = patientTeethStatus.getTeethStatus().toString();
		}
		String color = "Green";
		BigDecimal total = new BigDecimal(0);
		for (Treatment t : treatments) {
			if (t.getStatus().equals(TreatmentStatus.PENDING)) {
				color = "Red";
			}
			if (t.getStatus().equals(TreatmentStatus.COMPLETED)) {
				total = total.add(t.getAmountPaid());
			}
		}
		map.put("total", total);
		map.put("teeth", teeth);
		map.put("treatments", treatments);
		map.put("color", color);
		map.put("status", status);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/teethstatus/{patientID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Integer, String>> getPatientTeethStatus(@PathVariable("patientID") long patientID) {
		LOGGER.debug("processing request to get personal info");

		Map<Integer, String> patientTeethStatus = userServiceInterface.getPatientTeethStatusMapByPatientID(patientID);

		return new ResponseEntity<Map<Integer, String>>(patientTeethStatus, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{teethID}/{patientID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> getPatientTeethByPatientIDandTeethID(@PathVariable("teethID") int teethID,
			@PathVariable("patientID") long patientID) {

		Map<String, Object> map = new HashMap<String, Object>();
		List<Treatment> treatments = userServiceInterface.getTreatmentsByPatientIDandTeethID(patientID, teethID);
		Teeth teeth = userServiceInterface.getTeethByID(teethID);
		PatientTeethStatus patientTeethStatus = userServiceInterface.getPatientTeethStatusByPatientIDandTeethID(patientID, teethID);
		String status;
		if (patientTeethStatus == null) {
			status = "NORMAL";
		} else {
			status = patientTeethStatus.getTeethStatus().toString();
		}
		String color = "Green";
		BigDecimal total = new BigDecimal(0);
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
		map.put("total", total);
		map.put("teeth", teeth);
		map.put("treatments", treatments);
		map.put("color", color);
		map.put("status", status);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	/**
	 * POST END POINTS TO HANDLE TEETH STATUS FROM USER
	 **/

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/update/status", method = RequestMethod.POST, params = {"status=NORMAL"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Model> updateTeethStatusToNormal(Model model, @RequestParam(name = "teethID") int teethID) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		PatientTeethStatus patientTeethStatus = userServiceInterface.getPatientTeethStatusByPatientIDandTeethID(user.getUserID(), teethID);
		if (patientTeethStatus != null) {
			patientTeethStatus.setLastModified(new DateTime());
			patientTeethStatus.setTeethStatus(TeethStatus.NORMAL);
		} else {
			patientTeethStatus = new PatientTeethStatus();
			patientTeethStatus.setLastModified(new DateTime());
			patientTeethStatus.setTeethStatus(TeethStatus.NORMAL);
			TeethStatusPK teethStatusPK = new TeethStatusPK();
			Patient patient = userServiceInterface.getPatientInfoById(user.getUserID());
			teethStatusPK.setPatient(patient);
			Teeth teeth = userServiceInterface.getTeethByID(teethID);
			teethStatusPK.setTeeth(teeth);
			patientTeethStatus.setTeethStatusPK(teethStatusPK);
			userServiceInterface.setPatientTeethStatus(patientTeethStatus);
		}
		model.addAttribute("Success", "Success");
		return new ResponseEntity<Model>(model, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/update/status", method = RequestMethod.POST, params = {"status=EXTRACTED"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Model> updateTeethStatusToExtracted(Model model, @RequestParam(name = "teethID") int teethID) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		PatientTeethStatus patientTeethStatus = userServiceInterface.getPatientTeethStatusByPatientIDandTeethID(user.getUserID(), teethID);
		if (patientTeethStatus != null) {
			patientTeethStatus.setLastModified(new DateTime());
			patientTeethStatus.setTeethStatus(TeethStatus.EXTRACTED);
		} else {
			patientTeethStatus = new PatientTeethStatus();
			patientTeethStatus.setLastModified(new DateTime());
			patientTeethStatus.setTeethStatus(TeethStatus.EXTRACTED);
			TeethStatusPK teethStatusPK = new TeethStatusPK();
			Patient patient = userServiceInterface.getPatientInfoById(user.getUserID());
			teethStatusPK.setPatient(patient);
			Teeth teeth = userServiceInterface.getTeethByID(teethID);
			teethStatusPK.setTeeth(teeth);
			patientTeethStatus.setTeethStatusPK(teethStatusPK);
			userServiceInterface.setPatientTeethStatus(patientTeethStatus);
		}
		model.addAttribute("Success", "Success");
		return new ResponseEntity<Model>(model, HttpStatus.OK);

	}

	/**
	 * POST END POINTS TO HANDLE TEETH STATUS FROM ADMIN
	 **/

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/update/status/{patientID}", method = RequestMethod.POST, params = {
			"status=NORMAL"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Model> updateTeethStatusToNormalByDoc(Model model, @PathVariable("patientID") long patientID,
			@RequestParam(name = "teethID") int teethID) {

		PatientTeethStatus patientTeethStatus = userServiceInterface.getPatientTeethStatusByPatientIDandTeethID(patientID, teethID);
		if (patientTeethStatus != null) {
			patientTeethStatus.setLastModified(new DateTime());
			patientTeethStatus.setTeethStatus(TeethStatus.NORMAL);
		} else {
			patientTeethStatus = new PatientTeethStatus();
			patientTeethStatus.setLastModified(new DateTime());
			patientTeethStatus.setTeethStatus(TeethStatus.NORMAL);
			TeethStatusPK teethStatusPK = new TeethStatusPK();
			Patient patient = userServiceInterface.getPatientInfoById(patientID);
			teethStatusPK.setPatient(patient);
			Teeth teeth = userServiceInterface.getTeethByID(teethID);
			teethStatusPK.setTeeth(teeth);
			patientTeethStatus.setTeethStatusPK(teethStatusPK);
			userServiceInterface.setPatientTeethStatus(patientTeethStatus);
		}
		model.addAttribute("Success", "Success");
		return new ResponseEntity<Model>(model, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/update/status/{patientID}", method = RequestMethod.POST, params = {
			"status=EXTRACT"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Model> updateTeethStatusToExtractedByDoc(Model model, @PathVariable("patientID") long patientID,
			@RequestParam(name = "teethID") int teethID) {

		PatientTeethStatus patientTeethStatus = userServiceInterface.getPatientTeethStatusByPatientIDandTeethID(patientID, teethID);
		if (patientTeethStatus != null) {
			patientTeethStatus.setLastModified(new DateTime());
			patientTeethStatus.setTeethStatus(TeethStatus.EXTRACTED);
		} else {
			patientTeethStatus = new PatientTeethStatus();
			patientTeethStatus.setLastModified(new DateTime());
			patientTeethStatus.setTeethStatus(TeethStatus.EXTRACTED);
			TeethStatusPK teethStatusPK = new TeethStatusPK();
			Patient patient = userServiceInterface.getPatientInfoById(patientID);
			teethStatusPK.setPatient(patient);
			Teeth teeth = userServiceInterface.getTeethByID(teethID);
			teethStatusPK.setTeeth(teeth);
			patientTeethStatus.setTeethStatusPK(teethStatusPK);
			userServiceInterface.setPatientTeethStatus(patientTeethStatus);
		}
		model.addAttribute("Success", "Success");
		return new ResponseEntity<Model>(model, HttpStatus.OK);

	}

}
