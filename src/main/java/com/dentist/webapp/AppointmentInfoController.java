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

import com.dentist.domain.Appointment;
import com.dentist.service.UserServiceInterface;

/**
* 
*
* @author  Satyanandana Srikanthvarma Vadapalli
* @email srikanthvarma.vadapalli@gmail.com
* @version 1.0
* @since   Mar 27, 20161:39:33 PM
* @git 
*      
*/

@RestController
@Transactional
@RequestMapping("/appointments")
public class AppointmentInfoController {
	
	private static final Logger LOGGER = Logger.getLogger(AppointmentInfoController.class);
	@Autowired
	private UserServiceInterface userServiceInterface;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{appointmentID}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Appointment> getAppointmentByID(@PathVariable("AppointmentID")long AppointmentID ){
		LOGGER.info("processing get request to /appointments/{appointmentID}");
		Appointment Appointment = userServiceInterface.getAppointmentByID(AppointmentID);
		return new ResponseEntity<Appointment>(Appointment, HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/patient/{patientID}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Appointment>> getAppointmentsByPatientID(@PathVariable("patientID")long patientID ){
		LOGGER.info("processing get request to /appointments/patient/{patientID}");
		List<Appointment> appointments = userServiceInterface.getAppointmentsByPatientID(patientID);
		return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);
	}
	
}
