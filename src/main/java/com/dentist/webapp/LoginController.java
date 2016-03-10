package com.dentist.webapp;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
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
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dentist.dao.UserDaoInterface;
import com.dentist.domain.AccountStatus;
import com.dentist.domain.AppointmentRequest;
import com.dentist.domain.AppointmentRequestStatus;
import com.dentist.domain.Patient;
import com.dentist.domain.Role;
import com.dentist.domain.UserAuthentication;
import com.dentist.service.CustomUserDetails;
import com.dentist.util.CalendarEventHandler;
import com.dentist.util.EmailGenerator;
import com.dentist.util.EmailStructure;
import com.dentist.util.EmailTemplate;
import com.dentist.util.WebPath;
import com.dentist.service.UserServiceInterface;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

	private static final Logger logger = Logger.getLogger(LoginController.class);

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

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String loginForm(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is " + locale.toString());

		
		 
		 
		
		 String path =	 WebPath.WEB_ROOT.getPath();
		 File file = new File(servletContext.getRealPath("")+
		 "/WEB-INF/velocity/emailtemplates/"+EmailTemplate.WELCOME_EMAIL.getName());
		
		
		 
		 
		 org.joda.time.DateTime startDate = new org.joda.time.DateTime(2016,02, 24, 21, 00,DateTimeZone.forID("America/New_York"));
		 model.addAttribute("serverTime", startDate.toString());
		 
	//	 calendarEventHandler.insertFakeEvent(startDate);
		 calendarEventHandler.insertActualEvent(startDate, "gtsatyansv@gmail.com");
		 

		Map<String, Object> model1 = new HashMap<String, Object>();
		model1.put("user", "Srikanthvarma");
		
		  String body = emailSender.prepareBody(EmailTemplate.WELCOME_EMAIL,
		  model1); emailStructure.setBody(body);
		  emailStructure.setSenderEmail("gtsatyansv@gmail.com");
		  emailStructure.setSubject("Welcome");
		  emailStructure.addRecipient("srikanthvarma.vadapalli@gmail.com");
		  emailStructure.addAttachment("welcome.vm", file);
		//  emailSender.sendEmail(emailStructure);
		 

		return "login";

	}

	@RequestMapping(value = "/process", method = RequestMethod.POST)
	public String Customlogin(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		UserAuthentication user3 = new UserAuthentication();
		user3.setAccountStatus(AccountStatus.NOT_ACTIVATED_YET);
		user3.setUserRole(Role.USER_ROLE);
		user3.setCreationTime(new org.joda.time.DateTime());
		user3.setLastLoginTime(new org.joda.time.DateTime());
		user3.setUserPwd("lancer");
		user3.setUserEmail("gtsatyansv@gmail.com");
		user3.setVerifyKey("empty");
		user3.setUserIp("192.168.225.225");

	  //  userServiceInterface.setUserAuthenticationInfo(user3);
		//logger.info(user3.getUserEmail());
	  //  UserAuthentication user = userServiceInterface.getUserAuthenticationInfoById(1);
	
		Patient patient = new Patient();
		//patient.setUserID(user.getUserID());
	
		patient.setEmail(user3.getUserEmail());
		patient.setDateOfBirth(new LocalDate());
		patient.setPhoneNumber(6178491980L);
		patient.setFirstName("Satyanandana");
		patient.setLastName("Vadapalli");
		patient.setMiddleName("Srikanthvarma");
		patient.setUserAuth(user3);
		
		userServiceInterface.setPatient(patient);	
		
		Patient patient1 = userServiceInterface.getPatientInfoById(1);
		patient1.setMiddleName("");
		userServiceInterface.updatePatient(patient1);
		
		AppointmentRequest appointmentRequest = new AppointmentRequest();
		appointmentRequest.setAppointmentStartTime(new DateTime());
		appointmentRequest.setRequestInsertedTime(new DateTime());
		appointmentRequest.setNote("I need an appointment for dental check up");
		appointmentRequest.setAppointmentPatient(patient1);
		appointmentRequest.setStatus(AppointmentRequestStatus.WAITING_FOR_APPROVAL);
		
		userServiceInterface.setAppointmentRequest(appointmentRequest);
		UserAuthentication user = userServiceInterface.getUserAuthenticationInfoById(1);

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
		return null;
	}

}
