package com.oncolator.app.Utility;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.oncolator.app.entity.dataEntity.Diagnosis;
import com.oncolator.app.entity.dataEntity.PhaseDrugMap;
import com.oncolator.app.entity.dataEntity.Results;
import com.oncolator.app.entity.dataEntity.parameters.ALL_ICICLE_HR_PostConsolidation_Parameters;
import com.oncolator.app.entity.dataEntity.parameters.ALL_ICICLE_HR_PostInduction_Parameters;
import com.oncolator.app.entity.dataEntity.parameters.ALL_ICICLE_IR_PostConsolidation_Parameters;
import com.oncolator.app.entity.dataEntity.parameters.ALL_ICICLE_IR_PostInduction_Parameters;
import com.oncolator.app.entity.dataEntity.parameters.ALL_ICICLE_PostProphase_Parameters;
import com.oncolator.app.entity.dataEntity.parameters.ALL_ICICLE_Prophase_Parameters;
import com.oncolator.app.entity.dataEntity.parameters.ALL_ICICLE_SR_PostConsolidation_Parameters;
import com.oncolator.app.entity.dataEntity.parameters.ALL_ICICLE_SR_PostInduction_Parameters;
import com.oncolator.app.repository.DiagnosisRepository;
import com.oncolator.app.repository.PhaseDrugMapRepository;
import com.oncolator.app.repository.PhasesRepository;

public final class CalculatorUtility {
    public static List<Results> calculateProphaseResults(Diagnosis diag,
            PhaseDrugMapRepository phaseDrugMapRepository,
            PhasesRepository phasesRepository) {
        if (diag == null) {
            // TODO: Throw an exception instead and add handlers.
            return null;
        }
        // Assign PhaseCode
        String phaseCode = "PROPHASE";
        diag.phaseId = (long) phasesRepository.findByCode(phaseCode).id;
        return buildResultsList(diag, phaseDrugMapRepository);
    }

    public static List<Results> calculatePostProphaseResults(ALL_ICICLE_Prophase_Parameters prophaseParams,
            ALL_ICICLE_PostProphase_Parameters postProphaseParams, Diagnosis diag,
            PhaseDrugMapRepository phaseDrugMapRepository,
            PhasesRepository phasesRepository) {
        if (prophaseParams == null || postProphaseParams == null || diag == null)
            return new ArrayList<Results>();
        int patientAge = Period.between(diag.patient.DOB.toLocalDate(), LocalDate.now()).getYears();
        String phaseCode = null;
        // Assign PhaseCode
        if ((patientAge >= 0 && patientAge < 10)
                && prophaseParams.highestWBC < 50000
                && !prophaseParams.enlargedLiver
                && !prophaseParams.enlargedLymphNodes
                && !prophaseParams.enlargedSpleen
                && prophaseParams.randomization == 'A'
                && postProphaseParams.prednisoloneResponse < 1000
        // && !params.extramedullaryDisease
        // && !params.mediastinalMass
        )
            phaseCode = "SR-IN-R1A-IT3";
        if ((patientAge >= 0 && patientAge < 10)
                && prophaseParams.highestWBC < 50000
                && !prophaseParams.enlargedLiver
                && !prophaseParams.enlargedLymphNodes
                && !prophaseParams.enlargedSpleen
                && prophaseParams.randomization == 'B'
                && postProphaseParams.prednisoloneResponse < 1000
        // && !params.extramedullaryDisease
        // && !params.mediastinalMass)
        )
            phaseCode = "SR-IN-R1B-IT3";
        if ((patientAge >= 0 && patientAge < 10)
                && (prophaseParams.highestWBC >= 50000
                        || prophaseParams.enlargedLiver
                        || prophaseParams.enlargedLymphNodes
                        || prophaseParams.enlargedSpleen)
                && prophaseParams.randomization == 'A'
                && postProphaseParams.prednisoloneResponse < 1000
        // && !prophaseParams.extramedullaryDisease
        // && !prophaseParams.mediastinalMass
        )
            phaseCode = "IR-IN-R1A-IT3";
        if ((patientAge >= 0 && patientAge < 10)
                && (prophaseParams.highestWBC >= 50000
                        || prophaseParams.enlargedLiver
                        || prophaseParams.enlargedLymphNodes
                        || prophaseParams.enlargedSpleen)
                && prophaseParams.randomization == 'B'
                && postProphaseParams.prednisoloneResponse < 1000
        // && !prophaseParams.extramedullaryDisease
        // && !prophaseParams.mediastinalMass
        )
            phaseCode = "IR-IN-R1B-IT3";
        if (patientAge >= 10
                && postProphaseParams.prednisoloneResponse < 1000
        // && !prophaseParams.extramedullaryDisease
        // && !prophaseParams.mediastinalMass
        )
            phaseCode = "IR-IN-AD";
        if (!postProphaseParams.CNSDisease
                && (postProphaseParams.prednisoloneResponse > 1000
                        || !postProphaseParams.cytogenetics.equalsIgnoreCase("none"))
        // && !prophaseParams.extramedullaryDisease
        // && !prophaseParams.mediastinalMass
        )
            phaseCode = "HR-IN-IT3";

        if (postProphaseParams.CNSDisease
        // && !prophaseParams.extramedullaryDisease
        // && !prophaseParams.mediastinalMass
        )
            phaseCode = "HR-IN-IT5";

        diag.phaseId = (long) phasesRepository.findByCode(phaseCode).id;
        return buildResultsList(diag, phaseDrugMapRepository);
    }

    public static List<Results> calculatePostInductionResults(ALL_ICICLE_SR_PostInduction_Parameters params,
            Diagnosis diag, PhaseDrugMapRepository phaseDrugMapRepository, PhasesRepository phasesRepository) {
        if (params == null || diag == null)
            return null;
        String phaseCode = null;
        // Assign PhaseCode
        if (params.boneMarrowMicroscopy == 0
                && params.CNSStatus == false
                && params.MRDStatus == false)
            phaseCode = "SR-CO";
        if (params.boneMarrowMicroscopy >= 1
                || params.CNSStatus == true
                || params.MRDStatus == true)
            phaseCode = "HR-CO";

        diag.phaseId = (long) phasesRepository.findByCode(phaseCode).id;
        return buildResultsList(diag, phaseDrugMapRepository);
    }

    public static List<Results> calculatePostInductionResults(ALL_ICICLE_IR_PostInduction_Parameters params,
            Diagnosis diag, PhaseDrugMapRepository phaseDrugMapRepository, PhasesRepository phasesRepository) {
        if (params == null || diag == null)
            return null;
        String phaseCode = null;
        // Assign PhaseCode
        if (params.boneMarrowMicroscopy == 0
                && params.CNSStatus == false
                && params.MRDStatus == false)
            phaseCode = "IR-CO";
        if (params.boneMarrowMicroscopy >= 1
                || params.CNSStatus == true
                || params.MRDStatus == true)
            phaseCode = "HR-CO";

        diag.phaseId = (long) phasesRepository.findByCode(phaseCode).id;
        return buildResultsList(diag, phaseDrugMapRepository);
    }

    public static List<Results> calculatePostInductionResults(ALL_ICICLE_HR_PostInduction_Parameters params,
            Diagnosis diag, PhaseDrugMapRepository phaseDrugMapRepository, PhasesRepository phasesRepository) {
        if (params == null || diag == null)
            return null;
        String phaseCode = "HR-CO";

        diag.phaseId = (long) phasesRepository.findByCode(phaseCode).id;
        return buildResultsList(diag, phaseDrugMapRepository);
    }

    public static List<Results> calculatePostConsolidationResults(ALL_ICICLE_SR_PostConsolidation_Parameters params,
            Diagnosis diag, PhaseDrugMapRepository phaseDrugMapRepository, PhasesRepository phasesRepository) {
        if (params == null || diag == null)
            return null;
        String phaseCode = null;
        // Assign PhaseCode
        if (params.testicularDisease == false
                && params.mediastinalMass == false
                && params.boneMarrowMorphology.equalsIgnoreCase("normal"))
            phaseCode = "SR-IM";
        if ((params.testicularDisease == true
                || params.mediastinalMass == true)
                && params.boneMarrowMorphology.equalsIgnoreCase("normal"))
            phaseCode = "IR-IM";
        if (params.boneMarrowMorphology.equalsIgnoreCase("abnormal"))
            phaseCode = "HR-IM";

        diag.phaseId = (long) phasesRepository.findByCode(phaseCode).id;
        return buildResultsList(diag, phaseDrugMapRepository);
    }

    public static List<Results> calculatePostConsolidationResults(ALL_ICICLE_IR_PostConsolidation_Parameters params,
            Diagnosis diag, PhaseDrugMapRepository phaseDrugMapRepository, PhasesRepository phasesRepository) {
        if (params == null || diag == null)
            return null;
        String phaseCode = "IR-IM";
        // Assign PhaseCode
        if (params.boneMarrowMorphology.equalsIgnoreCase("abnormal"))
            phaseCode = "HR-IM";

        diag.phaseId = (long) phasesRepository.findByCode(phaseCode).id;
        return buildResultsList(diag, phaseDrugMapRepository);
    }

    public static List<Results> calculatePostConsolidationResults(ALL_ICICLE_HR_PostConsolidation_Parameters params,
            Diagnosis diag, PhaseDrugMapRepository phaseDrugMapRepository, PhasesRepository phasesRepository) {
        if (params == null || diag == null)
            return null;
        String phaseCode = "HR-IM";

        diag.phaseId = (long) phasesRepository.findByCode(phaseCode).id;
        return buildResultsList(diag, phaseDrugMapRepository);
    }

    public static List<Results> calculateResults(Diagnosis diag, PhaseDrugMapRepository phaseDrugMapRepository,
            PhasesRepository phasesRepository, DiagnosisRepository diagnosisRepository) {
        Diagnosis activeDiagnosis = diagnosisRepository.getActiveDiagnosis(diag.oncId);
        if (activeDiagnosis == null)
            return null;
        String phaseCode = null;
        switch (activeDiagnosis.phase.phaseCode) {
            case "SR-IM":
                phaseCode = "SR-DI-R2A";
                break;
            case "IR-IM":
                phaseCode = "IR-DI-R2A";
                break;
            case "HR-IM":
                phaseCode = "HR-DI-R2A";
                break;
            case "SR-DI-R2A":
                phaseCode = "SR-MT-C1";
                break;
            case "SR-DI-R2B":
                phaseCode = "SR-MT-C1";
                break;
            case "IR-DI-R2A":
                phaseCode = "IR-MT-C1";
                break;
            case "IR-DI-R2B":
                phaseCode = "IR-MT-C1";
                break;
            case "HR-DI-R2A":
                phaseCode = "HR-MT-C1";
                break;
            case "HR-DI-R2B":
                phaseCode = "HR-MT-C1";
                break;
            case "SR-MT-C1":
                phaseCode = "SR-MT-C2";
                break;
            case "IR-MT-C1":
                phaseCode = "IR-MT-C2";
                break;
            case "HR-MT-C1":
                phaseCode = "HR-MT-C2";
                break;
            case "SR-MT-C2":
                phaseCode = "SR-MT-C3";
                break;
            case "IR-MT-C2":
                phaseCode = "IR-MT-C3";
                break;
            case "HR-MT-C2":
                phaseCode = "HR-MT-C3";
                break;
            case "SR-MT-C3":
                phaseCode = "SR-MT-C4";
                break;
            case "IR-MT-C3":
                phaseCode = "IR-MT-C4";
                break;
            case "HR-MT-C3":
                phaseCode = "HR-MT-C4";
                break;
            case "SR-MT-C4":
                phaseCode = "SR-MT-C5";
                break;
            case "IR-MT-C4":
                phaseCode = "IR-MT-C5";
                break;
            case "HR-MT-C4":
                phaseCode = "HR-MT-C5";
                break;
            case "SR-MT-C5":
                phaseCode = "SR-MT-C6";
                break;
            case "IR-MT-C5":
                phaseCode = "IR-MT-C6";
                break;
            case "HR-MT-C5":
                phaseCode = "HR-MT-C6";
                break;
            case "SR-MT-C6":
                phaseCode = "SR-MT-C7";
                break;
            case "IR-MT-C6":
                phaseCode = "IR-MT-C7";
                break;
            case "HR-MT-C6":
                phaseCode = "HR-MT-C7";
                break;
            case "SR-MT-C7":
                phaseCode = "SR-MT-C8";
                break;
            case "IR-MT-C7":
                phaseCode = "IR-MT-C8";
                break;
            case "HR-MT-C7":
                phaseCode = "HR-MT-C8";
                break;
            default:
                phaseCode = "HR-MT-C8";
                break;
        }
        diag.phaseId = (long) phasesRepository.findByCode(phaseCode).id;
        return buildResultsList(diag, phaseDrugMapRepository);
    }

    private static List<Results> buildResultsList(Diagnosis diag,
            PhaseDrugMapRepository phaseDrugMapRepository) {
        int patientAge = Period.between(diag.patient.DOB.toLocalDate(), LocalDate.now()).getYears();
        List<Results> resultsList = new ArrayList<Results>();
        List<PhaseDrugMap> phaseDrugMapList = phaseDrugMapRepository.getListByPhaseId(diag.phaseId);
        for (PhaseDrugMap phaseDrugMap : phaseDrugMapList) {
            Results result = new Results();
            MapperUtility.mapResultsFromDiagnosis(result, diag);
            MapperUtility.mapResultsFromPhaseDrugMap(result, phaseDrugMap);
            result.dosage = MapperUtility.getDosageForDrug(phaseDrugMap, diag.patient.BSA, patientAge);
            result.startDate = Date.valueOf(LocalDate.now());
            List<Float> dosesList = Arrays.asList(JacksonUtility.deserializeDoseList(phaseDrugMap.doses));
            Float dose = phaseDrugMap.drugId == 3 || phaseDrugMap.drugId == 6 ? result.dosage
                    : result.dosage * diag.patient.BSA;
            Float max = phaseDrugMap.drug.maxDosage;
            for (int i = 0; i < dosesList.size(); i++) {
                float val = dosesList.get(i) * dose;
                if (phaseDrugMap.drug.rounded != null && phaseDrugMap.drug.rounded != 0) {
                    val = phaseDrugMap.drug.rounded *
                            Math.round((float) val / phaseDrugMap.drug.rounded);
                }
                if (max != null && val > max) {
                    val = max;
                }
                dosesList.set(i, val);
            }
            result.doses = JacksonUtility.serializeDoses(dosesList);
            resultsList.add(result);
        }

        return resultsList;
    }

}
