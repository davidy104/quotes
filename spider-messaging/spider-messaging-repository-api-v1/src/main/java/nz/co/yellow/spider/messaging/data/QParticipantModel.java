package nz.co.yellow.spider.messaging.data;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;


/**
 * QParticipantModel is a Querydsl query type for ParticipantModel
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QParticipantModel extends EntityPathBase<ParticipantModel> {

    private static final long serialVersionUID = -1908590655;

    public static final QParticipantModel participantModel = new QParticipantModel("participantModel");

    public final ListPath<MessageModel, QMessageModel> messages = this.<MessageModel, QMessageModel>createList("messages", MessageModel.class, QMessageModel.class, PathInits.DIRECT);

    public final NumberPath<Long> participantId = createNumber("participantId", Long.class);

    public final ListPath<ThreadParticipantModel, QThreadParticipantModel> theadParticipants = this.<ThreadParticipantModel, QThreadParticipantModel>createList("theadParticipants", ThreadParticipantModel.class, QThreadParticipantModel.class, PathInits.DIRECT);

    public final StringPath userId = createString("userId");

    public QParticipantModel(String variable) {
        super(ParticipantModel.class, forVariable(variable));
    }

    public QParticipantModel(Path<? extends ParticipantModel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QParticipantModel(PathMetadata<?> metadata) {
        super(ParticipantModel.class, metadata);
    }

}

