package nz.co.yellow.spider.messaging.data;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

@SuppressWarnings("serial")
public class MessageThreadResp extends MessageThread implements Serializable {
	private Long messageThreadId;

	private List<MessageResp> messages;

	public List<MessageResp> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageResp> messages) {
		this.messages = messages;
	}

	public Long getMessageThreadId() {
		return messageThreadId;
	}

	public void setMessageThreadId(Long messageThreadId) {
		this.messageThreadId = messageThreadId;
	}

	public static Builder getBuilder(String subject, String status) {
		return new Builder(subject, status);
	}

	public static Builder getBuilder(String subject) {
		return new Builder(subject);
	}

	public static Builder getBuilder(String subject, String createdTime,
			List<MessageResp> messages) {
		return new Builder(subject, createdTime, messages);
	}

	public static Builder getBuilder(String subject, String createdTime,
			String status, List<MessageResp> messages) {
		return new Builder(subject, createdTime, status, messages);
	}

	public static class Builder {

		private MessageThreadResp built;

		public Builder(String subject, String status) {
			built = new MessageThreadResp();
			built.subject = subject;
			built.status = status;
		}

		public Builder(String subject) {
			built = new MessageThreadResp();
			built.subject = subject;
		}

		public Builder(String subject, String createdTime,
				List<MessageResp> messages) {
			built = new MessageThreadResp();
			built.subject = subject;
			built.createdTime = createdTime;
			built.messages = messages;
		}

		public Builder(String subject, String createdTime, String status,
				List<MessageResp> messages) {
			built = new MessageThreadResp();
			built.subject = subject;
			built.createdTime = createdTime;
			built.status = status;
			built.messages = messages;
		}

		public MessageThreadResp build() {
			return built;
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
