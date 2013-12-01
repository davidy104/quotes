package nz.co.yellow.spider.messaging.data.predicate;

import java.util.Date;

import nz.co.yellow.spider.messaging.data.QMessageModel;

import com.mysema.query.types.Predicate;

public class MessagePredicates {
	public static Predicate findByTime(final Date sentTime) {
		QMessageModel message = QMessageModel.messageModel;
		return message.sentTime.eq(sentTime);
	}

	public static Predicate findBtTime(final Date from, final Date to) {
		QMessageModel message = QMessageModel.messageModel;
		return message.sentTime.between(from, to);
	}

	public static Predicate findByMessageThread(final Long threadId) {
		QMessageModel message = QMessageModel.messageModel;
		return message.thread.threadId.eq(threadId);
	}

	public static Predicate findByMessageThreadAndParticipant(
			final Long threadId, final String userId) {
		QMessageModel message = QMessageModel.messageModel;
		return message.thread.threadId.eq(threadId).and(
				message.createdBy.userId.eq(userId));
	}
}
