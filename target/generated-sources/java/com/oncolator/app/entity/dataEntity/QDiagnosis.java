package com.oncolator.app.entity.dataEntity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDiagnosis is a Querydsl query type for Diagnosis
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDiagnosis extends EntityPathBase<Diagnosis> {

    private static final long serialVersionUID = 1605318442L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDiagnosis diagnosis1 = new QDiagnosis("diagnosis1");

    public final StringPath diagnosis = createString("diagnosis");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final BooleanPath isActive = createBoolean("isActive");

    public final NumberPath<Long> oncId = createNumber("oncId", Long.class);

    public final StringPath parameters = createString("parameters");

    public final EnumPath<com.oncolator.app.entity.enums.ParameterTypeEnum> parameterType = createEnum("parameterType", com.oncolator.app.entity.enums.ParameterTypeEnum.class);

    public final QPatient patient;

    public final QPhases phase;

    public final NumberPath<Long> phaseId = createNumber("phaseId", Long.class);

    public final StringPath regimen = createString("regimen");

    public QDiagnosis(String variable) {
        this(Diagnosis.class, forVariable(variable), INITS);
    }

    public QDiagnosis(Path<? extends Diagnosis> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDiagnosis(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDiagnosis(PathMetadata metadata, PathInits inits) {
        this(Diagnosis.class, metadata, inits);
    }

    public QDiagnosis(Class<? extends Diagnosis> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.patient = inits.isInitialized("patient") ? new QPatient(forProperty("patient")) : null;
        this.phase = inits.isInitialized("phase") ? new QPhases(forProperty("phase")) : null;
    }

}

