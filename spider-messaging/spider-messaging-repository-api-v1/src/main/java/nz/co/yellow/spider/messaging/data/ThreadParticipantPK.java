package nz.co.yellow.spider.messaging.data;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 *
 * @author david
 *
 */
@SuppressWarnings("serial")
@Embeddable
public class ThreadParticipantPK implements Serializable {

	@ManyToOne
	private ThreadModel thread;

	@ManyToOne
	private ParticipantModel participant;

	public ThreadModel getThread() {
		return thread;
	}

	public void setThread(ThreadModel thread) {
		this.thread = thread;
	}

	public ParticipantModel getParticipant() {
		return participant;
	}

	public void setParticipant(ParticipantModel participant) {
		this.participant = participant;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ThreadParticipantPK that = (ThreadParticipantPK) o;

		if (thread != null ? !thread.equals(that.thread) : that.thread != null)
			return false;
		if (participant != null ? !participant.equals(that.participant)
				: that.participant != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = (thread != null ? thread.hashCode() : 0);
		result = 31 * result
				+ (participant != null ? participant.hashCode() : 0);
		return result;
	}
}
