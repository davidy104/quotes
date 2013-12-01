package nz.co.yellow.pure.quote.data.predicate;

import java.util.List;

import nz.co.yellow.pure.quote.data.QServiceProviderModel;

import com.mysema.query.types.Predicate;

public class ServiceProviderPredicates {
	public static Predicate findByProviderUserId(final String userId) {
		QServiceProviderModel serviceProviderModel = QServiceProviderModel.serviceProviderModel;
		return serviceProviderModel.userId.eq(userId);
	}

	public static Predicate findByProviderCustomerId(final String customerId) {
		QServiceProviderModel serviceProviderModel = QServiceProviderModel.serviceProviderModel;
		return serviceProviderModel.customerId.eq(customerId);
	}

	public static Predicate findByProviderIds(final List<Long> ids) {
		QServiceProviderModel serviceProviderModel = QServiceProviderModel.serviceProviderModel;
		return serviceProviderModel.providerId.in(ids);
	}
}
