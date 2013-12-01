package nz.co.yellow.pure.quote.resource;

import javax.ws.rs.core.Response;

import nz.co.yellow.pure.quote.data.ServiceProviderQuote;

public interface ServiceProviderQuoteAPI {

	Response getServiceProviderQuoteById(Long serviceProviderQuoteId);

	Response updateServiceProviderQuoteStatus(Long serviceProviderQuoteId,
			ServiceProviderQuote serviceProviderQuote);
}
