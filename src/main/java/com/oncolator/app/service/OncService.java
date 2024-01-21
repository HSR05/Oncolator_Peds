package com.oncolator.app.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.oncolator.app.Utility.CalculatorUtility;
import com.oncolator.app.Utility.JacksonUtility;
import com.oncolator.app.Utility.MapperUtility;
import com.oncolator.app.entity.dataEntity.Diagnosis;
import com.oncolator.app.entity.dataEntity.Patient;
import com.oncolator.app.entity.dataEntity.Results;
import com.oncolator.app.entity.dataEntity.parameters.ALL_ICICLE_Prophase_Parameters;
import com.oncolator.app.entity.dataEntity.parameters.ALL_ICICLE_SR_PostConsolidation_Parameters;
import com.oncolator.app.entity.dataEntity.parameters.ALL_ICICLE_SR_PostInduction_Parameters;
import com.oncolator.app.entity.dataEntity.parameters.ALL_ICICLE_HR_PostConsolidation_Parameters;
import com.oncolator.app.entity.dataEntity.parameters.ALL_ICICLE_HR_PostInduction_Parameters;
import com.oncolator.app.entity.dataEntity.parameters.ALL_ICICLE_IR_PostConsolidation_Parameters;
import com.oncolator.app.entity.dataEntity.parameters.ALL_ICICLE_IR_PostInduction_Parameters;
import com.oncolator.app.entity.dataEntity.parameters.ALL_ICICLE_PostProphase_Parameters;
import com.oncolator.app.entity.dataEntity.parameters.BaseParameters;
import com.oncolator.app.entity.enums.ParameterTypeEnum;
import com.oncolator.app.entity.requestEntity.AddDiagnosisParametersRequest;
import com.oncolator.app.entity.requestEntity.AddPatientDiagnosisRequest;
import com.oncolator.app.entity.requestEntity.AddPatientRequest;
import com.oncolator.app.entity.responseEntity.AddPatientDiagnosisResponse;
import com.oncolator.app.entity.responseEntity.AddPatientResponse;
import com.oncolator.app.entity.responseEntity.GetResultResponse;
import com.oncolator.app.entity.responseEntity.ResultResponse;
import com.oncolator.app.repository.DiagnosisRepository;
import com.oncolator.app.repository.DiagnosisTypeMapRepository;
import com.oncolator.app.repository.PatientRepository;
import com.oncolator.app.repository.PhaseDrugMapRepository;
import com.oncolator.app.repository.PhasesRepository;
import com.oncolator.app.repository.ResultsRepository;

@Component
public class OncService {

    private PatientRepository patientRepository;
    private DiagnosisRepository diagnosisRepository;
    private DiagnosisTypeMapRepository diagnosisTypeMapRepository;
    private ResultsRepository resultsRepository;
    private PhaseDrugMapRepository phaseDrugMapRepository;
    private PhasesRepository phasesRepository;
    private static final Logger logger = LogManager.getLogger(OncService.class);

    public OncService(PatientRepository patientRepository, DiagnosisRepository diagnosisRepository,
            ResultsRepository resultsRepository, PhaseDrugMapRepository phaseDrugMapRepository,
            PhasesRepository phasesRepository, DiagnosisTypeMapRepository diagnosisTypeMapRepository) {
        this.patientRepository = patientRepository;
        this.diagnosisRepository = diagnosisRepository;
        this.diagnosisTypeMapRepository = diagnosisTypeMapRepository;
        this.resultsRepository = resultsRepository;
        this.phaseDrugMapRepository = phaseDrugMapRepository;
        this.phasesRepository = phasesRepository;
    }

    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    public AddPatientResponse savePatient(AddPatientRequest patientRequest) {
        Patient patient = new Patient();
        patient.DOB = patientRequest.DOB;
        patient.height = patientRequest.height;
        patient.weight = patientRequest.weight;
        patient.sex = patientRequest.sex;
        patient.oncId = generateOncId(patient);

        patient.BMI = (float) (patientRequest.weight * 10000) / (patientRequest.height * patientRequest.height);
        patient.BSA = (float) Math.pow((float) (patientRequest.weight * patientRequest.height) / 3600f, 0.5f);

        boolean dbSaveResult = patientRepository.save(patient) != null;
        if (!dbSaveResult) {
            return null;
        }

        AddPatientDiagnosisRequest diagRequest = new AddPatientDiagnosisRequest();
        diagRequest.oncId = patient.oncId;
        diagRequest.diagnosis = patientRequest.diagnosis;
        diagRequest.regimen = patientRequest.regimen;

        AddPatientResponse response = new AddPatientResponse();
        response.oncId = patient.oncId;
        response.diagnosisInfo = addPatientDiagnosis(diagRequest);

        return response;
    }

    public GetResultResponse findPatient(long oncId, Date dob, Integer phaseId) {
        logger.info("Finding patient with oncId: " + oncId + ", dob: " + dob + " and phaseId: " + phaseId);
        Patient patient = patientRepository.findByOncIdAndDOB(oncId, dob);
        if (patient == null) {
            logger.info("Patient not found");
            return null;
        }

        return getResults(oncId, phaseId);
    }

    private long generateOncId(Patient patient) {
        int hospitalId = 1; // Default for now. Can change later
        long patientCount = patientRepository.countPatientInHospital(hospitalId);
        long oncId = (hospitalId * 10000) + patientCount + 1;
        return oncId;
    }

    public AddPatientDiagnosisResponse addPatientDiagnosis(AddPatientDiagnosisRequest request) {
        AddPatientDiagnosisResponse response = new AddPatientDiagnosisResponse();
        Diagnosis activeDiagnosis = diagnosisRepository.getActiveDiagnosis(request.oncId);
        Diagnosis inactiveDiagnosis = diagnosisRepository.getInactiveDiagnosis(request.oncId);
        if (activeDiagnosis != null && inactiveDiagnosis != null && inactiveDiagnosis.id > activeDiagnosis.id) {
            response.dId = inactiveDiagnosis.id;
            response.type = inactiveDiagnosis.parameterType == null ? -1 : inactiveDiagnosis.parameterType.ordinal();
            logger.debug("Returning existing diagnosis id: {}", response.dId); // 1

            return response;
        }
        Diagnosis diag = new Diagnosis();
        diag.oncId = request.oncId;
        diag.diagnosis = request.diagnosis;
        diag.regimen = request.regimen;
        diag.parameterType = resolveDiagnosisType(request.diagnosis, request.regimen, request.phaseId);
        diag.isActive = false;
        diag.parameters = null;

        Diagnosis dbSaveResult = diagnosisRepository.save(diag);

        response.dId = dbSaveResult.id;
        response.type = dbSaveResult.parameterType == null ? -1 : dbSaveResult.parameterType.ordinal();
        logger.debug("Created new diagnosis id: {}", response.dId); // 2

        return response;
    }

    public GetResultResponse addDiagnosisParameters(AddDiagnosisParametersRequest request) {
        Diagnosis diag = diagnosisRepository.findById(request.dId).get();

        if (request.parameters != null && !request.parameters.isEmpty()) {
            diag.parameters = request.parameters.toString();
        }

        GetResultResponse response = new GetResultResponse();

        response.result = calculateResults(diag);
        if (!response.result.isEmpty() && diag.phase == null) {
            diag.phase = phasesRepository.findById(diag.phaseId.intValue()).get();
        }
        response.diagnosisInfo = MapperUtility.mapDoagnosisInfoResponse(diag);
        response.patientInfo = MapperUtility.mapPatientInfoResponse(diag.patient);
        response.phaseInfo = MapperUtility.mapPhaseInfoResponse(diag.phase);
        if (response.phaseInfo != null)
            response.phaseInfo.availablePhasesList = MapperUtility
                    .mapLittlePhaseInfo(phasesRepository.getAvailablePhasesForOncId(diag.oncId));

        if (response.result != null && !response.result.isEmpty()) {
            // set all diagnosis to inactive for oncid
            diagnosisRepository.setAllDiagnosisInactive(diag.oncId);
            diag.isActive = true;
            Diagnosis dbSaveResult = diagnosisRepository.save(diag);
            System.out.println(dbSaveResult.id);
        }
        return response;
    }

    private List<ResultResponse> calculateResults(Diagnosis diag) {
        List<ResultResponse> response = new ArrayList<ResultResponse>();
        List<Results> results = new ArrayList<Results>();
        // check if result already exists for diagnosis
        if (resultsRepository.existsByDiagnosisId(diag.id) > 0) {
            results = resultsRepository.findByDiagnosisId(diag.id);
            for (Results result : results) {
                response.add(MapperUtility.mapResultResponse(result));
            }
            return response;
        }
        // if(diag.parameterType == null) {
        //     return response;
        // }
        // else{
            
        // }
        BaseParameters params = JacksonUtility.deserializeParameters(diag.parameterType, diag.parameters);
        switch ((diag.parameterType != null) ? diag.parameterType : ParameterTypeEnum.NULL) {
            case ALL_ICICLE_PROPHASE: {
                results = CalculatorUtility.calculateProphaseResults(diag, phaseDrugMapRepository, phasesRepository);
                break;
            }
            case ALL_ICICLE_PostProphase: {
                // Get previous diagnosis results from DB
                Diagnosis prophaseDiag = diagnosisRepository.getProphaseDiagnosisForPhase(diag.oncId,
                        phasesRepository.findByCode("PROPHASE").id);
                BaseParameters prophaseParams = JacksonUtility
                        .deserializeParameters(ParameterTypeEnum.ALL_ICICLE_PROPHASE, prophaseDiag.parameters);
                results = CalculatorUtility.calculatePostProphaseResults(
                        (ALL_ICICLE_Prophase_Parameters) prophaseParams, (ALL_ICICLE_PostProphase_Parameters) params,
                        diag, phaseDrugMapRepository, phasesRepository);
                break;
            }
            case ALL_ICICLE_SR_PostInduction: {
                results = CalculatorUtility.calculatePostInductionResults(
                        (ALL_ICICLE_SR_PostInduction_Parameters) params, diag, phaseDrugMapRepository,
                        phasesRepository);
                break;
            }
            case ALL_ICICLE_IR_PostInduction: {
                results = CalculatorUtility.calculatePostInductionResults(
                        (ALL_ICICLE_IR_PostInduction_Parameters) params, diag, phaseDrugMapRepository,
                        phasesRepository);
                break;
            }
            case ALL_ICICLE_HR_PostInduction: {
                results = CalculatorUtility.calculatePostInductionResults(
                        (ALL_ICICLE_HR_PostInduction_Parameters) params, diag, phaseDrugMapRepository,
                        phasesRepository);
                break;
            }
            case ALL_ICICLE_SR_PostConsolidation:
                results = CalculatorUtility.calculatePostConsolidationResults(
                        (ALL_ICICLE_SR_PostConsolidation_Parameters) params, diag, phaseDrugMapRepository,
                        phasesRepository);
                break;
            case ALL_ICICLE_IR_PostConsolidation:
                results = CalculatorUtility.calculatePostConsolidationResults(
                        (ALL_ICICLE_IR_PostConsolidation_Parameters) params, diag, phaseDrugMapRepository,
                        phasesRepository);
                break;
            case ALL_ICICLE_HR_PostConsolidation:
                results = CalculatorUtility.calculatePostConsolidationResults(
                        (ALL_ICICLE_HR_PostConsolidation_Parameters) params, diag, phaseDrugMapRepository,
                        phasesRepository);
                break;
            default:
                results = CalculatorUtility.calculateResults(diag, phaseDrugMapRepository, phasesRepository, diagnosisRepository);
                break;

        }

        if (results != null && !results.isEmpty()) {
            diag.phase = results.get(0).phase;
            resultsRepository.saveAll(results);
            for (Results result : results) {
                response.add(MapperUtility.mapResultResponse(result));
            }
        }

        return response;
    }

    public BaseParameters getDiagnosisParameters(int dId) {
        Diagnosis diag = diagnosisRepository.findById(dId).get();
        BaseParameters params = null;
        params = JacksonUtility.deserializeParameters(diag.parameterType, diag.parameters);
        return params;
    }

    private ParameterTypeEnum resolveDiagnosisType(String diagnosis, String regimen, Long phaseId) {
        Integer type;
        if (phaseId == null || phaseId == 0) {
            type = diagnosisTypeMapRepository.findTypeByDiagnosisAndRegimen(diagnosis.toUpperCase(),
                    regimen.toUpperCase());
        } else {
            type = diagnosisTypeMapRepository.findTypeByDiagnosisRegimenAndPhase(diagnosis.toUpperCase(),
                    regimen.toUpperCase(), phaseId);
        }
        if (type == null) {
            return null;
        }
        return ParameterTypeEnum.values()[type];
    }

    public GetResultResponse getResults(long oncId, Integer phaseId) {

        GetResultResponse response = new GetResultResponse();
        List<Results> dbResult = null;
        if (phaseId == null || phaseId == 0) {
            dbResult = resultsRepository.getResultsByOncId(oncId);
        } else {
            dbResult = resultsRepository.getResultsByOncIdAndPhaseId(oncId, phaseId);
        }
        if (dbResult == null || dbResult.isEmpty()) {
            response.patientInfo = MapperUtility.mapPatientInfoResponse(patientRepository.findByOncId(oncId));
            response.diagnosisInfo = MapperUtility
                    .mapDoagnosisInfoResponse(diagnosisRepository.getLatestDiagnosisByOncId(oncId));
            return response;
        }
        response.result = new ArrayList<ResultResponse>();
        for (Results results : dbResult) {
            response.result.add(MapperUtility.mapResultResponse(results));
        }

        response.patientInfo = MapperUtility.mapPatientInfoResponse(dbResult.get(0).diagnosis.patient);
        response.diagnosisInfo = MapperUtility.mapDoagnosisInfoResponse(dbResult.get(0).diagnosis);
        logger.debug("Start of the method");
        response.phaseInfo = MapperUtility.mapPhaseInfoResponse(dbResult.get(0).phase);
        logger.debug("End of the method");
        response.phaseInfo.availablePhasesList = MapperUtility
                .mapLittlePhaseInfo(phasesRepository.getAvailablePhasesForOncId(oncId));
        return response;
    }
}
