package com.dentist.webapp;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dentist.mail.EmailGenerator;
import com.dentist.mail.EmailStructure;
import com.dentist.mail.EmailTemplate;

/**
 * 
 *
 * @author Satyanandana Srikanthvarma Vadapalli
 * @email srikanthvarma.vadapalli@gmail.com
 * @version 1.0
 * @since Apr 30, 20164:36:09 PM
 * @git
 * 
 */

@RestController
@EnableAsync
@Transactional
@RequestMapping(value = "/visitor")
public class VisitorController {

	@Autowired
	private EmailGenerator emailSender;
	@Autowired
	private EmailStructure emailStructure;
	@Autowired
	@Qualifier("encryptableProps")
	private Properties encryptableProps;

	@RequestMapping(value = "/contactinfo", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> getContactInfo(@RequestParam(name = "name") String name, @RequestParam(name = "email") String email,
			@RequestParam(name = "number") String number, @RequestParam(name = "summary") String summary) {
		Map<String, String> map = new HashMap<>();

		// Prepare and send appointment request created email to admin
		Map<String, Object> emailMap = new HashMap<String, Object>();
		emailMap.put("user", "Admin");
		emailMap.put("name", name);
		emailMap.put("email", email);
		emailMap.put("number", number);
		emailMap.put("summary", summary);

		String body = emailSender.prepareBody(EmailTemplate.CONTACT_US, emailMap);
		emailStructure.setBody(body);
		emailStructure.setSenderEmail(encryptableProps.getProperty("email.id"));
		emailStructure.setSubject("New Contact Query");
		emailStructure.addRecipient(encryptableProps.getProperty("admin.email"));
		emailSender.sendEmail(emailStructure);

		map.put("Success", "Success");

		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
	}

}
