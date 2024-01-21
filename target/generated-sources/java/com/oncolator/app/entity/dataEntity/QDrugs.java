package com.oncolator.app.entity.dataEntity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDrugs is a Querydsl query type for Drugs
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDrugs extends EntityPathBase<Drugs> {

    private static final long serialVersionUID = -165259892L;

    public static final QDrugs drugs = new QDrugs("drugs");

    public final StringPath description = createString("description");

    public final StringPath drugName = createString("drugName");

    public final StringPath footNote = createString("footNote");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Float> maxDosage = createNumber("maxDosage", Float.class);

    public final NumberPath<Float> rounded = createNumber("rounded", Float.class);

    public final StringPath route = createString("route");

    public final StringPath unit = createString("unit");

    public QDrugs(String variable) {
        super(Drugs.class, forVariable(variable));
    }

    public QDrugs(Path<? extends Drugs> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDrugs(PathMetadata metadata) {
        super(Drugs.class, metadata);
    }

}

