package nz.co.yellow.pure.quote.resource;

import javax.ws.rs.core.Response;

import nz.co.yellow.pure.quote.data.ServiceProviderReq;

public interface ServiceProviderAPI {

	Response createServiceProvider(ServiceProviderReq serviceProvider);

	Response updateServiceProvider(Long providerId,
			ServiceProviderReq serviceProvider);

	Response getServiceProviderById(Long providerId);

	Response getAllProvideQuotesByProviderId(Long providerId);
}
