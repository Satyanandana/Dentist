package com.dentist.webapp;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dentist.domain.Patient;
import com.dentist.domain.ReceivedDocument;
import com.dentist.domain.SentDocument;
import com.dentist.mail.EmailGenerator;
import com.dentist.mail.EmailStructure;
import com.dentist.service.CustomUserDetails;
import com.dentist.service.UserServiceInterface;

/**
 * 
 *
 * @author Satyanandana Srikanthvarma Vadapalli
 * @email srikanthvarma.vadapalli@gmail.com
 * @version 1.0
 * @since Apr 15, 20165:22:31 PM
 * @git
 * 
 */

@RestController
@Transactional
@RequestMapping(value = "/doc")
public class DocumentController {
	private static final Logger LOGGER = Logger.getLogger(PatientInfoController.class);
	@Autowired
	private UserServiceInterface userServiceInterface;
	@Autowired
	private EmailGenerator emailSender;
	@Autowired
	private EmailStructure emailStructure;
	@Autowired
	@Qualifier("encryptableProps")
	private Properties encryptableProps;

	@Autowired
	private ServletContext servletContext;

	/**
	 * GET API END POINTS TO HANDELE REQUESTS FROM PATIENT
	 **/

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/download/sent/{documentID}", method = RequestMethod.GET)
	public void downloadSentFile(HttpServletResponse response, @PathVariable("documentID") long documentID) throws IOException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		SentDocument sentDocument = userServiceInterface.getSentDocumentByIDandPatientID(documentID, user.getUserID());
		if (sentDocument != null) {
			FileUploadDownloadHandler.downloadFile(response, sentDocument.getPath(), sentDocument.getFileExt());
		}

	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/download/received/{documentID}", method = RequestMethod.GET)
	public void downloadReceivedFile(HttpServletResponse response, @PathVariable("documentID") long documentID) throws IOException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		ReceivedDocument receivedDocument = userServiceInterface.getReceivedDocumentByIDandPatientID(documentID, user.getUserID());
		if (receivedDocument != null) {
			FileUploadDownloadHandler.downloadFile(response, receivedDocument.getPath(), receivedDocument.getFileExt());
		}

	}

	/**
	 * POST API END POINTS TO HANDELE REQUESTS FROM PATIENT
	 **/

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> handleFileUpload(@RequestParam(name = "name", defaultValue = "doc") String name,
			@RequestParam("file") MultipartFile file) {
		Map<String, String> map = new HashMap<>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		String currentDirectory = new File("").getAbsolutePath();
		String dir = currentDirectory + File.separator + "user_" + user.getUserID() + File.separator + "sent";
		boolean done = FileUploadDownloadHandler.uploadFile(map, file, "File", dir);
		if (done) {
			SentDocument sentDocument = new SentDocument();
			sentDocument.setFileExt(map.get("extension"));
			sentDocument.setFileName(name);
			sentDocument.setPath(map.get("path"));
			sentDocument.setSentTime(new DateTime());
			sentDocument.setSender(userServiceInterface.getPatientInfoById(user.getUserID()));
			userServiceInterface.setSentDocument(sentDocument);
			// Prepare and send an email to Admin to notify him about the new
			// document.
			Map<String, Object> emailMap = new HashMap<String, Object>();
			map.put("user", "Admin");

			/*String body = emailSender.prepareBody(EmailTemplate.NEW_DOCUMENT_NOTIFICATION, emailMap);
			emailStructure.setBody(body);
			emailStructure.setSenderEmail(encryptableProps.getProperty("email.id"));
			emailStructure.setSubject("You received a new document");
			emailStructure.addRecipient(encryptableProps.getProperty("admin.email"));
			emailSender.sendEmail(emailStructure);*/

			map.put("Success", "Success");
		}

		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
	}

	/**
	 * GET API END POINTS TO HANDELE REQUESTS FROM ADMIN
	 **/

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/receiveddocuments/{patientID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SentDocument>> getReceivedDocsPatient(Model model, @PathVariable("patientID") long patientID) {
		LOGGER.debug("processing request to get received documents ...");

		List<SentDocument> received = userServiceInterface.getSentDocumentsByPatientID(patientID);
		return new ResponseEntity<List<SentDocument>>(received, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/sentdocuments/{patientID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReceivedDocument>> getSentDocsPatient(Model model, @PathVariable("patientID") long patientID) {
		LOGGER.debug("processing request to get sent documents ...");

		List<ReceivedDocument> received = userServiceInterface.getReceivedDocumentsByPatientID(patientID);
		return new ResponseEntity<List<ReceivedDocument>>(received, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/download/sent/{documentID}/{patientID}", method = RequestMethod.GET)
	public void downloadSentFileByAdmin(HttpServletResponse response, @PathVariable("documentID") long documentID,
			@PathVariable("patientID") long patientID) throws IOException {

		ReceivedDocument receivedDocument = userServiceInterface.getReceivedDocumentByIDandPatientID(documentID, patientID);
		if (receivedDocument != null) {
			FileUploadDownloadHandler.downloadFile(response, receivedDocument.getPath(), receivedDocument.getFileExt());
		}

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/download/received/{documentID}/{patientID}", method = RequestMethod.GET)
	public void downloadReceivedFileByAdmin(HttpServletResponse response, @PathVariable("documentID") long documentID,
			@PathVariable("patientID") long patientID) throws IOException {

		SentDocument sentDocument = userServiceInterface.getSentDocumentByIDandPatientID(documentID, patientID);
		if (sentDocument != null) {
			FileUploadDownloadHandler.downloadFile(response, sentDocument.getPath(), sentDocument.getFileExt());
		}

	}

	/**
	 * POST API END POINTS TO HANDELE REQUESTS FROM ADMIN
	 **/

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/upload/{patientID}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> handleFileUploadByAdmin(@PathVariable("patientID") long patientID,
			@RequestParam(name = "name", defaultValue = "doc") String name, @RequestParam("file") MultipartFile file) {
		Map<String, String> map = new HashMap<>();

		String currentDirectory = new File("").getAbsolutePath();
		String dir = currentDirectory + File.separator + "user_" + patientID + File.separator + "received";
		boolean done = FileUploadDownloadHandler.uploadFile(map, file, "File", dir);
		if (done) {

			ReceivedDocument receivedDocument = new ReceivedDocument();
			receivedDocument.setFileExt(map.get("extension"));
			receivedDocument.setFileName(name);
			receivedDocument.setPath(map.get("path"));
			receivedDocument.setReceivedTime(new DateTime());
			Patient patient = userServiceInterface.getPatientInfoById(patientID);
			receivedDocument.setReceiver(patient);
			userServiceInterface.setReceivedDocument(receivedDocument);

			// Prepare and send an email to patient to notify him about the new
			// document.
			Map<String, Object> emailMap = new HashMap<String, Object>();
			map.put("user", patient.getFirstName() + " " + patient.getLastName());

			/*	String body = emailSender.prepareBody(EmailTemplate.NEW_DOCUMENT_NOTIFICATION, emailMap);
				emailStructure.setBody(body);
				emailStructure.setSenderEmail(encryptableProps.getProperty("email.id"));
				emailStructure.setSubject("You received a new document");
				emailStructure.addRecipient(patient.getEmail());
				emailSender.sendEmail(emailStructure);*/

			map.put("Success", "Success");
		}

		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
	}

}
