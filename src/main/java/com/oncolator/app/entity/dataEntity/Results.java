package com.oncolator.app.entity.dataEntity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "Results", schema = "dbo")
public class Results {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long id;
    
    @Column(name = "diagid")
    public long diagId;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "diagid", referencedColumnName="id", insertable=false, updatable=false)
    public Diagnosis diagnosis;
    
    @Column(name = "drugid")
    public long drugId;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "drugid", referencedColumnName="id", insertable=false, updatable=false)
    public Drugs drug;
    
    @Column(name = "phaseid")
    public long phaseId;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "phaseid", referencedColumnName="id", insertable=false, updatable=false)
    public Phases phase;
    
    @Column(name = "dosage")
    public float dosage;
    
    @Column(name = "startdate")
    public Date startDate;
    
    @Column(name = "duration")
    public long duration;
    
    @Column(name = "doses")
    public String doses;
    
    @Column(name = "checklist")
    public String checkList;
    
}
