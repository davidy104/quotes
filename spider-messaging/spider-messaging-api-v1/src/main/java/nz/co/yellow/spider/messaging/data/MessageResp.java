package nz.co.yellow.spider.messaging.data;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

@SuppressWarnings("serial")
public class MessageResp extends Message implements Serializable {
	private Long messageId;

	private String creatorUserId;

	public String getCreatorUserId() {
		return creatorUserId;
	}

	public void setCreatorUserId(String creatorUserId) {
		this.creatorUserId = creatorUserId;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public static Builder getBuilder(String sentTime, String readTime,
			String content, String status) {
		return new Builder(sentTime, readTime, content, status);
	}

	public static Builder getBuilder(String sentTime, String readTime,
			String content, String status, String creatorUserId) {
		return new Builder(sentTime, readTime, content, status, creatorUserId);
	}

	public static Builder getBuilder(String content, String status) {
		return new Builder(content, status);
	}

	public static class Builder {

		private MessageResp built;

		public Builder(String sentTime, String readTime, String content,
				String status) {
			built = new MessageResp();
			built.sentTime = sentTime;
			built.readTime = readTime;
			built.content = content;
			built.status = status;
		}

		public Builder(String sentTime, String readTime, String content,
				String status, String creatorUserId) {
			built = new MessageResp();
			built.sentTime = sentTime;
			built.readTime = readTime;
			built.content = content;
			built.status = status;
			built.creatorUserId = creatorUserId;
		}

		public Builder(String content, String status) {
			built = new MessageResp();
			built.content = content;
			built.status = status;
		}

		public MessageResp build() {
			return built;
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
