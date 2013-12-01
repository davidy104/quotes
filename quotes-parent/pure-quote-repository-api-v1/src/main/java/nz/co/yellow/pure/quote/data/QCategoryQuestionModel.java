package nz.co.yellow.pure.quote.data;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.*;
import com.mysema.query.types.path.*;

import javax.annotation.Generated;


/**
 * QCategoryQuestionModel is a Querydsl query type for CategoryQuestionModel
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QCategoryQuestionModel extends EntityPathBase<CategoryQuestionModel> {

    private static final long serialVersionUID = -1408524753;

    public static final QCategoryQuestionModel categoryQuestionModel = new QCategoryQuestionModel("categoryQuestionModel");

    public final NumberPath<Long> categoryId = createNumber("categoryId", Long.class);

    public final StringPath dataType = createString("dataType");

    public final NumberPath<Integer> ordinal = createNumber("ordinal", Integer.class);

    public final StringPath parameters = createString("parameters");

    public final NumberPath<Long> questionId = createNumber("questionId", Long.class);

    public final StringPath systemName = createString("systemName");

    public final StringPath wording = createString("wording");

    public QCategoryQuestionModel(String variable) {
        super(CategoryQuestionModel.class, forVariable(variable));
    }

    public QCategoryQuestionModel(Path<? extends CategoryQuestionModel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCategoryQuestionModel(PathMetadata<?> metadata) {
        super(CategoryQuestionModel.class, metadata);
    }

}

