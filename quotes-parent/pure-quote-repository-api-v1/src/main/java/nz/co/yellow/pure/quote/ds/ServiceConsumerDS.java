package nz.co.yellow.pure.quote.ds;

import java.util.List;

import nz.co.yellow.pure.quote.data.ServiceConsumerResp;
import nz.co.yellow.pure.quote.data.ServiceConsumerReq;

public interface ServiceConsumerDS {

	ServiceConsumerResp createServiceConsumer(ServiceConsumerReq serviceConsumer)
			throws Exception;

	ServiceConsumerResp updateServiceConsumer(Long id,
			ServiceConsumerReq serviceConsumer) throws Exception;

	ServiceConsumerResp getServiceConsumerById(Long consumerId) throws Exception;

	ServiceConsumerResp getServiceConsumerByUserId(String userId) throws Exception;

	List<ServiceConsumerResp> getServiceConsumersByIds(List<Long> ids)
			throws Exception;
}
