package nz.co.yellow.pure.quote.ds.converter;

import nz.co.yellow.pure.quote.data.QuoteLoadStrategies;
import nz.co.yellow.pure.quote.data.QuoteRequestModel;
import nz.co.yellow.pure.quote.data.QuoteSystemPictureModel;
import nz.co.yellow.pure.quote.data.QuoteSystemPictureReq;
import nz.co.yellow.pure.quote.data.QuoteSystemPictureResp;
import nz.co.yellow.pure.quote.ds.APIConverter;
import nz.co.yellow.pure.quote.ds.ConvertException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("quoteSystemPictureConverter")
public class QuoteSystemPictureConverter
		implements
		APIConverter<QuoteSystemPictureReq, QuoteSystemPictureModel, QuoteSystemPictureResp> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(QuoteSystemPictureConverter.class);

	@Override
	public QuoteSystemPictureResp convertToResponse(
			QuoteSystemPictureModel model, Object... additionalSourceObj)
			throws ConvertException {
		LOGGER.debug("convert to dto start:{}", model);
		QuoteSystemPictureResp dto = QuoteSystemPictureResp
				.getBuilder(model.getPictureId(), model.getPictureRef(),
						model.getCaption()).build();
		if (model.getQuoteRequest() != null) {
			dto.setQuoteRequestId(model.getQuoteRequest().getQuoteRequestId());
		}

		QuoteLoadStrategies loadStrategies = QuoteLoadStrategies.NONE;

		if (additionalSourceObj != null && additionalSourceObj.length > 0) {
			loadStrategies = (QuoteLoadStrategies) additionalSourceObj[0];
		}
		LOGGER.debug("loadStrategies:{}", loadStrategies);
		if (loadStrategies == QuoteLoadStrategies.ALL
				|| loadStrategies == QuoteLoadStrategies.LOAD_QUOTE_REQUEST) {
			QuoteRequestModel qrm = model.getQuoteRequest();
			if (qrm != null) {
				dto.setQuoteRequestId(qrm.getQuoteRequestId());
			}
		}
		LOGGER.debug("convert to dto end:{}", dto);
		return dto;
	}

	@Override
	public QuoteSystemPictureModel convertFromRequest(
			QuoteSystemPictureReq request, Object... additionalSourceObj)
			throws ConvertException {
		LOGGER.debug("convert to model start:{}", request);
		QuoteSystemPictureModel model = QuoteSystemPictureModel.getBuilder(
				request.getPictureRef(), request.getCaption()).build();

		LOGGER.debug("convert to model end:{}", model);
		return model;
	}

}
