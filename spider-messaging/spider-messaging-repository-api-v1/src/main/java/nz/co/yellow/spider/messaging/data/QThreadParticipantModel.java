package nz.co.yellow.spider.messaging.data;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;


/**
 * QThreadParticipantModel is a Querydsl query type for ThreadParticipantModel
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QThreadParticipantModel extends EntityPathBase<ThreadParticipantModel> {

    private static final long serialVersionUID = -1492205141;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QThreadParticipantModel threadParticipantModel = new QThreadParticipantModel("threadParticipantModel");

    public final NumberPath<Long> threadParticipantId = createNumber("threadParticipantId", Long.class);

    public final QThreadParticipantPK threadParticipantPk;

    public QThreadParticipantModel(String variable) {
        this(ThreadParticipantModel.class, forVariable(variable), INITS);
    }

    public QThreadParticipantModel(Path<? extends ThreadParticipantModel> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QThreadParticipantModel(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QThreadParticipantModel(PathMetadata<?> metadata, PathInits inits) {
        this(ThreadParticipantModel.class, metadata, inits);
    }

    public QThreadParticipantModel(Class<? extends ThreadParticipantModel> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.threadParticipantPk = inits.isInitialized("threadParticipantPk") ? new QThreadParticipantPK(forProperty("threadParticipantPk"), inits.get("threadParticipantPk")) : null;
    }

}

