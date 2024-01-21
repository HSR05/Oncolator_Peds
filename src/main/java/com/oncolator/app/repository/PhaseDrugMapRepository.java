package com.oncolator.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.oncolator.app.entity.dataEntity.PhaseDrugMap;

@RepositoryRestResource()
public interface PhaseDrugMapRepository extends JpaRepository<PhaseDrugMap, Integer>, JpaSpecificationExecutor<PhaseDrugMap>, QuerydslPredicateExecutor<PhaseDrugMap> {

    @Query(value = "SELECT * FROM PhaseDrugMap WHERE phaseId = ?1", nativeQuery = true)
    List<PhaseDrugMap> getListByPhaseId(Long phaseId);}
