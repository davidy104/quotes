package nz.co.yellow.spider.messaging.data.predicate;

import nz.co.yellow.spider.messaging.data.QParticipantModel;

import com.mysema.query.types.Predicate;

public class ParticipantPredicates {
	public static Predicate findByUserId(final String userId) {
		QParticipantModel participant = QParticipantModel.participantModel;
		return participant.userId.eq(userId);
	}

}
