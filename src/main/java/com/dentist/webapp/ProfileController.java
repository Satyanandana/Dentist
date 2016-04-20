package com.dentist.webapp;

import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dentist.domain.Appointment;
import com.dentist.domain.AppointmentRequest;
import com.dentist.domain.Insurance;
import com.dentist.domain.Patient;
import com.dentist.domain.Role;
import com.dentist.domain.Treatment;
import com.dentist.service.CustomUserDetails;

/**
 * 
 *
 * @author Satyanandana Srikanthvarma Vadapalli
 * @email srikanthvarma.vadapalli@gmail.com
 * @version 1.0
 * @since Apr 17, 20162:24:18 PM
 * @git
 * 
 */
@Controller
@Transactional
@RequestMapping(value = "/profile")
public class ProfileController {

	private static final Logger LOGGER = Logger.getLogger(ProfileController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String getProfile(Model model) {
		model.addAttribute("patient", new Patient());
		model.addAttribute("insurance", new Insurance());
		model.addAttribute("appointment", new Appointment());
		model.addAttribute("appointmentRequest", new AppointmentRequest());
		model.addAttribute("treatment", new Treatment());

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		if (user.getUserRole().equals(Role.ROLE_USER)) {
			LOGGER.debug("processing GET request to /profile/view  with USER role");
			return "myprofile";
		} else {
			return null;
		}

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/view/{patientID}", method = RequestMethod.GET)
	public String getProfileByPatientID(Model model, @PathVariable("patientID") long patientID) {
		model.addAttribute("patient", new Patient());
		model.addAttribute("insurance", new Insurance());
		model.addAttribute("appointment", new Appointment());
		model.addAttribute("appointmentRequest", new AppointmentRequest());
		model.addAttribute("treatment", new Treatment());
		model.addAttribute("patientID", patientID);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		if (user.getUserRole().equals(Role.ROLE_ADMIN)) {
			LOGGER.debug("processing GET request to /profile/view/" + patientID + "  with ADMIN role");
			return "myprofile";
		} else {
			return null;
		}

	}

}
