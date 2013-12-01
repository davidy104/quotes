package nz.co.yellow.pure.quote.data.support;

import java.util.Date;

import nz.co.yellow.pure.quote.data.QuoteRequestModel;
import nz.co.yellow.pure.quote.data.QuoteSystemPictureModel;
import nz.co.yellow.pure.quote.data.ServiceConsumerModel;

public class QuoteRequestModelBuilder extends EntityBuilder<QuoteRequestModel> {

	@Override
	void initProduct() {
		this.product = new QuoteRequestModel();
	}

	public QuoteRequestModelBuilder create(Date createdTime, String status,
			Long categoryId, String categoryPayload,
			ServiceConsumerModel serviceConsumer) {
		this.product = QuoteRequestModel.getBuilder(createdTime, status,
				categoryId, categoryPayload, serviceConsumer).build();
		return this;
	}

//	public QuoteRequestModelBuilder addServiceProviderQuoteModels(
//			ServiceProviderQuoteModel... serviceProviderQuoteModels) {
//		for (ServiceProviderQuoteModel serviceProviderQuoteModel : serviceProviderQuoteModels) {
//			serviceProviderQuoteModel.setQuoteRequest(product);
//			this.product
//					.addServiceProviderQuoteModel(serviceProviderQuoteModel);
//		}
//		return this;
//	}

	public QuoteRequestModelBuilder addQuoteSystemPictureModels(
			QuoteSystemPictureModel... quoteSystemPictureModels) {
		for (QuoteSystemPictureModel quoteSystemPictureModel : quoteSystemPictureModels) {
			quoteSystemPictureModel.setQuoteRequest(product);
			this.product.addQuoteSystemPictureModel(quoteSystemPictureModel);
		}
		return this;
	}

	public @Override
	QuoteRequestModel assembleProduct() {
		return product;
	}

}
