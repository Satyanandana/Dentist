package com.dentist.webapp;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dentist.domain.AppointmentRequest;
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
@RequestMapping("/appointmentrequests")
public class AppointmentRequestInfoController {

	private static final Logger LOGGER = Logger.getLogger(AppointmentRequestInfoController.class);
	@Autowired
	private UserServiceInterface userServiceInterface;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{appointmentRequestID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AppointmentRequest> getAppointmentRequestByID(
			@PathVariable("appointmentRequestID") long appointmentRequestID) {
		LOGGER.info("processing get request to /appointmentrequests/{appointmentRequestID}");
		AppointmentRequest appointmentRequest = userServiceInterface.getAppointmentRequestByID(appointmentRequestID);
		return new ResponseEntity<AppointmentRequest>(appointmentRequest, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/patient/{patientID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AppointmentRequest>> getAppointmentRequestsByPatientID(
			@PathVariable("patientID") long patientID) {
		LOGGER.info("processing get request to /appointmentrequests/patient/{patientID}");
		List<AppointmentRequest> appointmentRequests = userServiceInterface
				.getAppointmentRequestsByPatientID(patientID);
		return new ResponseEntity<List<AppointmentRequest>>(appointmentRequests, HttpStatus.OK);
	}

}
