package com.oncolator.app.repository;
import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.oncolator.app.entity.dataEntity.Patient;

@RepositoryRestResource()
public interface PatientRepository extends JpaRepository<Patient, Integer>, JpaSpecificationExecutor<Patient>, QuerydslPredicateExecutor<Patient> {
    
    Patient findByOncIdAndDOB(long oncId, Date DOB);
    
    @Query("SELECT COUNT(*) FROM Patient p WHERE p.oncId > ?1 * 10000 AND p.oncId < (?1 + 1)*10000")
    long countPatientInHospital(int hospitalId);

    @Query(value = "SELECT TOP 1 * FROM Patient WHERE oncId = ?1", nativeQuery = true)
    Patient findByOncId(long oncId);
}
