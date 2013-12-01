package nz.co.yellow.pure.quote.data;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;


/**
 * QQuoteSystemPictureModel is a Querydsl query type for QuoteSystemPictureModel
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QQuoteSystemPictureModel extends EntityPathBase<QuoteSystemPictureModel> {

    private static final long serialVersionUID = 1309603168;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QQuoteSystemPictureModel quoteSystemPictureModel = new QQuoteSystemPictureModel("quoteSystemPictureModel");

    public final StringPath caption = createString("caption");

    public final NumberPath<Long> pictureId = createNumber("pictureId", Long.class);

    public final StringPath pictureRef = createString("pictureRef");

    public final QQuoteRequestModel quoteRequest;

    public QQuoteSystemPictureModel(String variable) {
        this(QuoteSystemPictureModel.class, forVariable(variable), INITS);
    }

    public QQuoteSystemPictureModel(Path<? extends QuoteSystemPictureModel> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QQuoteSystemPictureModel(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QQuoteSystemPictureModel(PathMetadata<?> metadata, PathInits inits) {
        this(QuoteSystemPictureModel.class, metadata, inits);
    }

    public QQuoteSystemPictureModel(Class<? extends QuoteSystemPictureModel> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.quoteRequest = inits.isInitialized("quoteRequest") ? new QQuoteRequestModel(forProperty("quoteRequest"), inits.get("quoteRequest")) : null;
    }

}

