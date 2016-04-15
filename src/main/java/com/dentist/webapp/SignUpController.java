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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jasypt.hibernate4.encryptor.HibernatePBEStringEncryptor;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dentist.domain.Patient;
import com.dentist.domain.UserAuthentication;
import com.dentist.googlecalendar.CalendarEventHandler;
import com.dentist.mail.EmailGenerator;
import com.dentist.mail.EmailStructure;
import com.dentist.mail.EmailTemplate;
import com.dentist.service.UserServiceInterface;
import com.dentist.util.UrlSafeEncryption;
import com.dentist.util.WebUtility;

/**
 * Handles requests for the application signup page.
 */
@Controller
@Transactional
@EnableAsync
@RequestMapping(value = "/signup")
public class SignUpController {

	private static final Logger LOGGER = Logger.getLogger(SignUpController.class);

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
	@Autowired(required = true)
	private HibernatePBEStringEncryptor encryptor;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String signUpForm(HttpServletRequest request, HttpServletResponse response, Model model,
			@CookieValue(name = "USER", required = false) String userCookie) {

		/*
		 * // Prepare and send Welcome Email Map<String, Object> map = new
		 * HashMap<String, Object>(); map.put("user", "Srikanthvarma"); String
		 * body1 = emailSender.prepareBody(EmailTemplate.WELCOME_EMAIL, map);
		 * emailStructure.setBody(body1);
		 * emailStructure.setSenderEmail("gtsatyansv@gmail.com");
		 * emailStructure.setSubject("Welcome to Dr.Kang Dentistry");
		 * emailStructure.addRecipient("srikanthvarma.vadapalli@gmail.com");
		 * 
		 * String resource = servletContext.getRealPath(
		 * "/WEB-INF/velocity/emailtemplates/welcome/2.png"); File file = new
		 * File(resource); emailStructure.addInlineImages("image", file); //
		 * emailStructure.addAttachment("welcome.vm", file); Future<Boolean>
		 * sent1 = emailSender.sendEmail(emailStructure);
		 */

		LOGGER.debug("Checking for the user in session");
		boolean userSession = (request.getSession().getAttribute("user") != null);
		LOGGER.debug("User is in the session :" + userSession);
		if (userCookie != null && !userSession) {
			LOGGER.debug("check user in the cookie");
			String userEmail = encryptor.decrypt(userCookie);
			UserAuthentication userAuth = userServiceInterface.getUserAuthenticationInfoByEmail(userEmail);
			if (userAuth != null) {
				LOGGER.debug("adding the user to spring session registry w.r.t cookie data");

				try {
					SessionHandler.handleSession(sessionRegistry, successHandler, request, response, userAuth,
							encryptor);
				} catch (IOException e) {
					LOGGER.error("", e);
				} catch (ServletException e) {
					LOGGER.error("", e);
				}

				return null;

			} else {
				LOGGER.debug("unable to add the user to spring session w.r.t cookie data");
			}
		} else if (userSession) {
			LOGGER.debug("Redirecting to /");
			return "redirect:/";
		}

		// Required for spring form model attribute
		Patient patient = new Patient();
		model.addAttribute("patient", patient);
		LOGGER.debug("To signup page ");
		return "signup";

	}

	@RequestMapping(value = "/process", method = RequestMethod.POST)
	public String postSignUP(HttpServletRequest request, HttpServletResponse response, Model model,
			@ModelAttribute Patient patient, @RequestParam(name = "dob") String dob)
			throws IOException, ServletException, InterruptedException {

		boolean valid = false;
		boolean validEmail = ServerSideValidations.validateEmail(patient.getUserAuth().getUserEmail(), model, null,
				"errorEmail", "Invalid email address");
		boolean validPassword = ServerSideValidations.validatePassword(patient.getUserAuth().getUserPwd(), model, null,
				"errorPassword", "Invalid password");
		boolean validFirstname = ServerSideValidations.validateName(patient.getFirstName(), model, null,
				"errorFirstName", "Firstname Should contain only alphabets");
		boolean validLastname = ServerSideValidations.validateName(patient.getLastName(), model, null, "errorLastName",
				"Lasttname Should contain only alphabets");
		boolean validMiddlename = ServerSideValidations.validateName(patient.getMiddleName(), model, null,
				"errorMiddleName", "Middletname Should contain only alphabets");
		boolean validPhoneNumber = ServerSideValidations.validPhoneNumber(String.valueOf(patient.getPhoneNumber()),
				model, null, "errorPhoneNumber", "Invalid phone number");

		if (validEmail && validFirstname && validLastname && validMiddlename && validPassword && validPhoneNumber) {
			valid = true;
		}

		LocalDate dateOfBirth = WebUtility.getLocalDateFromHtmlDate(dob);
		if (dateOfBirth != null) {
			patient.setDateOfBirth(dateOfBirth);
		} else {
			model.addAttribute("errorDate", "Please select a valid date");
			valid = false;
		}

		if (valid) {
			LOGGER.debug("Request parameters are valid.Checking for existing account with given email...");
			Patient patientCreated = userServiceInterface.signUp(patient, request, model);
			if (patientCreated != null) {
				userServiceInterface.setPatient(patientCreated);
				LOGGER.debug("new user account created successfully");

				// Prepare and send Welcome Email
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("user", "Srikanthvarma");
				String body1 = emailSender.prepareBody(EmailTemplate.WELCOME_EMAIL, map);
				emailStructure.setBody(body1);
				emailStructure.setSenderEmail("gtsatyansv@gmail.com");
				emailStructure.setSubject("Welcome to Dr.Kang Dentistry");
				emailStructure.addRecipient(patientCreated.getEmail());

				emailSender.sendEmail(emailStructure);

				// Prepare and send AccountVerification Email
				map.clear();
				map.put("user", patientCreated.getFirstName() + " " + patientCreated.getLastName());
				map.put("id",
						UrlSafeEncryption.encrypt(encryptor.encrypt(patientCreated.getUserAuth().getUserEmail())));
				map.put("key",
						UrlSafeEncryption.encrypt(encryptor.encrypt(patientCreated.getUserAuth().getVerifyKey())));
				LOGGER.debug(map.get("key"));
				LOGGER.debug(encryptor.decrypt(UrlSafeEncryption.decrypt((String) map.get("key"))));
				LOGGER.debug(map.get("id"));
				LOGGER.debug(encryptor.decrypt(UrlSafeEncryption.decrypt((String) map.get("id"))));
				String body2 = emailSender.prepareBody(EmailTemplate.VERIFY_ACCOUNT_EMAIL, map);
				emailStructure.setBody(body2);
				emailStructure.setSenderEmail("gtsatyansv@gmail.com");
				emailStructure.setSubject("Verify your account with Dr.Kang Dentistry");
				emailStructure.addRecipient(patientCreated.getEmail());

				emailSender.sendEmail(emailStructure);

				UserAuthentication userAuth = userServiceInterface.getUserAuthenticationInfoByEmail(patient.getEmail());
				SessionHandler.handleSession(sessionRegistry, successHandler, request, response, userAuth, encryptor);

				// Wait until the emails are sent
				/*
				 * while (!(sent1.isDone() && sent2.isDone())) { LOGGER.info(
				 * "waiting to send email ..."); Thread.sleep(10);
				 * //10-millisecond pause between each check }
				 */

				LOGGER.debug("Sent welcome and account verification emails");
				return null;
			}
		}

		return "signup";
	}

}
