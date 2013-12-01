package nz.co.yellow.spider.messaging.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * @author david
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Message")
public class MessageModel implements Serializable {

	@Id
	@GeneratedValue(generator = "mSeq")
	@SequenceGenerator(name = "mSeq", sequenceName = "MESSAGE_SEQ")
	@Column(name = "message_id", insertable = false, updatable = false)
	private Long messageId;

	@Column(name = "SENT_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date sentTime;

	@Column(name = "READ_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date readTime;

	@Column(name = "CONTENT")
	private String content;

	@Column(name = "STATUS")
	private String status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "THREAD_ID", referencedColumnName = "THREAD_ID")
	private ThreadModel thread;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATEDBY", referencedColumnName = "participant_id")
	private ParticipantModel createdBy;

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public Date getSentTime() {
		return sentTime;
	}

	public void setSentTime(Date sentTime) {
		this.sentTime = sentTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ParticipantModel getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(ParticipantModel createdBy) {
		this.createdBy = createdBy;
	}

	public ThreadModel getThread() {
		return thread;
	}

	public void setThread(ThreadModel thread) {
		this.thread = thread;
	}

	public Date getReadTime() {
		return readTime;
	}

	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static Builder getBuilder(String content, ThreadModel thread,
			ParticipantModel createdBy, Date sentTime, String status) {
		return new Builder(content, thread, createdBy, sentTime, status);
	}

	public static Builder getBuilder(String content, ThreadModel thread,
			ParticipantModel createdBy, Date sentTime, Date readTime) {
		return new Builder(content, thread, createdBy, sentTime, readTime);
	}

	public static Builder getBuilder(String content,
			ParticipantModel createdBy, Date sentTime, String status) {
		return new Builder(content, createdBy, sentTime, status);
	}

	public static Builder getBuilder(String content, Date sentTime,
			String status) {
		return new Builder(content, sentTime, status);
	}

	public static Builder getBuilder(String content, String status) {
		return new Builder(content, status);
	}

	public static class Builder {

		private MessageModel built;

		public Builder(String content) {
			built = new MessageModel();
			built.content = content;
		}

		public Builder(String content, ParticipantModel createdBy,
				Date sentTime, String status) {
			built = new MessageModel();
			built.content = content;
			built.createdBy = createdBy;
			built.sentTime = sentTime;
			built.status = status;
		}

		public Builder(String content, ThreadModel thread,
				ParticipantModel createdBy, Date sentTime, String status) {
			built = new MessageModel();
			built.content = content;
			built.createdBy = createdBy;
			built.thread = thread;
			built.sentTime = sentTime;
			built.status = status;
		}

		public Builder(String content, ThreadModel thread,
				ParticipantModel createdBy, Date sentTime, Date readTime) {
			built = new MessageModel();
			built.content = content;
			built.createdBy = createdBy;
			built.thread = thread;
			built.sentTime = sentTime;
			built.readTime = readTime;
		}

		public Builder(String content, Date sentTime, String status) {
			built = new MessageModel();
			built.content = content;
			built.sentTime = sentTime;
			built.status = status;
		}

		public Builder(String content, String status) {
			built = new MessageModel();
			built.content = content;
			built.status = status;
		}

		public MessageModel build() {
			return built;
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
				.append("messageId", messageId).append("content", content)
				.append("sentTime", sentTime).toString();

	}

}
