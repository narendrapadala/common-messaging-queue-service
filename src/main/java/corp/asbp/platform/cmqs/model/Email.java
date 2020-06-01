package corp.asbp.platform.cmqs.model;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Narendra Padala
 *
 */
public class Email {

	private String fromAddress;
	private List<String> toAddress;
	private String subject;
	private String message;
	private String messageType;
	private List<String> attachments;
	private List<String> ccAddress;
	private List<String> bccAddress;

	public static class Builder {

		private String fromAddress;
		private List<String> toAddress = new ArrayList<String>();
		private String subject;
		private String message;
		private String messageType;
		private List<String> attachments = new ArrayList<String>();
		private List<String> ccAddress = new ArrayList<String>();
		private List<String> bccAddress = new ArrayList<String>();

		public Builder() {
		}

		Builder(String fromAddress, List<String> toAddress, String subject, String message, String messageType,
				List<String> attachments, List<String> ccAddress, List<String> bccAddress) {
			this.fromAddress = fromAddress;
			this.toAddress = toAddress;
			this.subject = subject;
			this.message = message;
			this.messageType = messageType;
			this.attachments = attachments;
			this.ccAddress = ccAddress;
			this.bccAddress = bccAddress;
		}

		public Builder fromAddress(String fromAddress) {
			this.fromAddress = fromAddress;
			return Builder.this;
		}

		public Builder toAddress(List<String> toAddress) {
			this.toAddress = toAddress;
			return Builder.this;
		}

		public Builder addToAddress(String toAddress) {
			this.toAddress.add(toAddress);
			return Builder.this;
		}

		public Builder subject(String subject) {
			this.subject = subject;
			return Builder.this;
		}

		public Builder message(String message) {
			this.message = message;
			return Builder.this;
		}

		public Builder messageType(String messageType) {
			this.messageType = messageType;
			return Builder.this;
		}

		public Builder attachments(List<String> attachments) {
			this.attachments = attachments;
			return Builder.this;
		}

		public Builder addAttachments(String attachments) {
			this.attachments.add(attachments);
			return Builder.this;
		}

		public Builder ccAddress(List<String> ccAddress) {
			this.ccAddress = ccAddress;
			return Builder.this;
		}

		public Builder addCcAddress(String ccAddress) {
			this.ccAddress.add(ccAddress);
			return Builder.this;
		}

		public Builder bccAddress(List<String> bccAddress) {
			this.bccAddress = bccAddress;
			return Builder.this;
		}

		public Builder addBccAddress(String bccAddress) {
			this.bccAddress.add(bccAddress);
			return Builder.this;
		}

		public Email build() {
			if (this.fromAddress == null) {
				throw new NullPointerException("The property \"fromAddress\" is null. "
						+ "Please set the value by \"fromAddress()\". "
						+ "The properties \"fromAddress\", \"toAddress\", \"subject\", \"message\" and \"messageType\" are required.");
			}
			if (this.toAddress == null) {
				throw new NullPointerException("The property \"toAddress\" is null. "
						+ "Please set the value by \"toAddress()\". "
						+ "The properties \"fromAddress\", \"toAddress\", \"subject\", \"message\" and \"messageType\" are required.");
			}
			if (this.subject == null) {
				throw new NullPointerException("The property \"subject\" is null. "
						+ "Please set the value by \"subject()\". "
						+ "The properties \"fromAddress\", \"toAddress\", \"subject\", \"message\" and \"messageType\" are required.");
			}
			if (this.message == null) {
				throw new NullPointerException("The property \"message\" is null. "
						+ "Please set the value by \"message()\". "
						+ "The properties \"fromAddress\", \"toAddress\", \"subject\", \"message\" and \"messageType\" are required.");
			}
			if (this.messageType == null) {
				throw new NullPointerException("The property \"messageType\" is null. "
						+ "Please set the value by \"messageType()\". "
						+ "The properties \"fromAddress\", \"toAddress\", \"subject\", \"message\" and \"messageType\" are required.");
			}

			return new Email(this);
		}
	}

	private Email(Builder builder) {
		this.fromAddress = builder.fromAddress;
		this.toAddress = builder.toAddress;
		this.subject = builder.subject;
		this.message = builder.message;
		this.messageType = builder.messageType;
		this.attachments = builder.attachments;
		this.ccAddress = builder.ccAddress;
		this.bccAddress = builder.bccAddress;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public List<String> getToAddress() {
		return toAddress;
	}

	public void setToAddress(List<String> toAddress) {
		this.toAddress = toAddress;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public List<String> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<String> attachments) {
		this.attachments = attachments;
	}

	public List<String> getCcAddress() {
		return ccAddress;
	}

	public void setCcAddress(List<String> ccAddress) {
		this.ccAddress = ccAddress;
	}

	public List<String> getBccAddress() {
		return bccAddress;
	}

	public void setBccAddress(List<String> bccAddress) {
		this.bccAddress = bccAddress;
	}

	public void doSomething() {
		// do something
	}
}
