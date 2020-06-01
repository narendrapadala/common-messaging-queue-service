package corp.asbp.platform.cmqs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import corp.asbp.platform.cmqs.model.Email;
import corp.asbp.platform.cmqs.model.EmailConfig;
import corp.asbp.platform.cmqs.service.NotificationService;
import corp.asbp.platform.cmqs.util.AwsSimpleEmailService;
import corp.asbp.platform.cmqs.util.CmqsProperties;
import corp.asbp.platform.cmqs.util.SmtpEmailService;

/**
 * @author Narendra Padala
 *
 */
@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	AwsSimpleEmailService awsEmail;

	@Autowired
	SmtpEmailService smtpEmail;

	@Autowired
	CmqsProperties properties;

	@Override
	public void notifyEmail(Email email) {
		// check
		if (properties.isAwsSes()) {
			awsEmail.processEmail(email);
		} else {
			smtpEmail.processEmail(email);
		}
	}

	@Override
	public void notifyEmail(Email email, EmailConfig config) {
		// check
		if (config.isAwsSes()) {
			awsEmail.processEmail(email, config);
		} else {
			smtpEmail.processEmail(email, config);
		}
	}

}
