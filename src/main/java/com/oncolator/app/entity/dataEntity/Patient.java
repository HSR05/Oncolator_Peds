package com.oncolator.app.entity.dataEntity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name = "Patient", schema = "dbo")
public class Patient {
    
    @Id
    @Column(name = "oncid")
    @NotNull(message="{NotNull.Patient.oncid}")
    public long oncId;
    
    @Column(name = "dob")
    @NotNull(message="{NotNull.Patient.DOB}")
    public Date DOB;

    @Column(name = "sex")
    @NotNull(message="{NotNull.Patient.sex}")
    public char sex;
    
    @Column(name = "height")
    @NotNull(message="{NotNull.Patient.height}")
    public float height;
    
    @Column(name = "weight")
    @NotNull(message="{NotNull.Patient.weight}")
    public float weight;
    
    @Column(name = "bmi")
    public float BMI;
    
    @Column(name = "bsa")
    public float BSA;
}
