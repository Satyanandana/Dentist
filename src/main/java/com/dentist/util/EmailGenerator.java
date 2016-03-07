package com.dentist.util;

import java.io.File;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

@Component
@Scope(value = "prototype")
public class EmailGenerator {

	@Autowired
	@Qualifier("emailSender")
	private JavaMailSender emailSender;

	@Autowired
	@Qualifier("velocityEngine")
	private VelocityEngine velocityEngine;

	public void setMailSender(JavaMailSender mailSender) {
		this.emailSender = mailSender;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public synchronized void sendEmail(final EmailStructure emailSructure) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);

				// Add list of recipients to the message
				for (String email : emailSructure.getRecipients()) {
					message.setTo(email);
				}
				// Add the sender email to the message
				message.setFrom(emailSructure.getSenderEmail());

				// Set subject to the message
				message.setSubject(emailSructure.getSubject());

				/*
				 * Map<String, Object> model = new HashMap<String, Object>();
				 * model.put("user", "Srikanthvarma"); String text =
				 * VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
				 * "welcomeemail.vm", "UTF-8", model);
				 * 
				 * message.setText(text, true); // let's include the infamous
				 * windows Sample file (this time // copied to c:/)
				 * FileSystemResource res = new FileSystemResource(new
				 * File("c:/sample.jpg")); message.addInline("identifier1234",
				 * res);
				 */
				// Add body to the message
				message.setText(emailSructure.getBody(), true);

				// let's attach the infamous windows Sample file (this time
				// copied to c:/)
				for (Map.Entry<String, File> attachment : emailSructure.getAttachments().entrySet()) {
					FileSystemResource file = new FileSystemResource(attachment.getValue());
					message.addAttachment(attachment.getKey(), file);
				}

			}

		};
		this.emailSender.send(preparator);
	}

	public synchronized String prepareBody(EmailTemplate template, Map<String, Object> model) {
		String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template.getName(), "UTF-8", model);
		return body;
	}

}
