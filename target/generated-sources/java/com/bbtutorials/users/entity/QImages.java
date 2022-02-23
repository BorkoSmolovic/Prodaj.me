package com.bbtutorials.users.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QImages is a Querydsl query type for Images
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QImages extends EntityPathBase<Images> {

    private static final long serialVersionUID = 1750028887L;

    public static final QImages images = new QImages("images");

    public final NumberPath<Long> adId = createNumber("adId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath image = createString("image");

    public QImages(String variable) {
        super(Images.class, forVariable(variable));
    }

    public QImages(Path<? extends Images> path) {
        super(path.getType(), path.getMetadata());
    }

    public QImages(PathMetadata metadata) {
        super(Images.class, metadata);
    }

}

