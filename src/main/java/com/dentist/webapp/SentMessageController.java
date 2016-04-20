package com.dentist.webapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
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
import com.dentist.domain.ReceivedMessage;
import com.dentist.domain.SentMessage;
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
@RequestMapping("/sentmessages")
public class SentMessageController {

	private static final Logger LOGGER = Logger.getLogger(SentMessageController.class);
	@Autowired
	private UserServiceInterface userServiceInterface;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{messageID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SentMessage> getSentMessageByID(@PathVariable("messageID") long messageID) {
		LOGGER.info("processing get request to /SentMessages/{messageID}");
		SentMessage SentMessage = userServiceInterface.getSentMessageByID(messageID);
		return new ResponseEntity<SentMessage>(SentMessage, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ROLE_USER')") // change to ROLE_ADMIN
	@RequestMapping(value = "/patient/{patientID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SentMessage>> getSentMessagesByPatientID(@PathVariable("patientID") long patientID) {
		LOGGER.info("processing get request to /sentmessages/patient/{patientID}");
		List<SentMessage> sentMessages = userServiceInterface.getSentMessagesByPatientID(patientID);
		return new ResponseEntity<List<SentMessage>>(sentMessages, HttpStatus.OK);
	}

	/*******************************************************
	 * POST API END POINTS FOR SENDING MESSAGES
	 ******************************************************/

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/sendtodoc", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> sendMessageToDoctor(@RequestParam(name = "msg") String msg) {

		Map<String, String> map = new HashMap<>();
		boolean valid = false;

		if (msg.length() <= 1000 || msg.isEmpty()) {
			valid = true;
		} else {
			map.put("errorMsg", "the length of the message should be between 0 and 1000");
		}

		if (valid) {

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
			Patient patient = userServiceInterface.getPatientInfoById(user.getUserID());
			SentMessage message = new SentMessage();
			message.setMsg(msg);
			message.setSender(patient);
			message.setSentTime(new DateTime());
			userServiceInterface.setSentMessage(message);
			map.put("success", "success");
		}

		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/sendtopatient/{patientID}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> sendMessageToPatient(@RequestParam(name = "msg") String msg,
			@PathVariable("patientID") long patientID) {

		Map<String, String> map = new HashMap<>();
		boolean valid = false;

		if (msg.length() <= 1000 || msg.isEmpty()) {
			valid = true;
		} else {
			map.put("error", "the length of the message should be between 0 and 1000");
		}

		if (valid) {
			Patient patient = userServiceInterface.getPatientInfoById(patientID);
			if (patient != null) {
				ReceivedMessage message = new ReceivedMessage();
				message.setMsg(msg);
				message.setReceivedTime(new DateTime());
				message.setReceiver(patient);
				map.put("success", "success");
			} else {
				map.put("error", "Invalid Patient ID");
			}

		}

		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);

	}
}
