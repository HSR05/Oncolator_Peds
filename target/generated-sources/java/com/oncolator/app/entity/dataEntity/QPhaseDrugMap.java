package com.oncolator.app.entity.dataEntity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPhaseDrugMap is a Querydsl query type for PhaseDrugMap
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPhaseDrugMap extends EntityPathBase<PhaseDrugMap> {

    private static final long serialVersionUID = 386020552L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPhaseDrugMap phaseDrugMap = new QPhaseDrugMap("phaseDrugMap");

    public final NumberPath<Float> dose = createNumber("dose", Float.class);

    public final StringPath doses = createString("doses");

    public final QDrugs drug;

    public final NumberPath<Long> drugId = createNumber("drugId", Long.class);

    public final NumberPath<Long> duration = createNumber("duration", Long.class);

    public final StringPath frequency = createString("frequency");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath notesOverride = createString("notesOverride");

    public final QPhases phase;

    public final NumberPath<Long> phaseId = createNumber("phaseId", Long.class);

    public QPhaseDrugMap(String variable) {
        this(PhaseDrugMap.class, forVariable(variable), INITS);
    }

    public QPhaseDrugMap(Path<? extends PhaseDrugMap> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPhaseDrugMap(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPhaseDrugMap(PathMetadata metadata, PathInits inits) {
        this(PhaseDrugMap.class, metadata, inits);
    }

    public QPhaseDrugMap(Class<? extends PhaseDrugMap> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.drug = inits.isInitialized("drug") ? new QDrugs(forProperty("drug")) : null;
        this.phase = inits.isInitialized("phase") ? new QPhases(forProperty("phase")) : null;
    }

}

