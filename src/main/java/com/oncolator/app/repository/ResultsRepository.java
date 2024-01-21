package com.oncolator.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.oncolator.app.entity.dataEntity.Results;

@RepositoryRestResource()
public interface ResultsRepository
        extends JpaRepository<Results, Integer>, JpaSpecificationExecutor<Results>,
        QuerydslPredicateExecutor<Results> {

    @Query(value = "SELECT * FROM Results WHERE diagId = ?1 AND phaseId = ?2", nativeQuery = true)
    List<Results> getResultsByDiagnosisIdAndPhaseId(long diagId, long phaseId);

    @Query(value = "SELECT * FROM Results WHERE diagId = (SELECT TOP 1 id FROM Diagnosis WHERE oncId = ?1 AND isActive = 1)AND phaseId = (SELECT TOP 1 phaseId FROM Diagnosis WHERE oncId = ?1 AND isActive = 1)", nativeQuery = true)
    List<Results> getResultsByOncId(long oncId);

    @Query(value = "SELECT COUNT(*) FROM Results WHERE diagId = ?1", nativeQuery = true)
    int existsByDiagnosisId(int diagId);

    @Query(value = "SELECT * FROM Results WHERE diagId = ?1", nativeQuery = true)
    List<Results> findByDiagnosisId(int diagId);

    @Query(value = "SELECT * FROM Results WHERE diagId = (SELECT TOP 1 id FROM Diagnosis WHERE oncId = ?1 AND phaseId = ?2)", nativeQuery = true)
    List<Results> getResultsByOncIdAndPhaseId(long oncId, Integer phaseId);
}
