package com.oncolator.app.entity.dataEntity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QResults is a Querydsl query type for Results
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QResults extends EntityPathBase<Results> {

    private static final long serialVersionUID = -734428561L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QResults results = new QResults("results");

    public final StringPath checkList = createString("checkList");

    public final NumberPath<Long> diagId = createNumber("diagId", Long.class);

    public final QDiagnosis diagnosis;

    public final NumberPath<Float> dosage = createNumber("dosage", Float.class);

    public final StringPath doses = createString("doses");

    public final QDrugs drug;

    public final NumberPath<Long> drugId = createNumber("drugId", Long.class);

    public final NumberPath<Long> duration = createNumber("duration", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPhases phase;

    public final NumberPath<Long> phaseId = createNumber("phaseId", Long.class);

    public final DatePath<java.sql.Date> startDate = createDate("startDate", java.sql.Date.class);

    public QResults(String variable) {
        this(Results.class, forVariable(variable), INITS);
    }

    public QResults(Path<? extends Results> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QResults(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QResults(PathMetadata metadata, PathInits inits) {
        this(Results.class, metadata, inits);
    }

    public QResults(Class<? extends Results> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.diagnosis = inits.isInitialized("diagnosis") ? new QDiagnosis(forProperty("diagnosis"), inits.get("diagnosis")) : null;
        this.drug = inits.isInitialized("drug") ? new QDrugs(forProperty("drug")) : null;
        this.phase = inits.isInitialized("phase") ? new QPhases(forProperty("phase")) : null;
    }

}

