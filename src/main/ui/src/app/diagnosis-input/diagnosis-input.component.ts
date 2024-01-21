import { DataService } from './../data.service';
import { Component, OnDestroy } from '@angular/core';
import { AppService } from '../app.service';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { diagnosisForm } from './../diagnosis-form';
import { Location } from '@angular/common';

interface DIAGNOSIS {
  dId: Number;
  parameters: {};
}
@Component({
  selector: 'app-diagnosis-input',
  templateUrl: './diagnosis-input.component.html',
  styleUrls: ['./diagnosis-input.component.css']
})
export class DiagnosisInputComponent implements OnDestroy {

  diagnosisForm = new FormGroup({})
  constructor(public dataservice: DataService, private appService: AppService, public router: Router, public DiagnosisFormObj: diagnosisForm, private location: Location) { }
  patientData: any;
  oncId: any;
  formType: any;
  diagnosisData: DIAGNOSIS = { dId: 0, parameters: {} };
  ngOnInit() {
    this.patientData = this.dataservice.patientData;
    if (this.patientData.oncId) {
      this.oncId = this.patientData.oncId;
    }
    else {
      this.oncId = this.patientData.patientInfo.oncId;
    }
    this.formType = this.patientData.diagnosisInfo.type;
    console.log('Loaded patiend Data: ', this.patientData);
  }

  onDiagnosisSubmit() {
    this.diagnosisData.dId = this.patientData.diagnosisInfo.dId;
    this.diagnosisData.parameters = this.diagnosisForm.value;
    this.appService.addDiagnosisParameters(this.diagnosisData).pipe(takeUntil(this.destroy$)).subscribe((data: any) => {
      console.log('Diagnosis Result::::', data);
      this.dataservice.dosageData = data;
      this.diagnosisForm.reset();
      this.router.navigate(['/patient-result']);
    });
  }

  goBack() {
    this.location.back();
  }

  destroy$: Subject<boolean> = new Subject<boolean>();
  ngOnDestroy() {
    this.destroy$.next(true);
    this.destroy$.unsubscribe();
  }

}
