package com.bbtutorials.users.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAds is a Querydsl query type for Ads
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAds extends EntityPathBase<Ads> {

    private static final long serialVersionUID = 995544369L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAds ads = new QAds("ads");

    public final NumberPath<Long> categoryId = createNumber("categoryId", Long.class);

    public final NumberPath<Long> cityId = createNumber("cityId", Long.class);

    public final StringPath description = createString("description");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final ListPath<Images, QImages> images = this.<Images, QImages>createList("images", Images.class, QImages.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final NumberPath<Long> ownerId = createNumber("ownerId", Long.class);

    public final NumberPath<Double> price = createNumber("price", Double.class);

    public final NumberPath<Long> statusId = createNumber("statusId", Long.class);

    public final NumberPath<Long> typeId = createNumber("typeId", Long.class);

    public final QUsers user;

    public QAds(String variable) {
        this(Ads.class, forVariable(variable), INITS);
    }

    public QAds(Path<? extends Ads> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAds(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAds(PathMetadata metadata, PathInits inits) {
        this(Ads.class, metadata, inits);
    }

    public QAds(Class<? extends Ads> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUsers(forProperty("user")) : null;
    }

}

