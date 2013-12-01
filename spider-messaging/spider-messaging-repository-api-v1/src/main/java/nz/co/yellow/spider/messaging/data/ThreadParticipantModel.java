package nz.co.yellow.spider.messaging.data;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author david
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "Thread_participant")
@AssociationOverrides({
    @AssociationOverride(name = "threadParticipantPk.participant", joinColumns = @JoinColumn(name = "participant_id")),
    @AssociationOverride(name = "threadParticipantPk.thread", joinColumns = @JoinColumn(name = "THREAD_ID")) })
public class ThreadParticipantModel
    implements Serializable {

  @Id
  @GeneratedValue(generator = "tpSeq")
  @SequenceGenerator(name = "tpSeq", sequenceName = "THREAD_PARTICIPANT_ID_SEQ")
  @Column(name = "THREAD_PARTICIPANT_ID")
  private Long threadParticipantId;

  @Embedded
  private ThreadParticipantPK threadParticipantPk = new ThreadParticipantPK();

  public ThreadParticipantPK getThreadParticipantPk() {
    return threadParticipantPk;
  }

  public void setThreadParticipantPk(
      ThreadParticipantPK threadParticipantPk) {
    this.threadParticipantPk = threadParticipantPk;
  }

  public Long getThreadParticipantId() {
    return threadParticipantId;
  }

  public void setThreadParticipantId(Long threadParticipantId) {
    this.threadParticipantId = threadParticipantId;
  }

  @Transient
  public ThreadModel getThreadModel() {
    return getThreadParticipantPk().getThread();
  }

  public void setThreadModel(ThreadModel thread) {
    getThreadParticipantPk().setThread(thread);
  }

  @Transient
  public ParticipantModel getParticipantModel() {
    return getThreadParticipantPk().getParticipant();
  }

  public void setParticipantModel(
      ParticipantModel participant) {
    getThreadParticipantPk().setParticipant(participant);
  }

  public static Builder getBuilder(ThreadModel thread,
      ParticipantModel participant) {
    return new Builder(thread, participant);
  }

  public static class Builder {

    private ThreadParticipantModel built;

    public Builder(ThreadModel thread,
        ParticipantModel participant) {
      built = new ThreadParticipantModel();
      built.getThreadParticipantPk().setThread(thread);
      built.getThreadParticipantPk().setParticipant(participant);
    }

    public ThreadParticipantModel build() {
      return built;
    }
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    ThreadParticipantModel that = (ThreadParticipantModel) o;

    if (getThreadParticipantPk() != null ? !getThreadParticipantPk()
        .equals(that.getThreadParticipantPk()) : that
        .getThreadParticipantPk() != null)
      return false;

    return true;
  }

  public int hashCode() {
    return (getThreadParticipantPk() != null ? getThreadParticipantPk()
        .hashCode() : 0);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE)
        .append("threadParticipantId", threadParticipantId)
        .append("msgThreadId",
            getThreadModel().getThreadId())
        .append("participantId",
            getParticipantModel().getParticipantId())
        .toString();

  }
}
