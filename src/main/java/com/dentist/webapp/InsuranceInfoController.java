package com.dentist.webapp;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dentist.domain.Insurance;
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

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/patient/{patientID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Insurance>> getInsurancesByPatientID(@PathVariable("patientID") long patientID) {
		LOGGER.info("processing get request to /insurances/patient/{patientID}");
		List<Insurance> insurances = userServiceInterface.getInsurancesByPatientID(patientID);
		return new ResponseEntity<List<Insurance>>(insurances, HttpStatus.OK);
	}

}
