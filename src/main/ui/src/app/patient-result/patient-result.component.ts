import { DataService } from './../data.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subject } from 'rxjs';
import { AppService } from '../app.service';
import { Router } from '@angular/router';
import { takeUntil } from 'rxjs/operators';
import { SharedService } from '../shared.service';

@Component({
  selector: 'app-patient-result',
  templateUrl: './patient-result.component.html',
  styleUrls: ['./patient-result.component.css']
})
export class PatientResultComponent implements OnInit {

  constructor(public dataservice: DataService, private appService: AppService, public router: Router, private sharedService: SharedService) { }
  diagnosisData: any;
  phaseData: any;
  availablePhaseList: any;
  activePhase: any;
  nextPhasePayload: NEXT_PHASE_PAYLOAD = {
    diagnosis: "",
    oncId: 0,
    phaseId: 0,
    regimen: ""
  };
  phaseSelectPayload: PHASE_SELECT_PAYLOAD = {
    dob: new Date(),
    oncId: 0,
    phaseId: 0
  };

  ngOnInit() {
    this.diagnosisData = this.dataservice.dosageData;
    console.log('Loaded diagnosis Data: ', this.diagnosisData);
    this.getAvailablePhaseList();
  }

  destroy$: Subject<boolean> = new Subject<boolean>();
  ngOnDestroy() {
    this.destroy$.next(true);
    this.destroy$.unsubscribe();
    this.dataservice.patientData = this.phaseData;
    console.log('saved data: ', this.dataservice.patientData);
  }

  getAvailablePhaseList() {
    this.availablePhaseList = this.diagnosisData.phaseInfo.availablePhasesList.map(phase => phase.phaseName);
    this.activePhase = this.diagnosisData.phaseInfo.phaseName;
    console.log('available phases: ', this.availablePhaseList);
  }

  enterNextPhase() {
    this.nextPhasePayload.diagnosis = this.diagnosisData.diagnosisInfo.diagnosis;
    this.nextPhasePayload.oncId = this.diagnosisData.patientInfo.oncId;
    this.nextPhasePayload.phaseId = this.diagnosisData.diagnosisInfo.phaseId;
    this.nextPhasePayload.regimen = this.diagnosisData.diagnosisInfo.regimen;
    this.appService.addDiagnosis(this.nextPhasePayload).pipe(takeUntil(this.destroy$)).subscribe((data: any) => {
      console.log('message::::', data);
      this.phaseData = data;
      this.router.navigate(['/diagnosis-input']);
    });
  }

  onPhaseSelect(value: string) {
    this.phaseSelectPayload.dob = this.diagnosisData.patientInfo.DOB;
    this.phaseSelectPayload.oncId = this.diagnosisData.patientInfo.oncId;
    this.phaseSelectPayload.phaseId = this.getPhaseId(value);
    this.appService.searchPhase(this.phaseSelectPayload).pipe(takeUntil(this.destroy$)).subscribe((data: any) => {
      console.log('Phase Result::::', data);
      this.dataservice.dosageData = data;
      this.sharedService.updateItems(data);
    });
  }

  getPhaseId(value: String): number {
    const phaseId = this.diagnosisData.phaseInfo.availablePhasesList.find(phase => phase.phaseName === value);
    console.log('Phase id =', phaseId);
    if (phaseId) {
      return phaseId.id;
    }
    return -1; //throw an error if not found
  }

}

interface NEXT_PHASE_PAYLOAD {
  diagnosis: string;
  oncId: Number;
  phaseId: Number;
  regimen: string;
}

interface PHASE_SELECT_PAYLOAD {
  dob: Date;
  oncId: Number;
  phaseId: Number;
}
