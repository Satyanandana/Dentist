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
import java.util.concurrent.Future;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dentist.domain.UserAuthentication;
import com.dentist.util.CalendarEventHandler;
import com.dentist.util.EmailGenerator;
import com.dentist.util.EmailStructure;
import com.dentist.util.EmailTemplate;
import com.dentist.util.IpAddressGeoLocation;
import com.dentist.util.ServerLocation;
import com.dentist.webapp.SessionHandler;
import com.dentist.service.UserServiceInterface;
import com.dentist.service.WebUtility;

/**
 * Handles requests for the application home page.
 */
@Controller
@EnableAsync
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
	@Autowired
	private PooledPBEStringEncryptor encryptor;
	@Autowired
	private IpAddressGeoLocation geoLocation;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String loginForm(HttpServletRequest request, HttpServletResponse response,Model model,@CookieValue(name="USER",required=false) String userCookie) {
		
		logger.info("Checking for the user in session");
		boolean userSession = (request.getSession().getAttribute("user")!= null);
		logger.info("User is in the session :" + userSession);
		if(userCookie != null && !userSession){
		logger.info("check user in the cookie");
		  String userEmail  = encryptor.decrypt(userCookie);
		  UserAuthentication userAuth = userServiceInterface.getUserAuthenticationInfoByEmail(userEmail);
			if (userAuth != null) {
				logger.info("adding the user to spring session registry w.r.t cookie data");
				  
						
							try {
								SessionHandler.handleSession(sessionRegistry, successHandler, request, response, userAuth, encryptor);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (ServletException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
					
					return null; 
				
			}else{
				logger.info("unable to add the user to spring session w.r.t cookie data");
			}
		}else if(userSession){
			logger.info("Redirecting to /");
			return "redirect:/";
		}
		
	   		
		logger.info("To login page .... ");
		model.addAttribute("serverTime",new DateTime().toString());
		return "login";

	}

	@RequestMapping(value = "/process", method = RequestMethod.POST)
	public String Customlogin(HttpServletRequest request, HttpServletResponse response,Locale locale,Model model,@RequestParam String email,@RequestParam String password)
			throws IOException, ServletException {
		logger.info("processing post requset to /login/process and athuenticate the user if email and password are valid credentials");
		boolean valid = false;
		boolean validEmail = ServerSideValidations.validateEmail(email, model,"emailError", "Invalid email address");
        boolean validPassword = ServerSideValidations.validatePassword(password, model, "passwordError", "Invalid password");
		
		if(validEmail && validPassword){
			valid = true;
			logger.info("valid email and password");
		}
		
		if(valid){
			logger.info("Checking whether a user exists with the given password");
		UserAuthentication userAuth = userServiceInterface.getUserAuthenticationInfoByEmail(email);
		if (userAuth != null) {
			if (userAuth.getUserPwd().equals(password)) {
				logger.info("valid user credentials");
				logger.info("adding user to spring session registry");
				SessionHandler.handleSession(sessionRegistry, successHandler, request, response, userAuth, encryptor);
				// get the location of user with IP address
				
				ServerLocation serverLocation = geoLocation.getLocation(WebUtility.getIpAddress(request));  
				   logger.info(serverLocation.getCountryCode());
				   logger.info(serverLocation.getCountryName());
				   logger.info(serverLocation.getRegionCode());
				   logger.info(serverLocation.getRegionName());
				   logger.info(serverLocation.getCity());
				   logger.info(serverLocation.getLatitude());
				   logger.info(serverLocation.getLongitude());
				
				// Prepare and send last login info email
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ipAddress",WebUtility.getIpAddress(request));
				map.put("location",serverLocation);
				
				
				String body = emailSender.prepareBody(EmailTemplate.LAST_LOGIN_EMAIL, map);
				emailStructure.setBody(body);
				emailStructure.setSenderEmail("gtsatyansv@gmail.com");
				emailStructure.setSubject("Last login info");
				emailStructure.addRecipient(userAuth.getUserEmail());
			 // emailStructure.addAttachment("welcome.vm", file);
		     // Future<Boolean> sent1 =  emailSender.sendEmail(emailStructure);
				return null;
			}
		} 
		}
		model.addAttribute("serverTime",new DateTime().toString());
		return "login";
	}

}
