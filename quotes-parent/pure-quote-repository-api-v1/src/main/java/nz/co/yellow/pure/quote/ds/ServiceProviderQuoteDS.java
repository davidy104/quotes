package nz.co.yellow.pure.quote.ds;

import nz.co.yellow.pure.quote.data.ServiceProviderQuote;

public interface ServiceProviderQuoteDS {

	ServiceProviderQuote getServiceProviderQuoteById(Long serviceProviderQuoteId)
			throws Exception;

	ServiceProviderQuote updateServiceProviderQuoteStatus(
			Long serviceProviderQuoteId, String status) throws Exception;
}
