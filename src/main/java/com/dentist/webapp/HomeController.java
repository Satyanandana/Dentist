package com.dentist.webapp;
/**
* 
*
* @author  Satyanandana Srikanthvarma Vadapalli
* @email srikanthvarma.vadapalli@gmail.com
* @version 1.0
* @since   Mar 17, 20161:10:28 AM
*       
*/
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dentist.service.CustomUserDetails;
import com.dentist.service.UserServiceInterface;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = Logger.getLogger(HomeController.class);

	@Autowired
	private UserServiceInterface userServiceInterface;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!auth.getPrincipal().equals("anonymousUser")){
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();

		logger.info("handling get request to /home   " + user.getUserEmail());
		logger.info("handling get request to /home   " + user.getUserRole());
		logger.info("handling get request to /home   " + user.getUserID());

		logger.info("Welcome home! The client locale is " + locale.toString());

		// BasicProfile user1= userServiceInterface.findUserById(2);
		}

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String homeForm(Locale locale, Model model) {

		/*
		 * Authentication auth =
		 * SecurityContextHolder.getContext().getAuthentication();
		 * CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		 * 
		 * logger.info("handling post request to /home" + user.getUser_email());
		 */

		logger.info("handling post request to /home");

		return "home";
	}

}
