package com.oncolator.app.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oncolator.app.entity.dataEntity.Patient;
import com.oncolator.app.entity.requestEntity.AddDiagnosisParametersRequest;
import com.oncolator.app.entity.requestEntity.AddPatientDiagnosisRequest;
import com.oncolator.app.entity.requestEntity.AddPatientRequest;
import com.oncolator.app.entity.responseEntity.AddPatientDiagnosisResponse;
import com.oncolator.app.entity.responseEntity.AddPatientResponse;
import com.oncolator.app.entity.responseEntity.GetResultResponse;
import com.oncolator.app.links.OncLinks;
import com.oncolator.app.service.OncService;

import io.swagger.annotations.ApiOperation;
import lombok.var;

@RestController
@RequestMapping("/api/")
public class OncController {
    @Autowired
	OncService oncService;
    
    @ApiOperation(value = "API to get the list of Patients", notes = "This API returns Patient info like DOB, height, weight & sex and adds from database.")
    @GetMapping(path = OncLinks.LIST_PATIENTS)
    public List<Patient> listPatients() {
        List<Patient> resource = oncService.getPatients();
        return resource;
    }
    
    @ApiOperation(value = "API to add patient", notes = "This API takes basic Patient info like DOB, height, weight & sex. Aditionally it takes diagnosis & regimen and adds to database. Also returns the ONC_ID and diagnosisInfo of the patient")
    @PostMapping(path = OncLinks.ADD_PATIENT)
    public ResponseEntity<?> addPatient(@RequestBody AddPatientRequest patient)
    {
        var result = oncService.savePatient(patient);
        if(result != null)
        {
            return ResponseEntity.ok(result);
        }
        else
        {
            return new ResponseEntity<AddPatientResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ApiOperation(value = "API find patient", notes = "This API takes ONC_ID and DOB of Patient and returns the patient based on these values")
    @GetMapping(path = OncLinks.FIND_PATIENT)
    public ResponseEntity<?> findPatient(@RequestParam long oncId, Date dob, Integer phaseId)
    {
        var result = oncService.findPatient(oncId, dob, phaseId);
        if(result != null)
        {
            return ResponseEntity.ok(result);
        }
        else
        {
            return new ResponseEntity<Patient>(HttpStatus.NO_CONTENT);
        }
    }
    
    @ApiOperation(value = "API to add new Diagnosis in database", notes = "This API takes ONC_ID, Diagnosis and Regimen of Patient and returns the diagnosis id(dId) and (type) of parameters")
    @PostMapping(path = OncLinks.ADD_DIAGNOSIS)
    public ResponseEntity<?> addPatientDiagnosis(@RequestBody AddPatientDiagnosisRequest request) {
        var result = new AddPatientResponse();
        result.oncId = request.oncId;
        result.diagnosisInfo = oncService.addPatientDiagnosis(request);
        if (result.diagnosisInfo != null) {
            return ResponseEntity.ok(result);
        } else {
            return new ResponseEntity<AddPatientDiagnosisResponse>(HttpStatus.NOT_FOUND);
        }
    }
    
    @ApiOperation(value = "API to add dynamic Parameters for a diagnosis", notes = "This API takes Diagnosis ID, type and the parameters and returns the patient based on these values")
    @PostMapping(path = OncLinks.ADD_DIAGNOSIS_PARAMETERS)
    public ResponseEntity<?> addDiagnosisParameters(@RequestBody AddDiagnosisParametersRequest request) {
        var result = oncService.addDiagnosisParameters(request);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return new ResponseEntity<Patient>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ApiOperation(value = "API to get the dynamic parameters from db", notes = "This API takes Diagnosis ID as Query Param and returns the parameters set in database")
    @GetMapping(path = OncLinks.GET_DIAGNOSIS_PARAMETERS)
    public ResponseEntity<?> getDiagnosisParameters(@RequestParam int dId) {
        var result = oncService.getDiagnosisParameters(dId);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
        }
    }
    
    @ApiOperation(value = "API to get the dynamic parameters from db", notes = "This API takes Diagnosis ID as Query Param and returns the parameters set in database")
    @GetMapping(path = OncLinks.GET_RESULTS)
    public ResponseEntity<?> getResultScreen(@RequestParam int oncId) {
        GetResultResponse result = oncService.getResults(oncId, null);
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return new ResponseEntity<Patient>(HttpStatus.NO_CONTENT);
        }
    }
}
