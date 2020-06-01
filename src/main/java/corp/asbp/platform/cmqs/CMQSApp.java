package corp.asbp.platform.cmqs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Narendra Padala
 *
 */
@SpringBootApplication
@EnableAsync // for faster processing
@PropertySource("classpath:cmqs.properties")
public class CMQSApp {

	public static void main(String[] args) {
		SpringApplication.run(CMQSApp.class, args);
	}
}
