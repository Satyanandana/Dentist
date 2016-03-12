package com.dentist.webapp;

import java.io.IOException;
import java.util.Locale;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dentist.domain.Patient;
import com.dentist.domain.UserAuthentication;
import com.dentist.util.CalendarEventHandler;
import com.dentist.util.EmailGenerator;
import com.dentist.util.EmailStructure;
import com.dentist.service.UserServiceInterface;
import com.dentist.service.WebUtility;

/**
 * Handles requests for the application signup page.
 */
@Controller
@RequestMapping(value = "/signup")
public class SignUpController {

	private static final Logger logger = Logger.getLogger(SignUpController.class);

	@Autowired
	ServletContext servletContext;
	@Autowired
	private AuthenticationSuccessHandler successHandler;
	@Autowired
	private SessionRegistry sessionRegistry;
	@Autowired
	private CalendarEventHandler calendarEventHandler;
	@Autowired
	private UserServiceInterface userServiceInterface;
	@Autowired
	private EmailGenerator emailSender;
	@Autowired
	private EmailStructure emailStructure;
	@Autowired
	private WebUtility webUtility;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String signUpForm(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is " + locale.toString());
		Patient patient = new Patient();
		model.addAttribute("patient", patient);

		return "signup";

	}

	@RequestMapping(value = "/process", method = RequestMethod.POST)
	public String postSignUP(HttpServletRequest request, HttpServletResponse response,@ModelAttribute Patient patient,@RequestParam String dob,Model model )
			throws IOException, ServletException {


		LocalDate dateOfBirth = webUtility.getLocalDatefromHtmlDate(dob);
		if(dateOfBirth!=null){
			patient.setDateOfBirth(dateOfBirth);
		}

		Patient patientCreated = userServiceInterface.signUp(patient, request);
		if(patientCreated!=null){
			userServiceInterface.setPatient(patientCreated);
			UserAuthentication userAuth = userServiceInterface.getUserAuthenticationInfoByEmail(patient.getEmail());
			SessionHandler.handleSession(sessionRegistry, successHandler, request, response, userAuth);
			return null;
		}


		return "signup";
	}

}
