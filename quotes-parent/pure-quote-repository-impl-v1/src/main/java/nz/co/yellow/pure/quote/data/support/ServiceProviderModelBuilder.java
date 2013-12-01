package nz.co.yellow.pure.quote.data.support;

import java.math.BigDecimal;
import java.util.Date;

import nz.co.yellow.pure.quote.data.ServiceProviderModel;

public class ServiceProviderModelBuilder extends
		EntityBuilder<ServiceProviderModel> {

	@Override
	void initProduct() {
		this.product = new ServiceProviderModel();
	}

	public ServiceProviderModelBuilder create(String userId, String customerId,
			Date createdTime, BigDecimal boostWeight) {
		this.product = ServiceProviderModel.getBuilder(userId, customerId,
				createdTime, boostWeight).build();
		return this;
	}

	@Override
	ServiceProviderModel assembleProduct() {
		return product;
	}

}
