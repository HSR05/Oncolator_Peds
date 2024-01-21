package com.oncolator.app.Utility;

import java.util.List;

import com.oncolator.app.entity.dataEntity.Diagnosis;
import com.oncolator.app.entity.dataEntity.Drugs;
import com.oncolator.app.entity.dataEntity.Patient;
import com.oncolator.app.entity.dataEntity.PhaseDrugMap;
import com.oncolator.app.entity.dataEntity.Phases;
import com.oncolator.app.entity.dataEntity.Results;
import com.oncolator.app.entity.responseEntity.DiagnosisInfoResponse;
import com.oncolator.app.entity.responseEntity.DrugInfoResponse;
import com.oncolator.app.entity.responseEntity.LittlePhaseInfo;
import com.oncolator.app.entity.responseEntity.PatientInfoResponse;
import com.oncolator.app.entity.responseEntity.PhaseInfoResponse;
import com.oncolator.app.entity.responseEntity.ResultResponse;

public final class MapperUtility {

    public static PhaseInfoResponse mapPhaseInfoResponse(Phases phase) {
        if (phase == null)
            return null;
        PhaseInfoResponse response = new PhaseInfoResponse();
        response.daysOfTherapy = phase.daysOfTherapy;
        response.endWeek = phase.endWeek;
        response.id = phase.id;
        response.intrathecalInjections = phase.intrathecalInjections;
        response.phaseCode = phase.phaseCode;
        response.phaseDescription = phase.phaseDescription;
        response.phaseName = phase.phaseName;
        response.possibleValues = phase.possibleValues;
        response.randomisation = phase.randomisation;
        response.risk = phase.risk;
        response.startWeek = phase.startWeek;
        return response;
    }

    public static DiagnosisInfoResponse mapDoagnosisInfoResponse(Diagnosis diagnosis) {
        if (diagnosis == null)
            return null;
        DiagnosisInfoResponse response = new DiagnosisInfoResponse();
        response.diagnosis = diagnosis.diagnosis;
        response.dId = diagnosis.id;
        response.isActive = diagnosis.isActive;
        response.phaseId = diagnosis.phaseId;
        response.regimen = diagnosis.regimen;
        response.typeEnum = diagnosis.parameterType;
        response.type = diagnosis.parameterType != null ? diagnosis.parameterType.ordinal() : -1;
        response.parameters = JacksonUtility.deserializeParameters(diagnosis.parameterType, diagnosis.parameters);
        return response;
    }

    public static PatientInfoResponse mapPatientInfoResponse(Patient patient) {
        if (patient == null)
            return null;
        PatientInfoResponse response = new PatientInfoResponse();
        response.BMI = patient.BMI;
        response.BSA = patient.BSA;
        response.DOB = patient.DOB;
        response.height = patient.height;
        response.oncId = patient.oncId;
        response.sex = patient.sex;
        response.weight = patient.weight;
        return response;
    }

    public static ResultResponse mapResultResponse(Results result) {
        if (result == null)
            return null;
        ResultResponse response = new ResultResponse();
        response.id = result.id;
        response.dosageQuantity = result.dosage;
        response.drugInfo = MapperUtility.mapDrugInfoResponse(result.drug);
        response.duration = result.duration;
        response.startDate = result.startDate;
        response.checkList = JacksonUtility.deserializeCheckList(result.checkList);
        response.dosage = JacksonUtility.deserializeDoseList(result.doses);
        return response;
    }

    private static DrugInfoResponse mapDrugInfoResponse(Drugs drug) {
        if (drug == null)
            return null;
        DrugInfoResponse response = new DrugInfoResponse();
        response.id = drug.id;
        response.description = drug.description;
        response.drugName = drug.drugName;
        response.footNote = drug.footNote;
        response.maxDosage = drug.maxDosage;
        response.route = drug.route;
        response.unit = drug.unit;
        return response;
    }

    public static float getDosageForDrug(PhaseDrugMap phaseDrugMap, float bsa, int age) {
        if (phaseDrugMap == null) {
            return 0;
        }
        switch ((int) phaseDrugMap.drugId) {
            case 3:
                if (age < 2)
                    return 8;
                else if (age >= 2 && age < 3)
                    return 10;
                else if (age >= 3)
                    return 12;
            case 6:
                if (bsa >= 0.5 && bsa <= 0.75)
                    return 240;
                else if (bsa >= 0.76 && bsa <= 1)
                    return 360;
                else if (bsa > 1)
                    return 480;
            default:
                return phaseDrugMap.dose;
        }
    }

    public static void mapResultsFromPhaseDrugMap(Results results, PhaseDrugMap phaseDrugMap) {
        if (phaseDrugMap == null)
            return;
        results.drugId = phaseDrugMap.drugId;
        results.drug = phaseDrugMap.drug;
        results.duration = phaseDrugMap.duration;
        results.phaseId = phaseDrugMap.phaseId;
        results.phase = phaseDrugMap.phase;
    }

    public static void mapResultsFromDiagnosis(Results results, Diagnosis diag) {
        if (diag == null)
            return;
        results.diagId = diag.id;
        results.diagnosis = diag;
    }

    public static List<LittlePhaseInfo> mapLittlePhaseInfo(List<Phases> availablePhasesForOncId) {
        if (availablePhasesForOncId == null)
            return null;
        return availablePhasesForOncId.stream().map(phase -> {
            LittlePhaseInfo littlePhaseInfo = new LittlePhaseInfo();
            littlePhaseInfo.id = phase.id;
            littlePhaseInfo.phaseName = phase.phaseName;
            littlePhaseInfo.phaseCode = phase.phaseCode;
            return littlePhaseInfo;
        }).collect(java.util.stream.Collectors.toList());
    }
}
