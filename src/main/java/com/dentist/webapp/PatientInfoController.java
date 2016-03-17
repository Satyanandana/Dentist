package com.dentist.webapp;

import java.util.HashMap;
import java.util.Map;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dentist.domain.Patient;
import com.dentist.service.CustomUserDetails;
import com.dentist.service.UserServiceInterface;
/**
* 
*
* @author  Satyanandana Srikanthvarma Vadapalli
* @email srikanthvarma.vadapalli@gmail.com
* @version 1.0
* @since   Mar 17, 20161:10:28 AM
*       
*/
@RestController
@RequestMapping("/patient")
public class PatientInfoController {
	private static final Logger logger = Logger.getLogger(PatientInfoController.class);
	@Autowired
	private UserServiceInterface userServiceInterface;
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/info", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Object>> getPersonalInfo(Model model){
		logger.debug("processing request to get personal info");
		Map<String, Object> info = new HashMap<String, Object>();
		Patient patient =null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!auth.getPrincipal().equals("anonymousUser")){
			CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		patient = userServiceInterface.getPatientInfoById(user.getUserID());
		info.put("firstName", patient.getFirstName());
		info.put("middleName", patient.getMiddleName());
		info.put("lastName", patient.getLastName());
		info.put("dateOfBirth", patient.getDateOfBirth().toString());
		info.put("phoneNumber", patient.getPhoneNumber());
		info.put("email", patient.getEmail());
		}
		return new ResponseEntity<Map<String, Object>>(info,HttpStatus.OK);
			
	}
	

}
