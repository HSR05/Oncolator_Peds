package com.oncolator.app.entity.dataEntity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPhases is a Querydsl query type for Phases
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPhases extends EntityPathBase<Phases> {

    private static final long serialVersionUID = -494359361L;

    public static final QPhases phases = new QPhases("phases");

    public final NumberPath<Integer> daysOfTherapy = createNumber("daysOfTherapy", Integer.class);

    public final NumberPath<Integer> endWeek = createNumber("endWeek", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Integer> intrathecalInjections = createNumber("intrathecalInjections", Integer.class);

    public final StringPath phaseCode = createString("phaseCode");

    public final StringPath phaseDescription = createString("phaseDescription");

    public final StringPath phaseName = createString("phaseName");

    public final StringPath possibleValues = createString("possibleValues");

    public final StringPath randomisation = createString("randomisation");

    public final StringPath risk = createString("risk");

    public final NumberPath<Integer> startWeek = createNumber("startWeek", Integer.class);

    public QPhases(String variable) {
        super(Phases.class, forVariable(variable));
    }

    public QPhases(Path<? extends Phases> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPhases(PathMetadata metadata) {
        super(Phases.class, metadata);
    }

}

