package nz.co.yellow.pure.quote.data;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;


/**
 * QServiceConsumerModel is a Querydsl query type for ServiceConsumerModel
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QServiceConsumerModel extends EntityPathBase<ServiceConsumerModel> {

    private static final long serialVersionUID = -1399825612;

    public static final QServiceConsumerModel serviceConsumerModel = new QServiceConsumerModel("serviceConsumerModel");

    public final NumberPath<Long> consumerId = createNumber("consumerId", Long.class);

    public final ListPath<QuoteRequestModel, QQuoteRequestModel> quoteRequestList = this.<QuoteRequestModel, QQuoteRequestModel>createList("quoteRequestList", QuoteRequestModel.class, QQuoteRequestModel.class, PathInits.DIRECT);

    public final StringPath userId = createString("userId");

    public QServiceConsumerModel(String variable) {
        super(ServiceConsumerModel.class, forVariable(variable));
    }

    public QServiceConsumerModel(Path<? extends ServiceConsumerModel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QServiceConsumerModel(PathMetadata<?> metadata) {
        super(ServiceConsumerModel.class, metadata);
    }

}

