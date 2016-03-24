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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dentist.domain.Patient;
import com.dentist.domain.UserAuthentication;
import com.dentist.util.CalendarEventHandler;
import com.dentist.util.EmailGenerator;
import com.dentist.util.EmailStructure;
import com.dentist.util.EmailTemplate;
import com.dentist.service.UserServiceInterface;
import com.dentist.service.WebUtility;

/**
 * Handles requests for the application signup page.
 */
@Controller
@EnableAsync
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
	@Autowired(required=true)
	private PooledPBEStringEncryptor encryptor;
	

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String signUpForm(HttpServletRequest request, HttpServletResponse response,Model model,@CookieValue(name="USER",required=false) String userCookie) {
		logger.debug("Checking for the user in session");
		boolean userSession = (request.getSession().getAttribute("user")!= null);
		logger.debug("User is in the session :" + userSession);
		if(userCookie != null && !userSession){
		logger.debug("check user in the cookie");
		  String userEmail  = encryptor.decrypt(userCookie);
		  UserAuthentication userAuth = userServiceInterface.getUserAuthenticationInfoByEmail(userEmail);
			if (userAuth != null) {
				logger.debug("adding the user to spring session registry w.r.t cookie data");
				    
						
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
				logger.debug("unable to add the user to spring session w.r.t cookie data");
			}
		}else if(userSession){
			logger.debug("Redirecting to /");
			return "redirect:/";
		} 
		
		//Required for spring form model attribute		
		Patient patient = new Patient();
		model.addAttribute("patient", patient);
		logger.debug("To signup page ");
		return "signup";

	}

	@RequestMapping(value = "/process", method = RequestMethod.POST)
	public String postSignUP(HttpServletRequest request, HttpServletResponse response,Model model,@ModelAttribute Patient patient,@RequestParam String dob )
			throws IOException, ServletException, InterruptedException {

        boolean valid = false;
        boolean validEmail = ServerSideValidations.validateEmail(patient.getUserAuth().getUserEmail(), model,"errorEmail", "Invalid email address");
        boolean validPassword = ServerSideValidations.validatePassword(patient.getUserAuth().getUserPwd(), model, "errorPassword", "Invalid password");
        boolean validFirstname = ServerSideValidations.validateName(patient.getFirstName(), model, "errorFirstName", "Firstname Should contain only alphabets");
        boolean validLastname = ServerSideValidations.validateName(patient.getLastName(), model, "errorLastName", "Lasttname Should contain only alphabets");
        boolean validMiddlename = ServerSideValidations.validateName(patient.getMiddleName(), model, "errorMiddleName", "Middletname Should contain only alphabets");
        boolean validPhoneNumber = ServerSideValidations.validPhoneNumber(String.valueOf(patient.getPhoneNumber()), model, "errorPhoneNumber", "Invalid phone number");
        
        if(validEmail&&validFirstname&&validLastname&&validMiddlename&&validPassword&&validPhoneNumber){
            valid  = true;	
        }
        
        LocalDate dateOfBirth = WebUtility.getLocalDatefromHtmlDate(dob);
		if(dateOfBirth!=null){
			patient.setDateOfBirth(dateOfBirth);
		}
		else{
			model.addAttribute("errorDate", "Please select a valid date");
			valid = false;
		}
		
       if(valid){
    	   logger.debug("Request parameters are valid.Checking for existing account with given email...");
		Patient patientCreated = userServiceInterface.signUp(patient, request,model);
		if(patientCreated!=null){
			userServiceInterface.setPatient(patientCreated);
			logger.debug("new user account created successfully");
			
			// Prepare and send Welcome Email
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user", "Srikanthvarma");
			String body1 = emailSender.prepareBody(EmailTemplate.WELCOME_EMAIL, map);
			emailStructure.setBody(body1);
			emailStructure.setSenderEmail("gtsatyansv@gmail.com");
			emailStructure.setSubject("Welcome to Dr.Kang Dentistry");
			emailStructure.addRecipient(patientCreated.getEmail());
		 // emailStructure.addAttachment("welcome.vm", file);
	     //   Future<Boolean> sent1 =  emailSender.sendEmail(emailStructure);
	        
	     // Prepare and send AccountVerification Email
	        map.clear();
	        map.put("veryfyKey", patientCreated.getUserAuth().getVerifyKey());
	        String body2 = emailSender.prepareBody(EmailTemplate.VERIFY_ACCOUNT_EMAIL, map);
			emailStructure.setBody(body2);
			emailStructure.setSenderEmail("gtsatyansv@gmail.com");
			emailStructure.setSubject("Verify your account with Dr.Kang Dentistry");
			emailStructure.addRecipient(patientCreated.getEmail());
		 // emailStructure.addAttachment("welcome.vm", file);
	     //   Future<Boolean> sent2 =  emailSender.sendEmail(emailStructure);
	        
	        
	        
			UserAuthentication userAuth = userServiceInterface.getUserAuthenticationInfoByEmail(patient.getEmail());
			SessionHandler.handleSession(sessionRegistry, successHandler, request, response, userAuth, encryptor);
		
		    
			// Wait until the emails are sent
	    /*    while (!(sent1.isDone() && sent2.isDone())) {
	            logger.info("waiting to send email ...");
	            Thread.sleep(10); //10-millisecond pause between each check
	        }*/
			
			logger.debug("Sent welcome and account verification emails");
			return null;
		}
       }


		return "signup";
	}

}
