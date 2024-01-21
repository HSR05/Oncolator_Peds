package com.oncolator.app.entity.dataEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "Phases", schema = "dbo")
public class Phases {
    @Id
    @Column(name = "id")
    public int id;
    
    @Column(name = "phasecode")
    public String phaseCode;

    @Column(name = "phasename")
    public String phaseName;

    @Column(name = "risk")
    public String risk;
    
    @Column(name = "randomisation")
    public String randomisation;
    
    @Column(name = "daysoftherapy")
    public int daysOfTherapy;

    @Column(name = "startweek")
    public int startWeek;

    @Column(name = "endweek")
    public int endWeek;

    @Column(name = "phasedescription")
    public String phaseDescription;

    @Column(name = "intrathecalinjections")
    public int intrathecalInjections;
    
    @Column(name = "possiblevalues")
    public String possibleValues;
}
