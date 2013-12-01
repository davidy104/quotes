package nz.co.yellow.pure.quote.data.predicate;

import nz.co.yellow.pure.quote.data.QQuoteRequestModel;

import com.mysema.query.types.Predicate;

public class QuoteRequestPredicates {


	public static Predicate findByCategoryId(final Long categoryId) {
		QQuoteRequestModel quoteRequestModel = QQuoteRequestModel.quoteRequestModel;
		return quoteRequestModel.categoryId.eq(categoryId);
	}


}
