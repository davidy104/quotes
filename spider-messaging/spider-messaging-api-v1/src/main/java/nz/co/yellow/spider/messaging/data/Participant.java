package nz.co.yellow.spider.messaging.data;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

@SuppressWarnings("serial")
public class Participant implements Serializable {

	private Long participantId;

	private String userId;

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

	public void update(String userId) {
		this.userId = userId;

	}

	public static Builder getBuilder(String userId) {
		return new Builder(userId);
	}

	public static class Builder {

		private Participant built;

		public Builder(String userId) {
			built = new Participant();
			built.userId = userId;
		}

		public Participant build() {
			return built;
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
