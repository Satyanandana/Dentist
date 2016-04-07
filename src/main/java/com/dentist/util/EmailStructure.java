package com.dentist.util;
/**
* 
*
* @author  Satyanandana Srikanthvarma Vadapalli
* @email srikanthvarma.vadapalli@gmail.com
* @version 1.0
* @since   Mar 17, 20161:10:28 AM
*       
*/
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class EmailStructure {
	private String senderEmail;
	private ArrayList<String> recipients = new ArrayList<String>();
	private String subject;
	private String body;
	private Map<String, File> attachments = new HashMap<String, File>();
	private Map<String, File> inlineImages = new HashMap<String, File>();

	public EmailStructure() {

	}

	public EmailStructure(String senderEmail, ArrayList<String> recipients, String subject, String body,
			Map<String, File> attachments) {
		this.senderEmail = senderEmail;
		this.recipients = recipients;
		this.subject = subject;
		this.body = body;
		this.attachments = attachments;
	}

	public String getSenderEmail() {
		return senderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public ArrayList<String> getRecipients() {
		return recipients;
	}

	public void setRecipients(ArrayList<String> recipients) {
		this.recipients = recipients;
	}

	public void addRecipient(String recipient) {
		recipients.add(recipient);
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Map<String, File> getAttachments() {
		return attachments;
	}

	public void setAttachments(Map<String, File> attachments) {
		this.attachments = attachments;
	}

	public void addAttachment(String name, File attachment) {
		attachments.put(name, attachment);
	}

	public Map<String, File> getInlineImages() {
		return inlineImages;
	}

	public void setInlineImages(Map<String, File> inlineImages) {
		this.inlineImages = inlineImages;
	}
	
	public void addInlineImages(String name,File file){
		this.inlineImages.put(name, file);
	}

}
