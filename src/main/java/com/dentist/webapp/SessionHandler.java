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
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jasypt.hibernate4.encryptor.HibernatePBEStringEncryptor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import com.dentist.domain.Patient;
import com.dentist.domain.UserAuthentication;
import com.dentist.service.CustomUserDetails;

@Service
public class SessionHandler {

	private static final Logger LOGGER = Logger.getLogger(SessionHandler.class);

	public static void handleSession(SessionRegistry sessionRegistry, AuthenticationSuccessHandler successHandler, HttpServletRequest request,
			HttpServletResponse response, UserAuthentication user, HibernatePBEStringEncryptor encryptor, Patient patient)
			throws IOException, ServletException {

		List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(user.getUserRole().toString());
		UserDetails userDetails = new CustomUserDetails(user);
		Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, user.getUserPwd(), authorities);

		Iterator<SessionInformation> i = sessionRegistry.getAllSessions(auth.getPrincipal(), true).iterator();
		while (i.hasNext()) {
			SessionInformation si = i.next();
			si.expireNow();
		}
		sessionRegistry.registerNewSession(request.getSession().getId(), auth.getPrincipal());
		LOGGER.debug("added user to the spring session registry");
		user.setPrevSessionID(request.getSession().getId());
		LOGGER.debug("updated new sessionID in the database");
		SecurityContextHolder.getContext().setAuthentication(auth);
		LOGGER.debug("added user to the spring security context holder");
		request.getSession().setAttribute("user", user.getUserEmail());
		request.getSession().setAttribute("role", user.getUserRole());
		request.getSession().setAttribute("name", patient.getFirstName() + " " + patient.getLastName());
		LOGGER.debug("added user to the http servlet session");
		Cookie cookieUserId = new Cookie("USER", encryptor.encrypt(user.getUserEmail() + "-" + request.getSession().getId()));
		cookieUserId.setMaxAge(24 * 60 * 60); // 24 hours.
		cookieUserId.setComment("www.kangdentalnewton.com");
		cookieUserId.setHttpOnly(true);
		cookieUserId.setPath(request.getContextPath() + "/");
		// uncomment the below lines during production
		// cookieUserId.setDomain("https://www.kangdentalnewton.com");
		// cookieUserId.setSecure(true);
		response.addCookie(cookieUserId);
		LOGGER.debug("added user to the cookie");
		successHandler.onAuthenticationSuccess(request, response, auth);
		LOGGER.debug("Redirecting to where the request came from i.e " + request.getPathTranslated());
	}

}
