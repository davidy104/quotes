package nz.co.yellow.pure.quote.ds.converter;

import nz.co.yellow.pure.quote.data.CategoryQuestion;
import nz.co.yellow.pure.quote.data.CategoryQuestionModel;
import nz.co.yellow.pure.quote.ds.ConvertException;
import nz.co.yellow.pure.quote.ds.GeneralConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("categoryQuestionConverter")
public class CategoryQuestionConverter implements
		GeneralConverter<CategoryQuestion, CategoryQuestionModel> {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CategoryQuestionConverter.class);

	@Override
	public CategoryQuestion convertFrom(CategoryQuestionModel model,
			Object... additionalSourceObj) throws ConvertException {
		LOGGER.debug("converter to dto start:{}", model);
		CategoryQuestion dto = CategoryQuestion.getBuilder(
				model.getSystemName(), model.getWording(), model.getDataType(),
				model.getParameters(), model.getOrdinal(),
				model.getCategoryId()).build();
		dto.setId(model.getQuestionId());

		LOGGER.debug("converter to dto end:{}", dto);
		return dto;
	}

	@Override
	public CategoryQuestionModel convertTo(CategoryQuestion dto,
			Object... additionalSourceObj) throws ConvertException {
		LOGGER.debug("converter to model start:{}", dto);
		CategoryQuestionModel model = CategoryQuestionModel.getBuilder(
				dto.getSystemName(), dto.getWording(), dto.getDataType(),
				dto.getParameters(), dto.getOrdinal(), dto.getCategory())
				.build();

		LOGGER.debug("converter to model end:{}", model);
		return model;
	}

}
