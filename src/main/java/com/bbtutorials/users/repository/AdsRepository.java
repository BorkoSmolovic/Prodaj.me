package com.bbtutorials.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.bbtutorials.users.entity.Ads;

@RepositoryRestResource()
public interface AdsRepository extends JpaRepository<Ads, Integer>, JpaSpecificationExecutor<Ads>, QuerydslPredicateExecutor<Ads> {}
