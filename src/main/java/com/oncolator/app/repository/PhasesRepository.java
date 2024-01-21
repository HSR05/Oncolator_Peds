package com.oncolator.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.oncolator.app.entity.dataEntity.Phases;

@RepositoryRestResource()
public interface PhasesRepository extends JpaRepository<Phases, Integer>, JpaSpecificationExecutor<Phases>, QuerydslPredicateExecutor<Phases> {

    @Query(value = "SELECT TOP 1 * FROM Phases p WHERE UPPER(p.phaseCode) = UPPER(?1)", nativeQuery = true)
    Phases findByCode(String phaseCode);
    
    @Query(value = "SELECT DISTINCT * FROM Phases p JOIN Diagnosis d ON d.phaseId = p.id WHERE d.oncId = ?1", nativeQuery = true)
	List<Phases> getAvailablePhasesForOncId(long oncId);

}

    
