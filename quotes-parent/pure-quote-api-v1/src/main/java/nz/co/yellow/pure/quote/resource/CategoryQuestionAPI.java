package nz.co.yellow.pure.quote.resource;

import java.util.List;

import javax.ws.rs.core.Response;

import nz.co.yellow.pure.quote.data.CategoryQuestion;

public interface CategoryQuestionAPI {

	Response createCategoryQuestion(CategoryQuestion categoryQuestion);

	Response getCategoryQuestionById(Long categoryQuestionId);

	Response getCategoryQuestionByCategoryId(Long categoryId);

	Response updateCategoryQuestion(Long id, CategoryQuestion categoryQuestion);

	Response getCategoryQuestionByIds(List<Long> ids);
}
