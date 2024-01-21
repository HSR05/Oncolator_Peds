package com.oncolator.app.entity.dataEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "Drugs", schema = "dbo")
public class Drugs {
    @Id
    @Column(name = "id")
    public long id;

    @Column(name = "drugname")
    public String drugName;

    @Column(name = "route")
    public String route;

    @Column(name = "maxdosage")
    public Float maxDosage;
    
    @Column(name = "rounded")
    public Float rounded;

    @Column(name = "unit")
    public String unit;

    @Column(name = "description")
    public String description;

    @Column(name = "footnote")
    public String footNote;
}
