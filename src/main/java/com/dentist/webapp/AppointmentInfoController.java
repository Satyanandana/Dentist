package com.dentist.webapp;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import com.dentist.domain.Appointment;
import com.dentist.domain.AppointmentStatus;
import com.dentist.domain.Patient;
import com.dentist.googlecalendar.CalendarEventHandler;
import com.dentist.mail.EmailGenerator;
import com.dentist.mail.EmailStructure;
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
@RequestMapping("/appointments")
public class AppointmentInfoController {

	private static final Logger LOGGER = Logger.getLogger(AppointmentInfoController.class);
	@Autowired
	private UserServiceInterface userServiceInterface;
	@Autowired
	private EmailGenerator emailSender;
	@Autowired
	private EmailStructure emailStructure;
	@Autowired
	@Qualifier("encryptableProps")
	private Properties encryptableProps;
	@Autowired
	private CalendarEventHandler calendarEventHandler;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{appointmentID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Appointment> getAppointmentByID(@PathVariable("AppointmentID") long AppointmentID) {
		LOGGER.info("processing get request to /appointments/{appointmentID}");
		Appointment Appointment = userServiceInterface.getAppointmentByID(AppointmentID);
		return new ResponseEntity<Appointment>(Appointment, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/patient/{patientID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Appointment>> getAppointmentsByPatientID(@PathVariable("patientID") long patientID) {
		LOGGER.info("processing get request to /appointments/patient/{patientID}");
		List<Appointment> appointments = userServiceInterface.getAppointmentsByPatientID(patientID);
		return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);
	}

	/*******************************************************
	 * POST API END POINTS TO HANDLE APPOINTMENTS FROM PATIENT
	 ******************************************************/

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/update", method = RequestMethod.POST, params = {"status=COMPLETED"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> updateAppointmentToCompleted(@RequestParam(name = "appointmentID") long appointmentID,
			@RequestParam(name = "note") String note, @RequestParam(name = "amountPaid") BigDecimal amountPaid) {
		Map<String, Object> map = new HashMap<>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		Appointment appointment = userServiceInterface.getAppointmentByIDandPatientID(appointmentID, user.getUserID());
		if (appointment != null) {
			appointment.setNote(note);
			appointment.setAmountPaid(amountPaid);
			appointment.setStatus(AppointmentStatus.COMPLETED);
			map.put("Success", "Success");
		} else {
			map.put("error", "Invalid appoint id or patient id");
		}

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

	}

	/*******************************************************
	 * POST API END POINTS TO HANDLE APPOINTMENTS FROM ADMIN
	 ******************************************************/

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/update/{patientID}", method = RequestMethod.POST, params = {
			"status=CANCEL"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> updateAppointmentToCancelled(@PathVariable("patientID") long patientID,
			@RequestParam(name = "appointmentID") long appointmentID, @RequestParam(name = "note") String note) {
		Map<String, Object> map = new HashMap<>();
		Appointment appointment = userServiceInterface.getAppointmentByIDandPatientID(appointmentID, patientID);
		if (appointment != null) {
			appointment.setNote(note);
			appointment.setStatus(AppointmentStatus.CANCELLED);
			// Prepare and send an email confirmation to the patient about the
			// appointment cancellation
			Patient patient = userServiceInterface.getBasicPatientDetails(patientID);
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("user", patient.getFirstName() + " " + patient.getLastName());
			map1.put("appointment", appointment);

			/*String body = emailSender.prepareBody(EmailTemplate.APPOINTMENT_CANCELLED, map1);
			emailStructure.setBody(body);
			emailStructure.setSenderEmail(encryptableProps.getProperty("email.id"));
			emailStructure.setSubject("Last login info");
			emailStructure.addRecipient(patient.getEmail());
			emailSender.sendEmail(emailStructure);*/

			calendarEventHandler.deleteActualEvent(appointment.getActualCalEventID());
			calendarEventHandler.deleteFakeEvent(appointment.getFakeCalEventID());
			map.put("Success", "Success");
		} else {
			map.put("error", "Invalid appoint id or patient id");
		}

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/update/{patientID}", method = RequestMethod.POST, params = {
			"status=COMPLETED"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> updateAppointmentToCompletedByAdmin(@PathVariable("patientID") long patientID,
			@RequestParam(name = "appointmentID") long appointmentID, @RequestParam(name = "note") String note,
			@RequestParam(name = "amountPaid") BigDecimal amountPaid) {
		Map<String, Object> map = new HashMap<>();
		Appointment appointment = userServiceInterface.getAppointmentByIDandPatientID(appointmentID, patientID);
		if (appointment != null) {
			appointment.setNote(note);
			appointment.setAmountPaid(amountPaid);
			appointment.setStatus(AppointmentStatus.COMPLETED);
			map.put("Success", "Success");
		} else {
			map.put("error", "Invalid appoint id or patient id");
		}

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

	}

}
