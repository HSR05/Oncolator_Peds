package com.oncolator.app.entity.dataEntity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPatient is a Querydsl query type for Patient
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPatient extends EntityPathBase<Patient> {

    private static final long serialVersionUID = 1671573886L;

    public static final QPatient patient = new QPatient("patient");

    public final NumberPath<Float> BMI = createNumber("BMI", Float.class);

    public final NumberPath<Float> BSA = createNumber("BSA", Float.class);

    public final DatePath<java.sql.Date> DOB = createDate("DOB", java.sql.Date.class);

    public final NumberPath<Float> height = createNumber("height", Float.class);

    public final NumberPath<Long> oncId = createNumber("oncId", Long.class);

    public final ComparablePath<Character> sex = createComparable("sex", Character.class);

    public final NumberPath<Float> weight = createNumber("weight", Float.class);

    public QPatient(String variable) {
        super(Patient.class, forVariable(variable));
    }

    public QPatient(Path<? extends Patient> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPatient(PathMetadata metadata) {
        super(Patient.class, metadata);
    }

}

