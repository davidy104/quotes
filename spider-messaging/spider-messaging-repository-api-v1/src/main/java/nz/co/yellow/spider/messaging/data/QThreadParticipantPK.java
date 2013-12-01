package nz.co.yellow.spider.messaging.data;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;


/**
 * QThreadParticipantPK is a Querydsl query type for ThreadParticipantPK
 */
@Generated("com.mysema.query.codegen.EmbeddableSerializer")
public class QThreadParticipantPK extends BeanPath<ThreadParticipantPK> {

    private static final long serialVersionUID = 1007986329;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QThreadParticipantPK threadParticipantPK = new QThreadParticipantPK("threadParticipantPK");

    public final QParticipantModel participant;

    public final QThreadModel thread;

    public QThreadParticipantPK(String variable) {
        this(ThreadParticipantPK.class, forVariable(variable), INITS);
    }

    public QThreadParticipantPK(Path<? extends ThreadParticipantPK> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QThreadParticipantPK(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QThreadParticipantPK(PathMetadata<?> metadata, PathInits inits) {
        this(ThreadParticipantPK.class, metadata, inits);
    }

    public QThreadParticipantPK(Class<? extends ThreadParticipantPK> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.participant = inits.isInitialized("participant") ? new QParticipantModel(forProperty("participant")) : null;
        this.thread = inits.isInitialized("thread") ? new QThreadModel(forProperty("thread")) : null;
    }

}

