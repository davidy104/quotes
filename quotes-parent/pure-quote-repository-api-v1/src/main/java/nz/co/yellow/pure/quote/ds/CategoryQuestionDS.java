package nz.co.yellow.pure.quote.ds;

import java.util.List;

import nz.co.yellow.pure.quote.data.CategoryQuestion;

public interface CategoryQuestionDS {

  CategoryQuestion createCategoryQuestion(CategoryQuestion categoryQuestion)
      throws Exception;

  CategoryQuestion updateCategoryQuestion(Long categoryQuestionId, CategoryQuestion categoryQuestion)
      throws Exception;

  CategoryQuestion getCategoryQuestionById(Long categoryQuestionId)
      throws Exception;

  List<CategoryQuestion> getCategoryQuestionsByCateId(Long categoryId)
      throws Exception;

  List<CategoryQuestion> getCategoryQuestionsByIds(List<Long> cateQuestionIds)
      throws Exception;
}
