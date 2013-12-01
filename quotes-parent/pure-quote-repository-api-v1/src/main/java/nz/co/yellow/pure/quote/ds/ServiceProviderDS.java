package nz.co.yellow.pure.quote.ds;

import java.util.List;

import nz.co.yellow.pure.quote.data.QuoteLoadStrategies;
import nz.co.yellow.pure.quote.data.ServiceProviderReq;
import nz.co.yellow.pure.quote.data.ServiceProviderResp;

public interface ServiceProviderDS {

	ServiceProviderResp createServiceProvider(ServiceProviderReq serviceProvider)
			throws Exception;

	ServiceProviderResp getServiceProviderById(Long providerId,
			QuoteLoadStrategies loadStrategies) throws Exception;

	ServiceProviderResp updateServiceProvider(Long providerId,
			ServiceProviderReq serviceProvider) throws Exception;

	List<ServiceProviderResp> getServiceProvidersByIds(List<Long> ids,
			QuoteLoadStrategies loadStrategies) throws Exception;
}
