package com.oncolator.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.oncolator.app.entity.dataEntity.Drugs;

@RepositoryRestResource()
public interface DrugsRepository extends JpaRepository<Drugs, Integer>, JpaSpecificationExecutor<Drugs>, QuerydslPredicateExecutor<Drugs> {}
