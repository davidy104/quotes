package nz.co.yellow.spider.messaging.data;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;


/**
 * QMessageModel is a Querydsl query type for MessageModel
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMessageModel extends EntityPathBase<MessageModel> {

    private static final long serialVersionUID = -1863890707;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QMessageModel messageModel = new QMessageModel("messageModel");

    public final StringPath content = createString("content");

    public final QParticipantModel createdBy;

    public final NumberPath<Long> messageId = createNumber("messageId", Long.class);

    public final DateTimePath<java.util.Date> readTime = createDateTime("readTime", java.util.Date.class);

    public final DateTimePath<java.util.Date> sentTime = createDateTime("sentTime", java.util.Date.class);

    public final StringPath status = createString("status");

    public final QThreadModel thread;

    public QMessageModel(String variable) {
        this(MessageModel.class, forVariable(variable), INITS);
    }

    public QMessageModel(Path<? extends MessageModel> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMessageModel(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMessageModel(PathMetadata<?> metadata, PathInits inits) {
        this(MessageModel.class, metadata, inits);
    }

    public QMessageModel(Class<? extends MessageModel> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.createdBy = inits.isInitialized("createdBy") ? new QParticipantModel(forProperty("createdBy")) : null;
        this.thread = inits.isInitialized("thread") ? new QThreadModel(forProperty("thread")) : null;
    }

}

