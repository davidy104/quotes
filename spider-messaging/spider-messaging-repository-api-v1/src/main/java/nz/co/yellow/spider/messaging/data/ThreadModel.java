package nz.co.yellow.spider.messaging.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "thread")
public class ThreadModel implements Serializable {
	@Id
	@GeneratedValue(generator = "tSeq")
	@SequenceGenerator(name = "tSeq", sequenceName = "THREAD_SEQ")
	@Column(name = "THREAD_ID", insertable = false, updatable = false)
	private Long threadId;

	@Column(name = "TITLE")
	private String title;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "threadParticipantPk.thread")
	private List<ThreadParticipantModel> theadParticipants;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL }, mappedBy = "thread")
	private List<MessageModel> messages;

	@Column(name = "CREATE_TIME", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;

	@Column(name = "status")
	private String status;

	public Long getThreadId() {
		return threadId;
	}

	public void setThreadId(Long threadId) {
		this.threadId = threadId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public List<ThreadParticipantModel> getTheadParticipants() {
		return theadParticipants;
	}

	public List<MessageModel> getMessages() {
		return messages;
	}

	public void addMessageModel(MessageModel messageModel) {
		if (this.messages == null) {
			messages = new ArrayList<MessageModel>();
		}
		messages.add(messageModel);
	}

	public void addThreadParticipantModel(
			ThreadParticipantModel threadParticipantModel) {
		if (this.theadParticipants == null) {
			this.theadParticipants = new ArrayList<ThreadParticipantModel>();
		}
		this.theadParticipants.add(threadParticipantModel);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void addMessage(MessageModel message) {
		if (messages == null) {
			messages = new ArrayList<MessageModel>();
		}
		messages.add(message);
	}

	public static Builder getBuilder(String title, Date createdTime) {
		return new Builder(title, createdTime);
	}

	public static Builder getBuilder(String title, Date createdTime,
			String status) {
		return new Builder(title, createdTime, status);
	}

	public static Builder getBuilder(String title) {
		return new Builder(title);
	}

	public static Builder getBuilder(String title, String status) {
		return new Builder(title, status);
	}

	public static class Builder {

		private ThreadModel built;

		public Builder(String title, Date createdTime) {
			built = new ThreadModel();
			built.title = title;
			built.createdTime = createdTime;
		}

		public Builder(String title, Date createdTime, String status) {
			built = new ThreadModel();
			built.title = title;
			built.status = status;
			built.createdTime = createdTime;
		}

		public Builder(String title) {
			built = new ThreadModel();
			built.title = title;
		}

		public Builder(String title, String status) {
			built = new ThreadModel();
			built.title = title;
			built.status = status;
		}

		public ThreadModel build() {
			return built;
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
				.append("threadId", threadId).append("title", title)
				.append("status", status)
				.append("createdTime", createdTime).toString();

	}
}
