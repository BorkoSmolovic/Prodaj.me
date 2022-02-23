package com.bbtutorials.users.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTypes is a Querydsl query type for Types
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTypes extends EntityPathBase<Types> {

    private static final long serialVersionUID = -1041395526L;

    public static final QTypes types = new QTypes("types");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QTypes(String variable) {
        super(Types.class, forVariable(variable));
    }

    public QTypes(Path<? extends Types> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTypes(PathMetadata metadata) {
        super(Types.class, metadata);
    }

}

