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
import java.util.Map;
import java.util.concurrent.Future;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
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
	private static final Logger LOGGER = Logger.getLogger(EmailGenerator.class);

	public void setMailSender(JavaMailSender mailSender) {
		this.emailSender = mailSender;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	@Async
	public Future<Boolean> sendEmail(final EmailStructure emailSructure) {
		Boolean sent = new Boolean(true);
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				try {
					MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);

					// Add list of recipients to the message
					for (String email : emailSructure.getRecipients()) {
						message.setTo(email);
					}
					// Add the sender email to the message
					message.setFrom(emailSructure.getSenderEmail());

					// Set subject to the message
					message.setSubject(emailSructure.getSubject());

					// Add body to the message
					message.setText(emailSructure.getBody(), true);

					// let's attach the infamous windows Sample file (this time
					// copied to c:/)
					for (Map.Entry<String, File> attachment : emailSructure.getAttachments().entrySet()) {
						FileSystemResource file = new FileSystemResource(attachment.getValue());
						message.addAttachment(attachment.getKey(), file);
					}
					
					for (Map.Entry<String, File> inlineImage : emailSructure.getInlineImages().entrySet()) {
						FileSystemResource file = new FileSystemResource(inlineImage.getValue());
						message.addAttachment(inlineImage.getKey(), file);
					}

				} catch (MessagingException e) {
					LOGGER.error("Unable to prepare message",e);
				}

			}

		};
		if (preparator != null) {
			try {
				this.emailSender.send(preparator);
			} catch (MailException e) {
				sent = false;
				LOGGER.error("unable to send email", e);
			}
		}

		return new AsyncResult<Boolean>(sent);
	}

	public String prepareBody(EmailTemplate template, Map<String, Object> model) {
		String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template.getName(), "UTF-8", model);
		return body;
	}

}
