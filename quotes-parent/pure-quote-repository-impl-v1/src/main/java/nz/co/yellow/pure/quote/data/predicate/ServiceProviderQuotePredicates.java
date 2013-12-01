package nz.co.yellow.pure.quote.data.predicate;

import java.util.List;

import nz.co.yellow.pure.quote.data.QServiceProviderQuoteModel;

import com.mysema.query.types.Predicate;

public class ServiceProviderQuotePredicates {
	public static Predicate findByStatus(final String status) {
		QServiceProviderQuoteModel serviceProviderQuoteModel = QServiceProviderQuoteModel.serviceProviderQuoteModel;
		return serviceProviderQuoteModel.status.eq(status);
	}

	public static Predicate findByProviderQuoteReqIds(final List<Long> ids) {
		QServiceProviderQuoteModel serviceProviderQuoteModel = QServiceProviderQuoteModel.serviceProviderQuoteModel;
		return serviceProviderQuoteModel.providerQuoteId.in(ids);
	}

	public static Predicate findByServiceProviderId(final Long providerId) {
		QServiceProviderQuoteModel serviceProviderQuoteModel = QServiceProviderQuoteModel.serviceProviderQuoteModel;
		if (providerId != null) {
			return serviceProviderQuoteModel.serviceProvider.providerId
					.eq(providerId);
		}
		return null;
	}
}
