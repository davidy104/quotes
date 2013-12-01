package nz.co.yellow.pure.quote.data.predicate;

import java.util.List;

import nz.co.yellow.pure.quote.data.QQuoteSystemPictureModel;

import com.mysema.query.types.Predicate;

public class QuoteSystemPicturePredicates {
	public static Predicate findByPictureRef(final String pictureRef) {
		QQuoteSystemPictureModel quoteSystemPictureModel = QQuoteSystemPictureModel.quoteSystemPictureModel;
		return quoteSystemPictureModel.pictureRef.eq(pictureRef);
	}

	public static Predicate findByCaption(final String caption) {
		QQuoteSystemPictureModel quoteSystemPictureModel = QQuoteSystemPictureModel.quoteSystemPictureModel;
		return quoteSystemPictureModel.caption.eq(caption);
	}

	public static Predicate findByQSysPicIds(final List<Long> ids) {
		QQuoteSystemPictureModel quoteSystemPictureModel = QQuoteSystemPictureModel.quoteSystemPictureModel;
		return quoteSystemPictureModel.pictureId.in(ids);
	}

	public static Predicate findByQuoteRequestId(final Long quoteRequestId) {
		QQuoteSystemPictureModel quoteSystemPictureModel = QQuoteSystemPictureModel.quoteSystemPictureModel;
		if (quoteRequestId != null) {
			return quoteSystemPictureModel.quoteRequest.quoteRequestId
					.eq(quoteRequestId);
		}
		return null;
	}
}
