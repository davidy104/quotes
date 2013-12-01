package nz.co.yellow.pure.quote.data.support;

import nz.co.yellow.pure.quote.data.ServiceConsumerModel;

public class ServiceConsumerModelBuilder extends
		EntityBuilder<ServiceConsumerModel> {

	@Override
	void initProduct() {
		this.product = new ServiceConsumerModel();
	}

	public ServiceConsumerModelBuilder create(String userId) {
		this.product = ServiceConsumerModel.getBuilder(userId)
				.build();
		return this;
	}

	@Override
	ServiceConsumerModel assembleProduct() {
		return product;
	}

}
