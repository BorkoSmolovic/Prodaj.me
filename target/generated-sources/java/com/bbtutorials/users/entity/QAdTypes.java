package com.bbtutorials.users.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAdTypes is a Querydsl query type for AdTypes
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAdTypes extends EntityPathBase<AdTypes> {

    private static final long serialVersionUID = -362895849L;

    public static final QAdTypes adTypes = new QAdTypes("adTypes");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QAdTypes(String variable) {
        super(AdTypes.class, forVariable(variable));
    }

    public QAdTypes(Path<? extends AdTypes> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAdTypes(PathMetadata metadata) {
        super(AdTypes.class, metadata);
    }

}

