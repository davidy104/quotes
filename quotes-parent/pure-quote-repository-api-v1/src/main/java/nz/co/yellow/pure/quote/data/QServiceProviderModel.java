package nz.co.yellow.pure.quote.data;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;


/**
 * QServiceProviderModel is a Querydsl query type for ServiceProviderModel
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QServiceProviderModel extends EntityPathBase<ServiceProviderModel> {

    private static final long serialVersionUID = -1209147879;

    public static final QServiceProviderModel serviceProviderModel = new QServiceProviderModel("serviceProviderModel");

    public final NumberPath<java.math.BigDecimal> boostWeight = createNumber("boostWeight", java.math.BigDecimal.class);

    public final DateTimePath<java.util.Date> createdTime = createDateTime("createdTime", java.util.Date.class);

    public final StringPath customerId = createString("customerId");

    public final NumberPath<Long> providerId = createNumber("providerId", Long.class);

    public final ListPath<ServiceProviderQuoteModel, QServiceProviderQuoteModel> providerQuotesList = this.<ServiceProviderQuoteModel, QServiceProviderQuoteModel>createList("providerQuotesList", ServiceProviderQuoteModel.class, QServiceProviderQuoteModel.class, PathInits.DIRECT);

    public final StringPath userId = createString("userId");

    public QServiceProviderModel(String variable) {
        super(ServiceProviderModel.class, forVariable(variable));
    }

    public QServiceProviderModel(Path<? extends ServiceProviderModel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QServiceProviderModel(PathMetadata<?> metadata) {
        super(ServiceProviderModel.class, metadata);
    }

}

