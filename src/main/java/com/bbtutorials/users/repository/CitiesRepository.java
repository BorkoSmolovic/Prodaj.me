package com.bbtutorials.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.bbtutorials.users.entity.Cities;

@RepositoryRestResource()
public interface CitiesRepository extends JpaRepository<Cities, Integer>, JpaSpecificationExecutor<Cities>, QuerydslPredicateExecutor<Cities> {}
