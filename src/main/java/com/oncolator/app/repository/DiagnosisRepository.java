package com.oncolator.app.repository;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import com.oncolator.app.entity.dataEntity.Diagnosis;

@RepositoryRestResource()
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Integer>, JpaSpecificationExecutor<Diagnosis>,
        QuerydslPredicateExecutor<Diagnosis> {

    @Query(value = "SELECT TOP 1 * FROM Diagnosis d WHERE d.oncId = ?1 AND d.isActive = 1", nativeQuery = true)
    Diagnosis getActiveDiagnosis(long oncId);

    @Query(value = "SELECT TOP 1 * FROM Diagnosis d WHERE d.oncId = ?1 AND d.isActive = 1", nativeQuery = true)
    Diagnosis getPreviousPhaseDiagnosis(long oncId);
    
    @Query(value = "SELECT TOP 1 * FROM Diagnosis d WHERE d.oncId = ?1 AND d.phaseId = ?2 AND d.isActive = 1", nativeQuery = true)
    Diagnosis getProphaseDiagnosisForPhase(@NotNull(message = "{NotNull.Diagnosis.oncId}") long oncId, int phaseId);

    @Query(value = "SELECT TOP 1 * FROM Diagnosis d WHERE d.oncId = ?1 AND d.isActive = 0 ORDER BY Id desc", nativeQuery = true)
    Diagnosis getInactiveDiagnosis(long oncId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Diagnosis SET isActive = 0 WHERE oncId = ?1")
    void setAllDiagnosisInactive(@NotNull(message = "{NotNull.Diagnosis.oncId}") long oncId);

    @Query(value = "SELECT TOP 1 * FROM Diagnosis d WHERE d.oncId = ?1 ORDER BY Id desc", nativeQuery = true)
    Diagnosis getLatestDiagnosisByOncId(long oncId);

}
