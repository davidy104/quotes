package nz.co.yellow.pure.quote.data.predicate;

import java.util.List;

import nz.co.yellow.pure.quote.data.QCategoryQuestionModel;

import com.mysema.query.types.Predicate;

public class CategoryQuestionPredicates {
  public static Predicate findByDataType(final String dataType) {
    QCategoryQuestionModel categoryQuestionModel = QCategoryQuestionModel.categoryQuestionModel;
    return categoryQuestionModel.dataType.eq(dataType);
  }

  public static Predicate findBySystemName(final String systemName) {
    QCategoryQuestionModel categoryQuestionModel = QCategoryQuestionModel.categoryQuestionModel;
    return categoryQuestionModel.systemName.eq(systemName);
  }

  public static Predicate findByOrdinal(final Integer ordinal) {
    QCategoryQuestionModel categoryQuestionModel = QCategoryQuestionModel.categoryQuestionModel;
    return categoryQuestionModel.ordinal.eq(ordinal);
  }

  public static Predicate findByCategoryId(final Long categoryId) {
    QCategoryQuestionModel categoryQuestionModel = QCategoryQuestionModel.categoryQuestionModel;
    return categoryQuestionModel.categoryId.eq(categoryId);
  }

  public static Predicate findByQuestionIds(final List<Long> questionIds) {
    QCategoryQuestionModel categoryQuestionModel = QCategoryQuestionModel.categoryQuestionModel;
    return categoryQuestionModel.questionId.in(questionIds);
  }

}
