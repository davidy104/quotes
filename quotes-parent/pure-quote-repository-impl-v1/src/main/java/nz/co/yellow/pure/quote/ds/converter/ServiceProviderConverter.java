package nz.co.yellow.pure.quote.ds.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import nz.co.yellow.pure.quote.data.QuoteLoadStrategies;
import nz.co.yellow.pure.quote.data.ServiceProviderModel;
import nz.co.yellow.pure.quote.data.ServiceProviderQuote;
import nz.co.yellow.pure.quote.data.ServiceProviderQuoteModel;
import nz.co.yellow.pure.quote.data.ServiceProviderReq;
import nz.co.yellow.pure.quote.data.ServiceProviderResp;
import nz.co.yellow.pure.quote.ds.APIConverter;
import nz.co.yellow.pure.quote.ds.ConvertException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("serviceProviderConverter")
public class ServiceProviderConverter
		implements
		APIConverter<ServiceProviderReq, ServiceProviderModel, ServiceProviderResp> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ServiceProviderConverter.class);

	@Resource
	private ServiceProviderQuoteConverter serviceProviderQuoteConverter;

	@Override
	public ServiceProviderResp convertToResponse(ServiceProviderModel model,
			Object... additionalSourceObj) throws ConvertException {
		LOGGER.debug("convert to dto start:{}", model);
		QuoteLoadStrategies loadStrategies = QuoteLoadStrategies.NONE;
		ServiceProviderResp dto = ServiceProviderResp
				.getBuilder(model.getProviderId(), model.getUserId(),
						model.getCustomerId()).build();
		dto.setId(model.getProviderId());
		if (model.getBoostWeight() != null) {
			dto.setBoostWeight(model.getBoostWeight().toString());
		}

		if (additionalSourceObj != null && additionalSourceObj.length > 0) {
			loadStrategies = (QuoteLoadStrategies) additionalSourceObj[0];
		}

		if (loadStrategies == QuoteLoadStrategies.ALL
				|| loadStrategies == QuoteLoadStrategies.LOAD_PROVIDER_QUOTE_REQUEST) {
			this.loadProviderQuoteRequest(model, dto);
		}

		LOGGER.debug("convert to dto end:{}", dto);
		return dto;
	}

	@Override
	public ServiceProviderModel convertFromRequest(ServiceProviderReq request,
			Object... additionalSourceObj) throws ConvertException {
		LOGGER.debug("convert to model start:{}", request);
		ServiceProviderModel model = ServiceProviderModel.getBuilder(
				request.getUserId(), request.getCustomerId()).build();

		if (!StringUtils.isEmpty(request.getBoostWeight())) {
			LOGGER.debug("get boostWeight from dto:{}",
					request.getBoostWeight());
			model.setBoostWeight(new BigDecimal(request.getBoostWeight()));
		}
		LOGGER.debug("convert to model end:{}", model);
		return model;
	}

	private void loadProviderQuoteRequest(ServiceProviderModel model,
			ServiceProviderResp dto) throws ConvertException {
		List<ServiceProviderQuoteModel> providerQuoteReqList = model
				.getProviderQuotesList();
		List<ServiceProviderQuote> serviceProviderQuotes = null;
		if (providerQuoteReqList != null && providerQuoteReqList.size() > 0) {
			serviceProviderQuotes = new ArrayList<ServiceProviderQuote>();
			for (ServiceProviderQuoteModel spqModel : providerQuoteReqList) {
				serviceProviderQuotes.add(serviceProviderQuoteConverter
						.convertFrom(spqModel));
			}
			dto.setServiceProviderQuotes(serviceProviderQuotes);
		}
	}

}
