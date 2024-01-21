package com.oncolator.app.entity.responseEntity;

import com.oncolator.app.entity.dataEntity.parameters.BaseParameters;
import com.oncolator.app.entity.enums.ParameterTypeEnum;

public class DiagnosisInfoResponse {
    public int dId;
    public String diagnosis;
    public String regimen;
    public ParameterTypeEnum typeEnum;
    public int type;
    public Long phaseId;
    public boolean isActive;
    public BaseParameters parameters;
}
