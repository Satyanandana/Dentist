package com.dentist.webapp;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
	public static void handleSession(SessionRegistry sessionRegistry,AuthenticationSuccessHandler successHandler,HttpServletRequest request,HttpServletResponse response,UserAuthentication user) throws IOException, ServletException{
		List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(user.getUserRole().toString());
		UserDetails userDetails = new CustomUserDetails(user);

		Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, user.getUserPwd(), authorities);
		Iterator<SessionInformation> i = sessionRegistry.getAllSessions(auth.getPrincipal(), true).iterator();
		while (i.hasNext()) {
			SessionInformation si = i.next();
			si.expireNow();
		}
		sessionRegistry.registerNewSession(request.getSession().getId(), auth.getPrincipal());

		SecurityContextHolder.getContext().setAuthentication(auth);

		successHandler.onAuthenticationSuccess(request, response, auth);
	}

}
