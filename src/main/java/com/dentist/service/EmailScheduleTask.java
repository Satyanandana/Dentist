package com.dentist.service;
/**
* 
*
* @author  Satyanandana Srikanthvarma Vadapalli
* @email srikanthvarma.vadapalli@gmail.com
* @version 1.0
* @since   Mar 29, 201612:04:48 AM
* @git 
*      
*/

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dentist.domain.Appointment;
import com.dentist.domain.AppointmentStatus;
import com.dentist.domain.Patient;
import com.dentist.mail.EmailGenerator;
import com.dentist.mail.EmailStructure;
import com.dentist.mail.EmailTemplate;

@Component
public class EmailScheduleTask {
	private static final Logger LOGGER = Logger.getLogger(EmailScheduleTask.class);
	@Autowired
	private UserServiceInterface userServiceInterface;
	@Autowired
	private EmailGenerator emailSender;
	@Autowired
	private EmailStructure emailStructure;
	@Autowired
	@Qualifier("encryptableProps")
	private Properties encryptableProps;

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	/*
	 * Example patterns: "0 0 * * * *" = the top of every hour of every day.
	 * "10 * * * * *" = every ten seconds. "0 0 8-10 * * *" = 8, 9 and 10
	 * o'clock of every day. "0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30 and 10
	 * o'clock every day. "0 0 9-17 * * MON-FRI" = on the hour nine-to-five
	 * weekdays "0 0 0 25 12 ?" = every Christmas Day at midnight
	 */

	@Scheduled(cron = "0 30 21 * * *")
	public void reportCurrentTime() {

		List<Appointment> appointments = userServiceInterface.getAllAppointments();

		for (Appointment appointment : appointments) {
			if (appointment.getStatus().equals(AppointmentStatus.CONFIRMED) && appointment.getAppointmentStartTime().isAfterNow()
					&& appointment.getAppointmentStartTime().isBefore(new DateTime().plusHours(24))) {

				// Prepare and send an email reminder to the patient about the
				// appointment.
				Patient patient = userServiceInterface.getBasicPatientDetails(appointment.getPatientID());
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("user", patient.getFirstName() + " " + patient.getLastName());
				map1.put("appointment", appointment);

				String body = emailSender.prepareBody(EmailTemplate.APPOINTMENT_REMIENDER, map1);
				emailStructure.setBody(body);
				emailStructure.setSenderEmail(encryptableProps.getProperty("email.id"));
				emailStructure.setSubject("Appointment reminder");
				emailStructure.addRecipient(patient.getEmail());
				emailSender.sendEmail(emailStructure);
			}

		}

		LOGGER.info("The time is now " + dateFormat.format(new Date()));
	}
}