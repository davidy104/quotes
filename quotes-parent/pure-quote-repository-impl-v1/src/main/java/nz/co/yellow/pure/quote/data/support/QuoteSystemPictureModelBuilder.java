package nz.co.yellow.pure.quote.data.support;

import nz.co.yellow.pure.quote.data.QuoteSystemPictureModel;

public class QuoteSystemPictureModelBuilder extends
		EntityBuilder<QuoteSystemPictureModel> {

	@Override
	void initProduct() {
		this.product = new QuoteSystemPictureModel();
	}

	public QuoteSystemPictureModelBuilder create(String pictureRef,
			String caption) {
		this.product = QuoteSystemPictureModel.getBuilder(pictureRef, caption).build();
		return this;
	}

	@Override
	QuoteSystemPictureModel assembleProduct() {
		return product;
	}

}
