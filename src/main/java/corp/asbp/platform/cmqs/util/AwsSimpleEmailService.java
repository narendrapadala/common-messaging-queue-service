package corp.asbp.platform.cmqs.util;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.RawMessage;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendRawEmailRequest;

import corp.asbp.platform.cmqs.model.Email;
import corp.asbp.platform.cmqs.model.EmailConfig;

/**
 * @author Narendra Padala
 *
 */
@Component
public class AwsSimpleEmailService {
	
	private static Logger LOGGER = LoggerFactory.getLogger(AwsSimpleEmailService.class);
	
	@Autowired
	CmqsProperties properties;

	/**
	 * @param awsAccessKey
	 * @param awsSecretKey
	 * @param awsSesRegion
	 * @return
	 */
	public AmazonSimpleEmailService getAwsClient(String awsAccessKey, String awsSecretKey, String awsSesRegion) {
		// Regions.US_EAST_1;
		try {
			AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard()
					.withCredentials(
							new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
					.withRegion(awsSesRegion).build();
			// return
			return client;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// return
		return null;
	}

	/**
	 * @param email
	 * @return
	 */
	public SendEmailRequest getSendEmailRequest(Email email) {

		try {

			Destination destination = new Destination();
			// check
			if (email.getToAddress() != null && email.getToAddress().size() > 0) {
				destination.setToAddresses(email.getToAddress());
			}
			// check
			if (email.getCcAddress() != null && email.getCcAddress().size() > 0) {
				destination.setCcAddresses(email.getCcAddress());
			}
			// check
			if (email.getBccAddress() != null && email.getBccAddress().size() > 0) {
				destination.setBccAddresses(email.getBccAddress());
			}

			Body body = new Body();

			// Add the text parts to the child container.
			if (email.getMessageType().equalsIgnoreCase("text")) {
				body.setHtml(new Content().withCharset("UTF-8").withData(email.getMessage()));
			}
			// Add the HTML parts to the child container.
			if (email.getMessageType().equalsIgnoreCase("html")) {
				body.withText(new Content().withCharset("UTF-8").withData(email.getMessage()));
			}

			SendEmailRequest request = new SendEmailRequest().withDestination(destination)
					.withMessage(new Message().withBody(body)
							.withSubject(new Content().withCharset("UTF-8").withData(email.getSubject())))
					.withSource(email.getFromAddress());
			// return
			return request;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// return
		return null;
	}

	/**
	 * @param email
	 * @return
	 */
	public SendRawEmailRequest getSendRawEmailRequest(Email email) {
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

			Session session = Session.getDefaultInstance(new Properties());
			// Create a new MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Add subject, from and to lines.
			message.setSubject(email.getSubject(), "UTF-8");
			message.setFrom(new InternetAddress(email.getFromAddress()));

			// message.setRecipients(javax.mail.Message.RecipientType.TO,
			// InternetAddress.parse(TO));

			message.addRecipients(javax.mail.Message.RecipientType.TO, toAddress);

			// check
			if (ccAddressList != null && ccAddressList.size() > 0) {
				message.addRecipients(javax.mail.Message.RecipientType.CC, ccAddress);
			}
			// check
			if (bccAddressList != null && bccAddressList.size() > 0) {
				message.addRecipients(javax.mail.Message.RecipientType.BCC, bccAddress);
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

			// Send the email.
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			message.writeTo(outputStream);
			RawMessage rawMessage = new RawMessage(ByteBuffer.wrap(outputStream.toByteArray()));

			SendRawEmailRequest rawEmailRequest = new SendRawEmailRequest(rawMessage);
			// return
			return rawEmailRequest;

		} catch (Exception ex) {
			LOGGER.info("The email was not sent. Error message: " + ex.getMessage());
		}

		// return
		return null;
	}

	/**
	 * @param email
	 */
	public void processEmail(Email email) {
		// check
		if (email.getAttachments() != null && email.getAttachments().size() > 0) {
			SendRawEmailRequest rawRequest = getSendRawEmailRequest(email);
			this.getAwsClient(properties.getAwsAccessKey(), properties.getAwsSecretKey(), properties.getAwsSesRegion())
					.sendRawEmail(rawRequest);
		} else {
			SendEmailRequest request = getSendEmailRequest(email);
			this.getAwsClient(properties.getAwsAccessKey(), properties.getAwsSecretKey(), properties.getAwsSesRegion())
					.sendEmail(request);
		}
	}

	/**
	 * @param email
	 * @param config
	 */
	public void processEmail(Email email, EmailConfig config) {
		// check
		if (email.getAttachments() != null && email.getAttachments().size() > 0) {
			SendRawEmailRequest rawRequest = getSendRawEmailRequest(email);
			this.getAwsClient(config.getAwsAccessKey(), config.getAwsSecretKey(), config.getAwsSesRegion())
					.sendRawEmail(rawRequest);
		} else {
			SendEmailRequest request = getSendEmailRequest(email);
			this.getAwsClient(config.getAwsAccessKey(), config.getAwsSecretKey(), config.getAwsSesRegion())
					.sendEmail(request);
		}
	}
}
