package com.oncolator.app.entity.dataEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.oncolator.app.entity.enums.ParameterTypeEnum;

import lombok.Data;

@Entity
@Data
@Table(name = "Diagnosis", schema = "dbo")
public class Diagnosis {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int id;
    
    @Column(name = "oncid", nullable=false)
    @NotNull(message = "{NotNull.Diagnosis.oncId}")
    public long oncId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "oncid", referencedColumnName="oncid", insertable=false, updatable=false)
    public Patient patient;

    @Column(name = "diagnosis")
    @NotNull(message = "{NotNull.Diagnosis.diagnosis}")
    public String diagnosis;

    @Column(name = "regimen")
    @NotNull(message = "{NotNull.Diagnosis.regimen}")
    public String regimen;
    
    @Column(name = "type")
    public ParameterTypeEnum parameterType;

    @Column(name = "phaseid")
    public Long phaseId;
    
    @ManyToOne(optional = true)
    @JoinColumn(name = "phaseid", referencedColumnName="id", insertable=false, updatable=false)
    public Phases phase;

    @Column(name = "isactive")
    public Boolean isActive;

    @Column(name = "parameters")
    public String parameters;

}
