package nz.co.yellow.pure.quote.data;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;


/**
 * QServiceProviderQuoteModel is a Querydsl query type for ServiceProviderQuoteModel
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QServiceProviderQuoteModel extends EntityPathBase<ServiceProviderQuoteModel> {

    private static final long serialVersionUID = 102755613;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QServiceProviderQuoteModel serviceProviderQuoteModel = new QServiceProviderQuoteModel("serviceProviderQuoteModel");

    public final DateTimePath<java.util.Date> dateRequired = createDateTime("dateRequired", java.util.Date.class);

    public final NumberPath<Long> messageThreadId = createNumber("messageThreadId", Long.class);

    public final NumberPath<Long> providerQuoteId = createNumber("providerQuoteId", Long.class);

    public final QQuoteRequestModel quoteRequest;

    public final QServiceProviderModel serviceProvider;

    public final StringPath status = createString("status");

    public QServiceProviderQuoteModel(String variable) {
        this(ServiceProviderQuoteModel.class, forVariable(variable), INITS);
    }

    public QServiceProviderQuoteModel(Path<? extends ServiceProviderQuoteModel> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QServiceProviderQuoteModel(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QServiceProviderQuoteModel(PathMetadata<?> metadata, PathInits inits) {
        this(ServiceProviderQuoteModel.class, metadata, inits);
    }

    public QServiceProviderQuoteModel(Class<? extends ServiceProviderQuoteModel> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.quoteRequest = inits.isInitialized("quoteRequest") ? new QQuoteRequestModel(forProperty("quoteRequest"), inits.get("quoteRequest")) : null;
        this.serviceProvider = inits.isInitialized("serviceProvider") ? new QServiceProviderModel(forProperty("serviceProvider")) : null;
    }

}

