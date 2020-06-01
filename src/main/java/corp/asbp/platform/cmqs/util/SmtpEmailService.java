package corp.asbp.platform.cmqs.util;

import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import corp.asbp.platform.cmqs.model.Email;
import corp.asbp.platform.cmqs.model.EmailConfig;

/**
 * @author Narendra Padala
 *
 */
@Component
public class SmtpEmailService {

	private static Logger LOGGER = LoggerFactory.getLogger(SmtpEmailService.class);

	@Autowired
	CmqsProperties properties;

	/**
	 * @param port
	 * @return
	 */
	public Session getSmtpConfig(Long port) {

		Properties props = System.getProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");

		// return
		return Session.getDefaultInstance(props);

	}

	/**
	 * @param session
	 * @param email
	 * @return
	 */
	public MimeMessage getEmailRequest(Session session, Email email) {
		// Create a message with the specified information.
		MimeMessage message = new MimeMessage(session);
		try {

			List<String> toAddressList = email.getToAddress();
			List<String> ccAddressList = email.getCcAddress();
			List<String> bccAddressList = email.getBccAddress();
			List<String> attachmentsList = email.getAttachments();

			int size = 0;

			Address[] toAddress;
			Address[] ccAddress;
			Address[] bccAddress;

			// check add
			if (toAddressList != null && toAddressList.size() > 0) {
				toAddress = new Address[toAddressList.size()];
				int i = 0;
				// loop
				for (String toAddr : toAddressList) {
					toAddress[i] = new InternetAddress(toAddr);
					i++;
				}
			} else {
				toAddress = new Address[size];
			}

			// check add
			if (ccAddressList != null && ccAddressList.size() > 0) {
				ccAddress = new Address[ccAddressList.size()];
				int j = 0;
				// loop
				for (String ccAddr : ccAddressList) {
					ccAddress[j] = new InternetAddress(ccAddr);
					j++;
				}
			} else {
				ccAddress = new Address[size];
			}

			// check add
			if (bccAddressList != null && bccAddressList.size() > 0) {
				bccAddress = new Address[bccAddressList.size()];
				int k = 0;
				// loop
				for (String bccAddr : bccAddressList) {
					bccAddress[k] = new InternetAddress(bccAddr);
					k++;
				}
			} else {
				bccAddress = new Address[size];
			}

			// Add subject, from and to lines.
			message.setSubject(email.getSubject(), "UTF-8");
			message.setFrom(new InternetAddress(email.getFromAddress()));

			// message.setContent(BODY, "text/html");

			// Add subject, from and to lines.
			message.setSubject(email.getSubject(), "UTF-8");
			message.setFrom(new InternetAddress(email.getFromAddress()));

			// message.setRecipients(javax.mail.Message.RecipientType.TO,
			// InternetAddress.parse(TO));

			message.addRecipients(RecipientType.TO, toAddress);

			// check
			if (ccAddressList != null && ccAddressList.size() > 0) {
				message.addRecipients(RecipientType.CC, ccAddress);
			}
			// check
			if (bccAddressList != null && bccAddressList.size() > 0) {
				message.addRecipients(RecipientType.BCC, bccAddress);
			}

			// Create a multipart/alternative child container.
			MimeMultipart msg_body = new MimeMultipart("alternative");

			// Create a wrapper for the HTML and text parts.
			MimeBodyPart wrap = new MimeBodyPart();

			// Define the text part.
			MimeBodyPart textPart = new MimeBodyPart();
			textPart.setContent(email.getMessage(), "text/plain; charset=UTF-8");

			// Define the HTML part.
			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(email.getMessage(), "text/html; charset=UTF-8");

			// Add the text parts to the child container.
			if (email.getMessageType().equalsIgnoreCase("text")) {
				msg_body.addBodyPart(textPart);
			}
			// Add the HTML parts to the child container.
			if (email.getMessageType().equalsIgnoreCase("html")) {
				msg_body.addBodyPart(htmlPart);
			}

			// Add the child container to the wrapper object.
			wrap.setContent(msg_body);

			// Create a multipart/mixed parent container.
			MimeMultipart msg = new MimeMultipart("mixed");

			// Add the parent container to the message.
			message.setContent(msg);

			// Add the multipart/alternative part to the message.
			msg.addBodyPart(wrap);
			// check
			if (attachmentsList != null && attachmentsList.size() > 0) {
				// loop
				for (String attachment : attachmentsList) {
					// Define the attachment
					MimeBodyPart att = new MimeBodyPart();
					DataSource fds = new FileDataSource(attachment);
					att.setDataHandler(new DataHandler(fds));
					att.setFileName(fds.getName());
					// Add the attachment to the message.
					msg.addBodyPart(att);
				}
			}

			// return
			return message;

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// return
		return null;

	}

	/**
	 * @param email
	 */
	public void processEmail(Email email) {

		Session session = getSmtpConfig(properties.getSmtpPort());
		MimeMessage msg = getEmailRequest(session, email);

		try {
			Transport transport = session.getTransport();
			LOGGER.info("Sending...");
			transport.connect(properties.getSmtpHost(), properties.getSmtpUser(), properties.getSmtpPass());
			// Send the email.
			transport.sendMessage(msg, msg.getAllRecipients());
			LOGGER.info("Email sent!");
			transport.close();
		} catch (Exception ex) {
			LOGGER.info("The email was not sent.");
			LOGGER.info("Error message: " + ex.getMessage());
		}
	}

	/**
	 * @param email
	 * @param config
	 */
	public void processEmail(Email email, EmailConfig config) {

		Session session = getSmtpConfig(config.getSmtpPort());
		MimeMessage msg = getEmailRequest(session, email);

		try {
			Transport transport = session.getTransport();
			LOGGER.info("Sending...");
			transport.connect(config.getSmtpHost(), config.getSmtpUser(), config.getSmtpPass());
			// Send the email.
			transport.sendMessage(msg, msg.getAllRecipients());
			LOGGER.info("Email sent!");
			transport.close();
		} catch (Exception ex) {
			LOGGER.info("The email was not sent.");
			LOGGER.info("Error message: " + ex.getMessage());
		}
	}
}
