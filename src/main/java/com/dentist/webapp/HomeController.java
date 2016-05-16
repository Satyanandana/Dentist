package com.dentist.webapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jasypt.hibernate4.encryptor.HibernatePBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dentist.domain.AccountStatus;
import com.dentist.domain.Patient;
import com.dentist.domain.UserAuthentication;
import com.dentist.service.UserServiceInterface;

/**
 * Handles requests for the application home page.
 */
@Controller
@EnableAsync
@Transactional
public class HomeController {
	@Autowired
	private HibernatePBEStringEncryptor encryptor;
	@Autowired
	private AuthenticationSuccessHandler successHandler;
	@Autowired
	private SessionRegistry sessionRegistry;

	private static final Logger LOGGER = Logger.getLogger(HomeController.class);

	@Autowired
	private UserServiceInterface userServiceInterface;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	public String home(HttpServletRequest request, HttpServletResponse response, Model model,
			@CookieValue(name = "USER", required = false) String userCookie) {

		LOGGER.info("processing get request to / or /home");
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

					return "home";
				}
			} else {
				LOGGER.debug("unable to add the user to spring session w.r.t cookie data");
			}
		} else if (userSession) {

			return "home";
		}

		return "home";
	}

}
