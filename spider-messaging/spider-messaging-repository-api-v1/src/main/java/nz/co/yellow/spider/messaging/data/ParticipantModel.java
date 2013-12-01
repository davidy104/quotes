package nz.co.yellow.spider.messaging.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * @author david
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "participant")
public class ParticipantModel implements Serializable {

	@Id
	@GeneratedValue(generator = "pSeq")
	@SequenceGenerator(name = "pSeq", sequenceName = "PARTICIPANT_SEQ")
	@Column(name = "participant_id", insertable = false, updatable = false)
	private Long participantId;

	@Column(name = "USER_ID")
	private String userId;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "threadParticipantPk.participant")
	private List<ThreadParticipantModel> theadParticipants;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "createdBy")
	private List<MessageModel> messages;

	public Long getParticipantId() {
		return participantId;
	}

	public void setParticipantId(Long participantId) {
		this.participantId = participantId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<ThreadParticipantModel> getTheadParticipants() {
		return theadParticipants;
	}

	public List<MessageModel> getMessages() {
		return messages;
	}

	public void addMessage(MessageModel messageModel) {
		if (messages == null) {
			messages = new ArrayList<MessageModel>();
		}
		messages.add(messageModel);
	}

	public void addThreadParticipant(ThreadParticipantModel threadParticipant) {
		if (theadParticipants == null) {
			theadParticipants = new ArrayList<ThreadParticipantModel>();
		}
		theadParticipants.add(threadParticipant);
	}

	public static Builder getBuilder(String userId) {
		return new Builder(userId);
	}

	public static class Builder {

		private ParticipantModel built;

		public Builder(String userId) {
			built = new ParticipantModel();
			built.userId = userId;
		}

		public ParticipantModel build() {
			return built;
		}
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
				.append("participantId", participantId)
				.append("userId", userId).toString();
	}
}
