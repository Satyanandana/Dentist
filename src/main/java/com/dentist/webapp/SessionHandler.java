package com.dentist.webapp;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.dentist.domain.UserAuthentication;
import com.dentist.service.CustomUserDetails;
@Service
public class SessionHandler {
	
	private static final Logger logger = Logger.getLogger(SessionHandler.class);
			
	public static void handleSession(SessionRegistry sessionRegistry,AuthenticationSuccessHandler successHandler,HttpServletRequest request,HttpServletResponse response,UserAuthentication user,PooledPBEStringEncryptor encryptor) throws IOException, ServletException{
		
		List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(user.getUserRole().toString());
		UserDetails userDetails = new CustomUserDetails(user);

		Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, user.getUserPwd(), authorities);
		Iterator<SessionInformation> i = sessionRegistry.getAllSessions(auth.getPrincipal(), true).iterator();
		while (i.hasNext()) {
			SessionInformation si = i.next();
			si.expireNow();
						
		}
		sessionRegistry.registerNewSession(request.getSession().getId(), auth.getPrincipal());
		logger.info("added user to the spring session registry");
		SecurityContextHolder.getContext().setAuthentication(auth);
		logger.info("added user to the spring security context holder");
		request.getSession().setAttribute("user", user.getUserEmail());
		logger.info("add user to the http servlet session");
		Cookie cookieUserId  = new Cookie("USER",encryptor.encrypt(user.getUserEmail()));
		cookieUserId.setMaxAge(24 * 60 * 60);  // 24 hours.
		cookieUserId.setComment("www.kangdentalnewton.com");
		cookieUserId.setHttpOnly(true);
		cookieUserId.setPath("/Dentist/");
	//  uncomment the below lines during production
	//	cookieUserId.setDomain("https://www.kangdentalnewton.com");
	//	cookieUserId.setSecure(true);
		response.addCookie(cookieUserId);
        logger.info("add user to the cookie");
		successHandler.onAuthenticationSuccess(request, response, auth);
		logger.info("Redirecting to where the request came from  "+request.getPathTranslated());
	}

}
