package nz.co.yellow.pure.quote.data.support;

import java.util.Date;

import nz.co.yellow.pure.quote.data.QuoteRequestModel;
import nz.co.yellow.pure.quote.data.ServiceProviderModel;
import nz.co.yellow.pure.quote.data.ServiceProviderQuoteModel;

public class ServiceProviderQuoteRequestModelBuilder extends
		EntityBuilder<ServiceProviderQuoteModel> {

	@Override
	void initProduct() {
		this.product = new ServiceProviderQuoteModel();
	}

	public ServiceProviderQuoteRequestModelBuilder create(
			ServiceProviderModel serviceProvider,
			QuoteRequestModel quotesRequest, Long messageThreadId,
			String status, Date dateRequired) {
		this.product = ServiceProviderQuoteModel.getBuilder(serviceProvider,
				quotesRequest, messageThreadId, status, dateRequired).build();
		return this;
	}

	@Override
	ServiceProviderQuoteModel assembleProduct() {
		return product;
	}

}
