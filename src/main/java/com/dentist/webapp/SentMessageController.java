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

import com.dentist.domain.SentMessage;
import com.dentist.domain.SentMessage;
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
@RequestMapping("/sentmessages")
public class SentMessageController {
	
	private static final Logger LOGGER = Logger.getLogger(SentMessageController.class);
	@Autowired
	private UserServiceInterface userServiceInterface;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{messageID}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SentMessage> getSentMessageByID(@PathVariable("messageID")long messageID ){
		LOGGER.info("processing get request to /SentMessages/{messageID}");
		SentMessage SentMessage = userServiceInterface.getSentMessageByID(messageID);
		return new ResponseEntity<SentMessage>(SentMessage, HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/patient/{patientID}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SentMessage>> getSentMessagesByPatientID(@PathVariable("patientID")long patientID ){
		LOGGER.info("processing get request to /sentmessages/patient/{patientID}");
		List<SentMessage> sentMessages = userServiceInterface.getSentMessagesByPatientID(patientID);
		return new ResponseEntity<List<SentMessage>>(sentMessages, HttpStatus.OK);
	}
}
