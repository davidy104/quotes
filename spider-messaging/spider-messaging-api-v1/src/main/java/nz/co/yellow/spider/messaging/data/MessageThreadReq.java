package nz.co.yellow.spider.messaging.data;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

@SuppressWarnings("serial")
public class MessageThreadReq extends MessageThread implements Serializable {
	private List<MessageReq> messages;

	public List<MessageReq> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageReq> messages) {
		this.messages = messages;
	}

	public static Builder getBuilder(String subject, String status) {
		return new Builder(subject, status);
	}

	public static Builder getBuilder(String subject) {
		return new Builder(subject);
	}

	public static Builder getBuilder(String subject, String createdTime,
			List<MessageReq> messages) {
		return new Builder(subject, createdTime, messages);
	}

	public static Builder getBuilder(String subject, String createdTime,
			String status, List<MessageReq> messages) {
		return new Builder(subject, createdTime, status, messages);
	}

	public static class Builder {

		private MessageThreadReq built;

		public Builder(String subject, String status) {
			built = new MessageThreadReq();
			built.subject = subject;
			built.status = status;
		}

		public Builder(String subject) {
			built = new MessageThreadReq();
			built.subject = subject;
		}

		public Builder(String subject, String createdTime,
				List<MessageReq> messages) {
			built = new MessageThreadReq();
			built.subject = subject;
			built.createdTime = createdTime;
			built.messages = messages;
		}

		public Builder(String subject, String createdTime, String status,
				List<MessageReq> messages) {
			built = new MessageThreadReq();
			built.subject = subject;
			built.createdTime = createdTime;
			built.status = status;
			built.messages = messages;
		}

		public MessageThreadReq build() {
			return built;
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
