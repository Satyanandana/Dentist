package com.dentist.webapp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/appointments")
public class AppointmentInfoController {
	
	private static final Logger logger = Logger.getLogger(AppointmentInfoController.class);
	@Autowired
	private UserServiceInterface userServiceInterface;
	
}
