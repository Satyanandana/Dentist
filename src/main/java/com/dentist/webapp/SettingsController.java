package com.dentist.webapp;
/**
* 
*
* @author  Satyanandana Srikanthvarma Vadapalli
* @email srikanthvarma.vadapalli@gmail.com
* @version 1.0
* @since   Apr 27, 20163:18:18 PM
* @git 
*      
*/

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dentist.domain.Patient;
import com.dentist.domain.UserAuthentication;
import com.dentist.mail.EmailGenerator;
import com.dentist.mail.EmailStructure;
import com.dentist.mail.EmailTemplate;
import com.dentist.service.CustomUserDetails;
import com.dentist.service.UserServiceInterface;

@RestController
@Transactional
@RequestMapping(value = "/settings")
public class SettingsController {

	private static final Logger LOGGER = Logger.getLogger(SettingsController.class);
	@Autowired
	private UserServiceInterface userServiceInterface;
	@Autowired
	private Environment environment;
	@Autowired
	private EmailGenerator emailSender;
	@Autowired
	private EmailStructure emailStructure;
	@Autowired
	@Qualifier("encryptableProps")
	private Properties encryptableProps;

	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	@RequestMapping(value = "/updatepassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> changePassword(@RequestParam(name = "oldPwd") String oldPwd,
			@RequestParam(name = "newPwd") String newPwd) {
		Map<String, String> map = new HashMap<>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		UserAuthentication authentication = userServiceInterface.getUserAuthenticationInfoById(user.getUserID());

		if (authentication.getUserPwd().equals(oldPwd)) {
			boolean validPassword = ServerSideValidations.validatePassword(newPwd, null, map, "error",
					"New password should contain atleast one upper case,one lowercase,on number and the length between 6");
			if (validPassword) {
				authentication.setUserPwd(newPwd);
				// Prepare and send Welcome Email
				Patient patient = userServiceInterface.getBasicPatientDetails(user.getUserID());
				Map<String, Object> map1 = new HashMap<String, Object>();
				if (patient != null) {
					map1.put("user", patient.getFirstName() + " " + patient.getLastName());
					String body1 = emailSender.prepareBody(EmailTemplate.WELCOME_EMAIL, map1);
					emailStructure.setBody(body1);
					emailStructure.setSenderEmail(encryptableProps.getProperty("email.id"));
					emailStructure.setSubject("Your account password has been changed");
					emailStructure.addRecipient(patient.getEmail());
					map.put("Success", "Success");
				} else {
					map1.put("user", "Admin");
					String body1 = emailSender.prepareBody(EmailTemplate.WELCOME_EMAIL, map1);
					emailStructure.setBody(body1);
					emailStructure.setSenderEmail(encryptableProps.getProperty("email.id"));
					emailStructure.setSubject("Your account password has been changed");
					emailStructure.addRecipient(user.getUserEmail());
					map.put("Success", "Success");
				}

			}
		} else {
			map.put("error", "Please enter your old password correctly");
		}
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
	}

}
