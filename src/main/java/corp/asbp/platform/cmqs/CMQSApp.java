package corp.asbp.platform.cmqs;

import java.util.Arrays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import corp.asbp.platform.cmqs.model.Email;
import corp.asbp.platform.cmqs.model.EmailConfig;
import corp.asbp.platform.cmqs.service.NotificationService;

/**
 * @author Narendra Padala
 *
 */
@SpringBootApplication
@EnableAsync // for faster processing
@PropertySource("classpath:cmqs.properties")
@RestController
public class CMQSApp {

	private String SENDER = "napadala@cisco.com";

	private String ATTACHMENT = "C:\\Users\\napadala\\Desktop\\satvik\\donkey.jpg";
	private String ATTACHMENT1 = "C:\\Users\\napadala\\Desktop\\satvik\\monkey.png";
	
	// The subject line for the email.
	private String SUBJECT = "Customer service contact info";

	// The email body for recipients with non-HTML email clients.
	private String BODY_TEXT = "Hello,\r\n" + "Please see the attached file for a list "
			+ "of customers to contact.";

	// The HTML body of the email.
	private String BODY_HTML = "<html>" + "<head></head>" + "<body>" + "<h1>Hello!</h1>"
			+ "<p>Please see the attached file for a " + "list of customers to contact.</p>" + "</body>" + "</html>";

	
	@Autowired
	NotificationService notificationService;
	
	@GetMapping("/nofifyEmail")
	String nofifyEmail() {
		
		Email email =  new Email.Builder()
				.fromAddress(SENDER)
				.toAddress(Arrays.asList("narendrapadala@gmail.com"))
				.subject(SUBJECT)
				.message(BODY_TEXT)
				.attachments(Arrays.asList(this.ATTACHMENT, this.ATTACHMENT1))
				.ccAddress(Arrays.asList("napadala@cisco.com"))
				.bccAddress(Arrays.asList("napadala@cisco.com"))
				.messageType("html")
				.build();
		
		//set custom config
		EmailConfig config = new EmailConfig.Builder()
				.awsAccessKey("AKIAZ5MFDTEUD7MDDD5U")
				.awsSecretKey("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")
				.awsSesRegion("us-east-1")
				.isAwsSes(true).build();
		
		//take it default config from properties
		notificationService.notifyEmail(email);
		
		notificationService.notifyEmail(email,config);
		
		//return
		return "sent";
	}
	


	public static void main(String[] args) {
		SpringApplication.run(CMQSApp.class, args);
	}
}
