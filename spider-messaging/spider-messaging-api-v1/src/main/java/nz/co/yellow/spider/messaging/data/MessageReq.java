package nz.co.yellow.spider.messaging.data;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

@SuppressWarnings("serial")
public class MessageReq extends Message implements Serializable {

	private Long threadId;

	private Participant creator;

	public Long getThreadId() {
		return threadId;
	}

	public void setThreadId(Long threadId) {
		this.threadId = threadId;
	}

	public Participant getCreator() {
		return creator;
	}

	public void setCreator(Participant creator) {
		this.creator = creator;
	}

	public static Builder getBuilder(String sentTime, String readTime,
			String content, String status) {
		return new Builder(sentTime, readTime, content, status);
	}

	public static Builder getBuilder(String sentTime, String readTime,
			String content, String status, Participant creator) {
		return new Builder(sentTime, readTime, content, status, creator);
	}

	public static Builder getBuilder(Long threadId, String sentTime,
			String content, String status, Participant creator) {
		return new Builder(threadId, sentTime, content, status, creator);
	}

	public static Builder getBuilder(String content, String status) {
		return new Builder(content, status);
	}

	public static class Builder {

		private MessageReq built;

		public Builder(String sentTime, String readTime, String content,
				String status) {
			built = new MessageReq();
			built.sentTime = sentTime;
			built.readTime = readTime;
			built.content = content;
			built.status = status;
		}

		public Builder(Long threadId, String sentTime, String content,
				String status, Participant creator) {
			built = new MessageReq();
			built.sentTime = sentTime;
			built.threadId = threadId;
			built.content = content;
			built.status = status;
			built.creator = creator;
		}

		public Builder(String sentTime, String readTime, String content,
				String status, Participant creator) {
			built = new MessageReq();
			built.sentTime = sentTime;
			built.readTime = readTime;
			built.content = content;
			built.status = status;
			built.creator = creator;
		}

		public Builder(String content, String status) {
			built = new MessageReq();
			built.content = content;
			built.status = status;
		}

		public MessageReq build() {
			return built;
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
