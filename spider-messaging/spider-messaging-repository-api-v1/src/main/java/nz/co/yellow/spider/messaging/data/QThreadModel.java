package nz.co.yellow.spider.messaging.data;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;


/**
 * QThreadModel is a Querydsl query type for ThreadModel
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QThreadModel extends EntityPathBase<ThreadModel> {

    private static final long serialVersionUID = -320601004;

    public static final QThreadModel threadModel = new QThreadModel("threadModel");

    public final DateTimePath<java.util.Date> createdTime = createDateTime("createdTime", java.util.Date.class);

    public final ListPath<MessageModel, QMessageModel> messages = this.<MessageModel, QMessageModel>createList("messages", MessageModel.class, QMessageModel.class, PathInits.DIRECT);

    public final StringPath status = createString("status");

    public final ListPath<ThreadParticipantModel, QThreadParticipantModel> theadParticipants = this.<ThreadParticipantModel, QThreadParticipantModel>createList("theadParticipants", ThreadParticipantModel.class, QThreadParticipantModel.class, PathInits.DIRECT);

    public final NumberPath<Long> threadId = createNumber("threadId", Long.class);

    public final StringPath title = createString("title");

    public QThreadModel(String variable) {
        super(ThreadModel.class, forVariable(variable));
    }

    public QThreadModel(Path<? extends ThreadModel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QThreadModel(PathMetadata<?> metadata) {
        super(ThreadModel.class, metadata);
    }

}

