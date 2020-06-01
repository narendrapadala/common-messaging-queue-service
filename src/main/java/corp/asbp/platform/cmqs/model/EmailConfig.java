package corp.asbp.platform.cmqs.model;

/**
 * @author Narendra Padala
 *
 */
public class EmailConfig {

	private boolean isAwsSes;
	private String awsAccessKey;
	private String awsSecretKey;
	private String awsSesRegion;

	private Long smtpPort;
	private String smtpHost;
	private String smtpUser;
	private String smtpPass;

	public static class Builder {

		private boolean isAwsSes;
		private String awsAccessKey;
		private String awsSecretKey;
		private String awsSesRegion;
		private Long smtpPort;
		private String smtpHost;
		private String smtpUser;
		private String smtpPass;

		public Builder() {
		}

		Builder(boolean isAwsSes, String awsAccessKey, String awsSecretKey, String awsSesRegion, Long smtpPort,
				String smtpHost, String smtpUser, String smtpPass) {
			this.isAwsSes = isAwsSes;
			this.awsAccessKey = awsAccessKey;
			this.awsSecretKey = awsSecretKey;
			this.awsSesRegion = awsSesRegion;
			this.smtpPort = smtpPort;
			this.smtpHost = smtpHost;
			this.smtpUser = smtpUser;
			this.smtpPass = smtpPass;
		}

		public Builder isAwsSes(boolean isAwsSes) {
			this.isAwsSes = isAwsSes;
			return Builder.this;
		}

		public Builder awsAccessKey(String awsAccessKey) {
			this.awsAccessKey = awsAccessKey;
			return Builder.this;
		}

		public Builder awsSecretKey(String awsSecretKey) {
			this.awsSecretKey = awsSecretKey;
			return Builder.this;
		}

		public Builder awsSesRegion(String awsSesRegion) {
			this.awsSesRegion = awsSesRegion;
			return Builder.this;
		}

		public Builder smtpPort(Long smtpPort) {
			this.smtpPort = smtpPort;
			return Builder.this;
		}

		public Builder smtpHost(String smtpHost) {
			this.smtpHost = smtpHost;
			return Builder.this;
		}

		public Builder smtpUser(String smtpUser) {
			this.smtpUser = smtpUser;
			return Builder.this;
		}

		public Builder smtpPass(String smtpPass) {
			this.smtpPass = smtpPass;
			return Builder.this;
		}

		public EmailConfig build() {

			return new EmailConfig(this);
		}
	}

	private EmailConfig(Builder builder) {
		this.isAwsSes = builder.isAwsSes;
		this.awsAccessKey = builder.awsAccessKey;
		this.awsSecretKey = builder.awsSecretKey;
		this.awsSesRegion = builder.awsSesRegion;
		this.smtpPort = builder.smtpPort;
		this.smtpHost = builder.smtpHost;
		this.smtpUser = builder.smtpUser;
		this.smtpPass = builder.smtpPass;
	}

	public void doSomething() {
		// do something
	}

	public String getAwsSesRegion() {
		return awsSesRegion;
	}

	public void setAwsSesRegion(String awsSesRegion) {
		this.awsSesRegion = awsSesRegion;
	}

	public String getAwsAccessKey() {
		return awsAccessKey;
	}

	public void setAwsAccessKey(String awsAccessKey) {
		this.awsAccessKey = awsAccessKey;
	}

	public String getAwsSecretKey() {
		return awsSecretKey;
	}

	public void setAwsSecretKey(String awsSecretKey) {
		this.awsSecretKey = awsSecretKey;
	}

	public boolean isAwsSes() {
		return isAwsSes;
	}

	public void setAwsSes(boolean isAwsSes) {
		this.isAwsSes = isAwsSes;
	}

	public Long getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(Long smtpPort) {
		this.smtpPort = smtpPort;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public String getSmtpUser() {
		return smtpUser;
	}

	public void setSmtpUser(String smtpUser) {
		this.smtpUser = smtpUser;
	}

	public String getSmtpPass() {
		return smtpPass;
	}

	public void setSmtpPass(String smtpPass) {
		this.smtpPass = smtpPass;
	}

}
