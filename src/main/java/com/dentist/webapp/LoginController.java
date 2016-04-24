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
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jasypt.hibernate4.encryptor.HibernatePBEStringEncryptor;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dentist.domain.AccountStatus;
import com.dentist.domain.Patient;
import com.dentist.domain.UserAuthentication;
import com.dentist.geolocation.IpAddressGeoLocation;
import com.dentist.geolocation.ServerLocation;
import com.dentist.mail.EmailGenerator;
import com.dentist.mail.EmailStructure;
import com.dentist.mail.EmailTemplate;
import com.dentist.service.UserServiceInterface;
import com.dentist.util.UrlSafeEncryption;
import com.dentist.util.WebUtility;

/**
 * Handles requests for the application home page.
 */
@Controller
@EnableAsync
@Transactional
@RequestMapping(value = "/login")
public class LoginController {

	private static final Logger LOGGER = Logger.getLogger(LoginController.class);

	@Autowired
	ServletContext servletContext;
	@Autowired
	private AuthenticationSuccessHandler successHandler;
	@Autowired
	private SessionRegistry sessionRegistry;
	@Autowired
	private UserServiceInterface userServiceInterface;
	@Autowired
	private EmailGenerator emailSender;
	@Autowired
	private EmailStructure emailStructure;
	@Autowired
	private HibernatePBEStringEncryptor encryptor;
	@Autowired
	private IpAddressGeoLocation geoLocation;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String loginForm(HttpServletRequest request, HttpServletResponse response, Model model,
			@CookieValue(name = "USER", required = false) String userCookie, @RequestParam(name = "action", defaultValue = "login") String action) {
		LOGGER.info("processing get request to /login/form");
		LOGGER.debug("Checking for the user in session");
		boolean userSession = (request.getSession().getAttribute("user") != null);
		LOGGER.debug("User is in the session :" + userSession);
		if (userCookie != null && !userSession) {
			LOGGER.debug("check user in the cookie");
			userCookie = encryptor.decrypt(userCookie);
			String[] parts = userCookie.split("-");
			String userEmail = parts[0];
			String sessionID = parts[1];
			UserAuthentication userAuth = userServiceInterface.getUserAuthenticationInfoByEmail(userEmail);
			if (userAuth != null && userAuth.getPrevSessionID().equals(sessionID)) {
				if (userAuth.getAccountStatus().equals(AccountStatus.ACTIVE)) {
					LOGGER.debug("adding the user to spring session registry w.r.t cookie data");

					try {
						Patient patient = userServiceInterface.getPatientInfoById(userAuth.getUserID());
						SessionHandler.handleSession(sessionRegistry, successHandler, request, response, userAuth, encryptor, patient);
					} catch (IOException e) {
						LOGGER.error("", e);
					} catch (ServletException e) {
						LOGGER.error("", e);
					}

					return null;
				}
			} else {
				LOGGER.debug("unable to add the user to spring session w.r.t cookie data");
			}
		} else if (userSession) {
			LOGGER.debug("Redirecting to /");
			return "redirect:/";
		}

		LOGGER.debug("To login page .... ");
		model.addAttribute("patient", new Patient());
		model.addAttribute("action", "login");
		return "login";

	}

	@RequestMapping(value = "/process", method = RequestMethod.POST)
	public String customlogin(HttpServletRequest request, HttpServletResponse response, Device device, Locale locale, Model model,
			@RequestParam String email, @RequestParam String password) throws IOException, ServletException {
		LOGGER.info("processing post requset to /login/process and athuenticate the user if email and password are valid credentials");
		boolean valid = false;
		boolean validEmail = ServerSideValidations.validateEmail(email, model, null, "errorEmail", "Invalid email address");
		boolean validPassword = ServerSideValidations.validatePassword(password, model, null, "errorPassword", "Invalid password");

		if (validEmail && validPassword) {
			valid = true;
			LOGGER.debug("valid email and password");
		} else {
			model.addAttribute("error", "Either email Id or the password is wrong");
		}

		if (valid) {
			LOGGER.debug("Checking whether a user exists with the given password");
			UserAuthentication userAuth = userServiceInterface.getUserAuthenticationInfoByEmail(email);
			if (userAuth != null) {
				if (userAuth.getUserPwd().equals(password)) {
					if (userAuth.getAccountStatus().equals(AccountStatus.ACTIVE)) {
						LOGGER.debug("valid user credentials");
						LOGGER.debug("adding user to spring session registry");
						Patient patient = userServiceInterface.getPatientInfoById(userAuth.getUserID());
						SessionHandler.handleSession(sessionRegistry, successHandler, request, response, userAuth, encryptor, patient);
						// get the location of user with IP address
						String IpAddress = WebUtility.getIpAddress(request);
						// uncomment the below line in production
						// ServerLocation serverLocation
						// =geoLocation.getLocation(IpAddress);
						// Comment the below line in production
						ServerLocation serverLocation = geoLocation.getLocation("65.96.159.13");

						if (serverLocation != null) {

							// Prepare and send last login info email
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("user", patient.getFirstName() + " " + patient.getLastName());
							map.put("time", new DateTime().toString(DateTimeFormat.forPattern("EEE, dd MMM yyyy HH:mm:ss z")));
							map.put("device", WebUtility.getDevice(device));
							map.put("ipAddress", IpAddress);
							map.put("location", serverLocation);

							String body = emailSender.prepareBody(EmailTemplate.LAST_LOGIN_EMAIL, map);
							emailStructure.setBody(body);
							emailStructure.setSenderEmail("gtsatyansv@gmail.com");
							emailStructure.setSubject("Last login info");
							emailStructure.addRecipient(userAuth.getUserEmail());
							emailSender.sendEmail(emailStructure);
						}

						return null;
					} else if (userAuth.getAccountStatus().equals(AccountStatus.NOT_ACTIVATED_YET)) {
						model.addAttribute("error", "Please verify your email Id by clicking on the link that we sent to your email");
						// Prepare and send email for verifying email
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("verifyKey", userAuth.getVerifyKey());

						String body = emailSender.prepareBody(EmailTemplate.VERIFY_ACCOUNT_EMAIL, map);
						emailStructure.setBody(body);
						emailStructure.setSenderEmail("gtsatyansv@gmail.com");
						emailStructure.setSubject("Verify your email account");
						emailStructure.addRecipient(userAuth.getUserEmail());
						emailSender.sendEmail(emailStructure);

					} else if (userAuth.getAccountStatus().equals(AccountStatus.BLOCKED)) {
						model.addAttribute("error", "Your account has been blocked.Please contact the admin");
					}
				} else {
					model.addAttribute("error", "Either email Id or the password is wrong");
				}
			} else {
				model.addAttribute("error", "Either email Id or the password is wrong");
			}
		}
		model.addAttribute("serverTime", new DateTime().toString());
		LOGGER.debug("To login page .... ");
		model.addAttribute("patient", new Patient());
		model.addAttribute("action", "login");
		return "login";
	}

	@ResponseBody
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> getForgetPassword(Model model, @RequestParam(name = "email") String email) {
		Map<String, String> map = new HashMap<String, String>();
		boolean validEmail = ServerSideValidations.validateEmail(email, null, map, "errorEmail", "Invalid email address");

		if (validEmail) {
			UserAuthentication userAuth = userServiceInterface.getUserAuthenticationInfoByEmail(email);
			if (userAuth != null && userAuth.getUserEmail() != null) {
				Patient patient = userServiceInterface.getBasicPatientDetails(userAuth.getUserID());
				// Prepare and send Welcome Email
				Map<String, Object> emailMap = new HashMap<String, Object>();
				emailMap.put("user", patient.getFirstName() + " " + patient.getLastName());
				emailMap.put("username", userAuth.getUserEmail());
				emailMap.put("password", userAuth.getUserPwd());
				String body1 = emailSender.prepareBody(EmailTemplate.FORGOT_PASSWORD, emailMap);
				emailStructure.setBody(body1);
				emailStructure.setSenderEmail("gtsatyansv@gmail.com");
				emailStructure.setSubject("Forgot Password");
				emailStructure.addRecipient(userAuth.getUserEmail());

				emailSender.sendEmail(emailStructure);

				map.put("success", "We sent your credentials to your email ID.Please check your inbox");
			} else {
				map.put("noaccount", "We dont have an account matching the email ID");
			}
		} else {
			map.put("invalid", "Invalid email address");
		}

		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);

	}

	@RequestMapping(value = "/verifyemail", method = RequestMethod.GET)
	public String verifyEmail(Model model, @RequestParam(name = "key") String key, @RequestParam(name = "id") String id) {

		try {

			String decryptKey = encryptor.decrypt(UrlSafeEncryption.decrypt(key.trim()));
			String decryptEmail = encryptor.decrypt(UrlSafeEncryption.decrypt(id.trim()));
			UserAuthentication userAuth = userServiceInterface.getUserAuthenticationInfoByEmail(decryptEmail);
			if (userAuth != null) {
				String deCryptVerifyKey = userAuth.getVerifyKey();
				if (deCryptVerifyKey.equals(decryptKey)) {
					userAuth.setAccountStatus(AccountStatus.ACTIVE);
					model.addAttribute("success", "Successfully activate your account.Please login");
				}
			}
		} catch (Exception e) {
			LOGGER.error("", e);
			model.addAttribute("error", "unable to activate your account.Please try again");
		}
		return null;
	}

}
