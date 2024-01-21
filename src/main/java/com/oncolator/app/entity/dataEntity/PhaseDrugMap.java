package com.oncolator.app.entity.dataEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "PhaseDrugMap", schema = "dbo")
public class PhaseDrugMap {
    
    @Id
    @Column(name = "id")
    public long id;
    
    @Column(name = "phaseid")
    public long phaseId;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "phaseid", referencedColumnName="id", insertable=false, updatable=false)
    public Phases phase;
    
    @Column(name = "drugid")
    public long drugId;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "drugid", referencedColumnName="id", insertable=false, updatable=false)
    public Drugs drug;
    
    @Column(name = "dose")
    public Float dose;
    
    @Column(name = "duration")
    public long duration;
    
    @Column(name = "frequency")
    public String frequency;
    
    @Column(name = "notesoverride")
    public String notesOverride;
    
    @Column(name = "doses")
    public String doses;
}
