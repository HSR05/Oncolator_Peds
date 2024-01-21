package com.oncolator.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.oncolator.app.entity.dataEntity.DiagnosisTypeMap;


@RepositoryRestResource()
public interface DiagnosisTypeMapRepository extends JpaRepository<DiagnosisTypeMap, Integer>, JpaSpecificationExecutor<DiagnosisTypeMap>, QuerydslPredicateExecutor<DiagnosisTypeMap>{
    @Query(value = "SELECT TOP 1 type FROM DiagnosisTypeMap d WHERE d.diagnosis = ?1 AND d.regimen = ?2 AND d.phaseId IS NULL", nativeQuery = true)
    Integer findTypeByDiagnosisAndRegimen(String diagnosis, String regimen);
    
    @Query(value = "SELECT TOP 1 type FROM DiagnosisTypeMap d WHERE d.diagnosis = ?1 AND d.regimen = ?2 AND d.phaseId = ?3", nativeQuery = true)
    Integer findTypeByDiagnosisRegimenAndPhase(String diagnosis, String regimen, Long phaseId);
}
