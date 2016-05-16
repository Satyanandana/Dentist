package com.dentist.webapp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	@RequestMapping(value = "/contactinfo", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> getContactInfo(@RequestParam(name = "name") String name, @RequestParam(name = "email") String email,
			@RequestParam(name = "number") String number, @RequestParam(name = "summary") String summary) {
		Map<String, String> map = new HashMap<>();
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
	}

}
