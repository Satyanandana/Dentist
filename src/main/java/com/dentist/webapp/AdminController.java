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
import org.springframework.web.bind.annotation.RequestParam;

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
 * @since Apr 28, 20166:55:33 PM
 * @git
 * 
 */

@Controller
@Transactional
@RequestMapping(value = "/admin")
public class AdminController {

	private static final Logger LOGGER = Logger.getLogger(AdminController.class);

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/dashbord", method = RequestMethod.GET)
	public String getAdminDashbord(Model model) {
		LOGGER.debug("processing GET request to /admin/dashbord ....");
		return "admindashboard";

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/appointments", method = RequestMethod.GET)
	public String getAdminAppointments(Model model) {
		LOGGER.debug("processing GET request to /admin/dashbord ....");
		return "appointments";

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/appointmentrequests", method = RequestMethod.GET)
	public String getAdminAppointmentRequests(Model model) {
		LOGGER.debug("processing GET request to /admin/dashbord ....");
		return "appointmentrequests";

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/receivedmessages", method = RequestMethod.GET)
	public String getAdminReceivedMessages(Model model) {
		LOGGER.debug("processing GET request to /admin/dashbord ....");
		return "receivedmessages";

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/receiveddocuments", method = RequestMethod.GET)
	public String getAdminReceivedDocuments(Model model) {
		LOGGER.debug("processing GET request to /admin/dashbord ....");
		return "receiveddocuments";

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/view/{patientID}", method = RequestMethod.GET)
	public String getProfileByPatientID(Model model, @PathVariable("patientID") long patientID,
			@RequestParam(name = "action", defaultValue = "profile") String action) {
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
			return "myprofileforadmin";
		} else {
			return null;
		}

	}

}
