package com.oncolator.app.entity.dataEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name = "Diagnosis", schema = "dbo")
public class DiagnosisTypeMap {
    @Id
    @Column(name = "id")
    public long id;
    
    @Column(name = "diagnosis")
    @NotNull(message = "{NotNull.Diagnosis.diagnosis}")
    public String diagnosis;

    @Column(name = "regimen")
    @NotNull(message = "{NotNull.Diagnosis.regimen}")
    public String regimen;
    
    @Column(name = "type")
    @NotNull(message = "{NotNull.Diagnosis.type}")
    public int type;
}
