package nz.co.yellow.pure.quote.data.support;

import nz.co.yellow.pure.quote.data.CategoryQuestionModel;

public class CategoryQuestionModelBuilder extends
		EntityBuilder<CategoryQuestionModel> {

	@Override
	void initProduct() {
		this.product = new CategoryQuestionModel();
	}

	public CategoryQuestionModelBuilder create(String systemName,
			String wording, String dataType, String parameters,
			Integer ordinal, Long categoryId) {
		this.product = CategoryQuestionModel.getBuilder(systemName, wording,
				dataType, parameters, ordinal, categoryId).build();
		return this;
	}

	@Override
	CategoryQuestionModel assembleProduct() {
		return product;
	}

}
