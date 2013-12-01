package nz.co.yellow.pure.quote.ds.converter;

import nz.co.yellow.pure.quote.data.ServiceConsumerModel;
import nz.co.yellow.pure.quote.data.ServiceConsumerReq;
import nz.co.yellow.pure.quote.data.ServiceConsumerResp;
import nz.co.yellow.pure.quote.ds.APIConverter;
import nz.co.yellow.pure.quote.ds.ConvertException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("serviceConsumerConverter")
public class ServiceConsumerConverter
		implements
		APIConverter<ServiceConsumerReq, ServiceConsumerModel, ServiceConsumerResp> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ServiceConsumerConverter.class);

	@Override
	public ServiceConsumerResp convertToResponse(ServiceConsumerModel model,
			Object... additionalSourceObj) throws ConvertException {
		LOGGER.debug("convert to dto start:{}", model);
		ServiceConsumerResp dto = ServiceConsumerResp.getBuilder(
				model.getUserId()).build();
		dto.setId(model.getConsumerId());
		LOGGER.debug("convert to dto end:{}", dto);
		return dto;
	}

	@Override
	public ServiceConsumerModel convertFromRequest(ServiceConsumerReq request,
			Object... additionalSourceObj) throws ConvertException {
		LOGGER.debug("convert to model start:{}", request);
		ServiceConsumerModel model = ServiceConsumerModel.getBuilder(
				request.getUserId()).build();
		LOGGER.debug("convert to model end:{}", model);
		return model;
	}

}
