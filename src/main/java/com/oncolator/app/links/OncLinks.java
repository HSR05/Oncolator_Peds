package com.oncolator.app.links;

import org.springframework.stereotype.Component;

@Component
public class OncLinks {
    public static final String LIST_PATIENTS = "/patients";
    public static final String ADD_PATIENT = "/addpatient";
    public static final String FIND_PATIENT = "/patient";
    public static final String ADD_DIAGNOSIS = "/addDiagnosis";
    public static final String ADD_DIAGNOSIS_PARAMETERS = "/addDiagnosisParameters";
    public static final String GET_DIAGNOSIS_PARAMETERS = "/getDiagnosisParameters";
    public static final String GET_RESULTS = "/getResult";
}
