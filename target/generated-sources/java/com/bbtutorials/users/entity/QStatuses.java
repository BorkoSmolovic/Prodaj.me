package com.bbtutorials.users.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QStatuses is a Querydsl query type for Statuses
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStatuses extends EntityPathBase<Statuses> {

    private static final long serialVersionUID = 328858687L;

    public static final QStatuses statuses = new QStatuses("statuses");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QStatuses(String variable) {
        super(Statuses.class, forVariable(variable));
    }

    public QStatuses(Path<? extends Statuses> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStatuses(PathMetadata metadata) {
        super(Statuses.class, metadata);
    }

}

