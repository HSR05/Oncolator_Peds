package com.oncolator.app.entity.responseEntity;

import java.sql.Date;

public class ResultResponse {
    public long id;
    public DrugInfoResponse drugInfo;
    public float dosageQuantity;
    public Date startDate;
    public long duration;
    public Float[] dosage;
    public Boolean[] checkList;
}
