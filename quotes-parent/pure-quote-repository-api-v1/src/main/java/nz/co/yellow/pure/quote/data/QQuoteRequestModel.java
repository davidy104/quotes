package nz.co.yellow.pure.quote.data;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;


/**
 * QQuoteRequestModel is a Querydsl query type for QuoteRequestModel
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QQuoteRequestModel extends EntityPathBase<QuoteRequestModel> {

    private static final long serialVersionUID = -1282750688;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QQuoteRequestModel quoteRequestModel = new QQuoteRequestModel("quoteRequestModel");

    public final NumberPath<Long> categoryId = createNumber("categoryId", Long.class);

    public final StringPath categoryPayload = createString("categoryPayload");

    public final DateTimePath<java.util.Date> createdTime = createDateTime("createdTime", java.util.Date.class);

    public final ListPath<ServiceProviderQuoteModel, QServiceProviderQuoteModel> providerQuotesList = this.<ServiceProviderQuoteModel, QServiceProviderQuoteModel>createList("providerQuotesList", ServiceProviderQuoteModel.class, QServiceProviderQuoteModel.class, PathInits.DIRECT);

    public final NumberPath<Long> quoteRequestId = createNumber("quoteRequestId", Long.class);

    public final ListPath<QuoteSystemPictureModel, QQuoteSystemPictureModel> quoteSysPictureList = this.<QuoteSystemPictureModel, QQuoteSystemPictureModel>createList("quoteSysPictureList", QuoteSystemPictureModel.class, QQuoteSystemPictureModel.class, PathInits.DIRECT);

    public final QServiceConsumerModel serviceConsumer;

    public final StringPath status = createString("status");

    public QQuoteRequestModel(String variable) {
        this(QuoteRequestModel.class, forVariable(variable), INITS);
    }

    public QQuoteRequestModel(Path<? extends QuoteRequestModel> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QQuoteRequestModel(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QQuoteRequestModel(PathMetadata<?> metadata, PathInits inits) {
        this(QuoteRequestModel.class, metadata, inits);
    }

    public QQuoteRequestModel(Class<? extends QuoteRequestModel> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.serviceConsumer = inits.isInitialized("serviceConsumer") ? new QServiceConsumerModel(forProperty("serviceConsumer")) : null;
    }

}

