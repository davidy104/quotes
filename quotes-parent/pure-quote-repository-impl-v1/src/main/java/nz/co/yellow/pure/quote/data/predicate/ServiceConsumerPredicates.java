package nz.co.yellow.pure.quote.data.predicate;

import java.util.List;

import nz.co.yellow.pure.quote.data.QServiceConsumerModel;

import com.mysema.query.types.Predicate;

public class ServiceConsumerPredicates {

	public static Predicate findByConsumerUserId(final String userId) {
		QServiceConsumerModel serviceConsumerModel = QServiceConsumerModel.serviceConsumerModel;
		return serviceConsumerModel.userId.eq(userId);
	}

	public static Predicate findByConsumerIds(final List<Long> ids) {
		QServiceConsumerModel serviceConsumerModel = QServiceConsumerModel.serviceConsumerModel;
		return serviceConsumerModel.consumerId.in(ids);
	}
}
