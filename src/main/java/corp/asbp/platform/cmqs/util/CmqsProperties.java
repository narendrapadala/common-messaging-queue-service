package corp.asbp.platform.cmqs.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Narendra Padala
 *
 */
@Component
public class CmqsProperties {

	@Value("${common.thread.pool.enabled:false}")
	private Boolean isCommonThreadPoolEnabled;

	@Value("${thread.pool.core.size:50}")
	private Integer threadPoolCoreSize;

	@Value("${thread.pool.max.size:50}")
	private Integer threadPoolMaxSize;

	@Value("${cmsqs.aws.access.key}")
	private String awsAccessKey;

	@Value("${cmsqs.aws.secret.key}")
	private String awsSecretKey;

	@Value("${cmsqs.aws.ses.region}")
	private String awsSesRegion;

	@Value("${cmsqs.aws.ses:false}")
	private boolean isAwsSes;

	@Value("${cmsqs.mail.smtp.port:587}")
	private Long smtpPort;

	@Value("${cmsqs.mail.smtp.host}")
	private String smtpHost;

	@Value("${cmsqs.mail.smtp.user}")
	private String smtpUser;

	@Value("${cmsqs.mail.smtp.pass}")
	private String smtpPass;

	/**
	 * @return
	 */
	public Boolean getIsCommonThreadPoolEnabled() {
		return isCommonThreadPoolEnabled;
	}

	public Integer getThreadPoolCoreSize() {
		return threadPoolCoreSize;
	}

	/**
	 * @return
	 */
	public Integer getThreadPoolMaxSize() {
		return threadPoolMaxSize;
	}

	/**
	 * @return
	 */
	public String getAwsAccessKey() {
		return awsAccessKey;
	}

	/**
	 * @param awsAccessKey
	 */
	public void setAwsAccessKey(String awsAccessKey) {
		this.awsAccessKey = awsAccessKey;
	}

	/**
	 * @return
	 */
	public String getAwsSecretKey() {
		return awsSecretKey;
	}

	/**
	 * @param awsSecretKey
	 */
	public void setAwsSecretKey(String awsSecretKey) {
		this.awsSecretKey = awsSecretKey;
	}

	/**
	 * @return
	 */
	public boolean isAwsSes() {
		return isAwsSes;
	}

	/**
	 * @param isAwsSes
	 */
	public void setAwsSes(boolean isAwsSes) {
		this.isAwsSes = isAwsSes;
	}

	/**
	 * @return
	 */
	public String getAwsSesRegion() {
		return awsSesRegion;
	}

	/**
	 * @param awsSesRegion
	 */
	public void setAwsSesRegion(String awsSesRegion) {
		this.awsSesRegion = awsSesRegion;
	}

	/**
	 * @return
	 */
	public Long getSmtpPort() {
		return smtpPort;
	}

	/**
	 * @param smtpPort
	 */
	public void setSmtpPort(Long smtpPort) {
		this.smtpPort = smtpPort;
	}

	/**
	 * @return
	 */
	public String getSmtpHost() {
		return smtpHost;
	}

	/**
	 * @param smtpHost
	 */
	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	/**
	 * @return
	 */
	public String getSmtpUser() {
		return smtpUser;
	}

	/**
	 * @param smtpUser
	 */
	public void setSmtpUser(String smtpUser) {
		this.smtpUser = smtpUser;
	}

	/**
	 * @return
	 */
	public String getSmtpPass() {
		return smtpPass;
	}

	/**
	 * @param smtpPass
	 */
	public void setSmtpPass(String smtpPass) {
		this.smtpPass = smtpPass;
	}

}
