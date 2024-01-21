package com.oncolator.app.entity.dataEntity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDiagnosisTypeMap is a Querydsl query type for DiagnosisTypeMap
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDiagnosisTypeMap extends EntityPathBase<DiagnosisTypeMap> {

    private static final long serialVersionUID = 157645112L;

    public static final QDiagnosisTypeMap diagnosisTypeMap = new QDiagnosisTypeMap("diagnosisTypeMap");

    public final StringPath diagnosis = createString("diagnosis");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath regimen = createString("regimen");

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public QDiagnosisTypeMap(String variable) {
        super(DiagnosisTypeMap.class, forVariable(variable));
    }

    public QDiagnosisTypeMap(Path<? extends DiagnosisTypeMap> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDiagnosisTypeMap(PathMetadata metadata) {
        super(DiagnosisTypeMap.class, metadata);
    }

}

