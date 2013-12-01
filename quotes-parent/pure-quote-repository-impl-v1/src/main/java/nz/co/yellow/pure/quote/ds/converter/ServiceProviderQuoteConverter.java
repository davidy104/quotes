package nz.co.yellow.pure.quote.ds.converter;

import java.util.Date;

import nz.co.yellow.pure.quote.data.ServiceProviderQuote;
import nz.co.yellow.pure.quote.data.ServiceProviderQuoteModel;
import nz.co.yellow.pure.quote.ds.ConvertException;
import nz.co.yellow.pure.quote.ds.GeneralConverter;
import nz.co.yellow.pure.quote.util.QuotesUtils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("serviceProviderQuoteConverter")
public class ServiceProviderQuoteConverter implements
		GeneralConverter<ServiceProviderQuote, ServiceProviderQuoteModel> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ServiceProviderQuoteConverter.class);

	@Override
	public ServiceProviderQuote convertFrom(ServiceProviderQuoteModel model,
			Object... additionalSourceObj) throws ConvertException {
		LOGGER.debug("convert to dto start:{}", model);
		ServiceProviderQuote dto = ServiceProviderQuote.getBuilder(
				model.getProviderQuoteId(),
				model.getServiceProvider().getProviderId(),
				model.getMessageThreadId(), model.getStatus()).build();
		Date dateRequired = model.getDateRequired();
		if (dateRequired != null) {
			dto.setDateRequired(QuotesUtils.dateToStr(dateRequired));
		}
		LOGGER.debug("convert to dto end:{}", dto);
		return dto;
	}

	@Override
	public ServiceProviderQuoteModel convertTo(ServiceProviderQuote dto,
			Object... additionalSourceObj) throws ConvertException {
		LOGGER.debug("convert to model start:{}", dto);
		ServiceProviderQuoteModel model = null;

		try {
			model = ServiceProviderQuoteModel.getBuilder(
					dto.getMessageThreadId(), dto.getStatus()).build();
			if (!StringUtils.isEmpty(dto.getDateRequired())) {
				model.setDateRequired(QuotesUtils.strToDate(dto
						.getDateRequired()));
			}
		} catch (Exception e) {
			throw new ConvertException("ServiceProviderQuote mappings error", e);
		}
		LOGGER.debug("convert to model end:{}", model);
		return model;
	}

}
