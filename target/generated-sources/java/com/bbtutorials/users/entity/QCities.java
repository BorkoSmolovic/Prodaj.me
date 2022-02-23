package com.bbtutorials.users.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCities is a Querydsl query type for Cities
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCities extends EntityPathBase<Cities> {

    private static final long serialVersionUID = 1575127848L;

    public static final QCities cities = new QCities("cities");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QCities(String variable) {
        super(Cities.class, forVariable(variable));
    }

    public QCities(Path<? extends Cities> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCities(PathMetadata metadata) {
        super(Cities.class, metadata);
    }

}

