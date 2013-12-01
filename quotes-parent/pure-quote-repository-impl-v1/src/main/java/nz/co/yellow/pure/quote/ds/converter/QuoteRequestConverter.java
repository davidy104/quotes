package nz.co.yellow.pure.quote.ds.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import nz.co.yellow.pure.quote.data.QuoteLoadStrategies;
import nz.co.yellow.pure.quote.data.QuoteRequestModel;
import nz.co.yellow.pure.quote.data.QuoteRequestReq;
import nz.co.yellow.pure.quote.data.QuoteRequestResp;
import nz.co.yellow.pure.quote.data.QuoteSystemPictureModel;
import nz.co.yellow.pure.quote.data.QuoteSystemPictureReq;
import nz.co.yellow.pure.quote.data.QuoteSystemPictureResp;
import nz.co.yellow.pure.quote.data.ServiceConsumerModel;
import nz.co.yellow.pure.quote.data.ServiceProviderQuote;
import nz.co.yellow.pure.quote.data.ServiceProviderQuoteModel;
import nz.co.yellow.pure.quote.ds.APIConverter;
import nz.co.yellow.pure.quote.ds.ConvertException;
import nz.co.yellow.pure.quote.util.QuotesUtils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("quoteRequestConverter")
public class QuoteRequestConverter implements
		APIConverter<QuoteRequestReq, QuoteRequestModel, QuoteRequestResp> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(QuoteRequestConverter.class);

	@Resource
	private ServiceConsumerConverter serviceConsumerConverter;

	@Resource
	private ServiceProviderQuoteConverter serviceProviderQuoteConverter;

	@Resource
	private QuoteSystemPictureConverter quoteSystemPictureConverter;

	@Override
	public QuoteRequestResp convertToResponse(QuoteRequestModel model,
			Object... additionalSourceObj) throws ConvertException {
		LOGGER.debug("convert to dto start:{}", model);
		QuoteLoadStrategies loadStrategies = QuoteLoadStrategies.NONE;
		QuoteRequestResp resp = QuoteRequestResp.getBuilder(model.getStatus(),
				model.getCategoryId(), model.getCategoryPayload(),
				model.getQuoteRequestId()).build();
		Date createdTime = model.getCreatedTime();
		if (createdTime != null) {
			resp.setDateCreated(QuotesUtils.dateToStr(createdTime));
		}

		if (additionalSourceObj != null && additionalSourceObj.length > 0) {
			loadStrategies = (QuoteLoadStrategies) additionalSourceObj[0];
		}

		if (loadStrategies == QuoteLoadStrategies.ALL) {
			this.loadSysPicture(model, resp);
			this.loadServiceConsumer(model, resp);
			this.loadProviderQuote(model, resp);
		} else if (loadStrategies == QuoteLoadStrategies.LOAD_PROVIDER_QUOTE_REQUEST) {
			this.loadProviderQuote(model, resp);
		} else if (loadStrategies == QuoteLoadStrategies.LOAD_SERVICE_CONSUMER) {
			this.loadServiceConsumer(model, resp);
		} else if (loadStrategies == QuoteLoadStrategies.LOAD_SYSTEM_PIC) {
			this.loadSysPicture(model, resp);
		}
		LOGGER.debug("convert to dto end:{}", resp);
		return resp;
	}

	private void loadProviderQuote(QuoteRequestModel model,
			QuoteRequestResp resp) throws ConvertException {
		List<ServiceProviderQuote> serviceProviderQuotes = null;
		List<ServiceProviderQuoteModel> serviceProviderQuoteModels = model
				.getProviderQuotesList();
		if (serviceProviderQuoteModels != null
				&& serviceProviderQuoteModels.size() > 0) {
			serviceProviderQuotes = new ArrayList<ServiceProviderQuote>();
			for (ServiceProviderQuoteModel serviceProviderQuoteModel : serviceProviderQuoteModels) {
				serviceProviderQuotes.add(this.serviceProviderQuoteConverter
						.convertFrom(serviceProviderQuoteModel));
			}
			resp.setServiceProviderQuotes(serviceProviderQuotes);
		}
	}

	private void loadSysPicture(QuoteRequestModel model, QuoteRequestResp resp)
			throws ConvertException {
		List<QuoteSystemPictureModel> sysPicModels = model
				.getQuoteSysPictureList();
		List<QuoteSystemPictureResp> systemPictures = null;
		if (sysPicModels != null && sysPicModels.size() > 0) {
			systemPictures = new ArrayList<QuoteSystemPictureResp>();

			for (QuoteSystemPictureModel sysPicModel : sysPicModels) {
				systemPictures.add(this.quoteSystemPictureConverter
						.convertToResponse(sysPicModel));
			}
			resp.setSystemPictures(systemPictures);
		}
	}

	private void loadServiceConsumer(QuoteRequestModel model,
			QuoteRequestResp resp) throws ConvertException {
		ServiceConsumerModel serviceConsumerModel = model.getServiceConsumer();
		if (serviceConsumerModel != null) {
			resp.setServiceConsumer(this.serviceConsumerConverter
					.convertToResponse(serviceConsumerModel));
		}
	}

	@Override
	public QuoteRequestModel convertFromRequest(QuoteRequestReq request,
			Object... additionalSourceObj) throws ConvertException {
		LOGGER.debug("convert to model start:{}", request);
		QuoteRequestModel model = null;
		List<QuoteSystemPictureModel> quoteSysPictureList = null;
		try {
			model = QuoteRequestModel.getBuilder(request.getStatus(),
					request.getCategoryId(), request.getCategoryPayload())
					.build();
			String dateCreated = request.getDateCreated();
			if (!StringUtils.isEmpty(dateCreated)) {
				model.setCreatedTime(QuotesUtils.strToDate(dateCreated));
			}

			List<QuoteSystemPictureReq> sysPictures = request
					.getSystemPictures();
			if (sysPictures != null && sysPictures.size() > 0) {
				quoteSysPictureList = new ArrayList<QuoteSystemPictureModel>();
				for (QuoteSystemPictureReq quoteSystemPictureReq : sysPictures) {
					QuoteSystemPictureModel quoteSystemPictureModel = this.quoteSystemPictureConverter
							.convertFromRequest(quoteSystemPictureReq);
					quoteSystemPictureModel.setQuoteRequest(model);
					quoteSysPictureList.add(quoteSystemPictureModel);
				}
				model.setQuoteSysPictureList(quoteSysPictureList);
			}

		} catch (Exception e) {
			throw new ConvertException("QuoteRequest mappings error", e);
		}
		LOGGER.debug("convert to model end:{}", model);
		return model;
	}
}
