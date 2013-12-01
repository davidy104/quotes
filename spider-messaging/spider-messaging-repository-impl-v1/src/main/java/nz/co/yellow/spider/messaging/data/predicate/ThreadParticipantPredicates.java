package nz.co.yellow.spider.messaging.data.predicate;

import nz.co.yellow.spider.messaging.data.QThreadParticipantModel;

import com.mysema.query.types.Predicate;

public class ThreadParticipantPredicates {

	public static Predicate findThreadsByParticipantUserId(final String userId) {
		QThreadParticipantModel threadParticipant = QThreadParticipantModel.threadParticipantModel;
		return threadParticipant.threadParticipantPk.participant.userId
				.eq(userId);
	}

	public static Predicate findThreadsByParticipantId(final Long participantId) {
		QThreadParticipantModel threadParticipant = QThreadParticipantModel.threadParticipantModel;
		return threadParticipant.threadParticipantPk.participant.participantId
				.eq(participantId);
	}

}
