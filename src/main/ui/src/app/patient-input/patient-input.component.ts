import { DataService } from './../data.service';
import { Component, OnDestroy } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AppService } from '../app.service';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import Swal from 'sweetalert2/dist/sweetalert2.js';
import { Router } from '@angular/router';
@Component({
  selector: 'app-patient-input',
  templateUrl: './patient-input.component.html',
  styleUrls: ['./patient-input.component.css']
})
export class PatientInputComponent implements OnDestroy {

  constructor(private appService: AppService, public router: Router, public dataservice: DataService) { }

  title = 'angular-nodejs-oncolator';

  patientForm = new FormGroup({
    DOB: new FormControl('', Validators.nullValidator && Validators.required),
    height: new FormControl('', Validators.nullValidator && Validators.required && Validators.min(0)),
    weight: new FormControl('', Validators.nullValidator && Validators.required && Validators.min(0)),
    sex: new FormControl('', Validators.nullValidator && Validators.required),
    HistoryOfPriorTreatment: new FormControl(''),
    diagnosis: new FormControl('', Validators.nullValidator && Validators.required),
    regimen: new FormControl('', Validators.nullValidator && Validators.required),
  });

  patients: any[] = [];
  patientData: any;
  destroy$: Subject<boolean> = new Subject<boolean>();
  inputType = "number";

  onPatientSubmit() {
    this.appService.addPatient(this.patientForm.value).pipe(takeUntil(this.destroy$)).subscribe((data: any) => {
      console.log('message::::', data);
      this.patientData = data;
      //   this.showSuccessMessage(
      //     'Patient ID: '+ this.data,
      //     'Please note the patient ID for future reference',
      //     'success',
      // )  
      this.dataservice.patientData = this.patientData;
      console.log('saved data: ', this.dataservice.patientData);
      this.patientForm.reset();
      this.router.navigate(['/diagnosis-input']);
    });
  }

  getAllPatients() {
    this.appService.getPatients().pipe(takeUntil(this.destroy$)).subscribe((users: any[]) => {
      this.patients = users;
    });
  }

  ngOnDestroy() {
    this.destroy$.next(true);
    this.destroy$.unsubscribe();
  }

  showSuccessMessage(
    title, message, icon,
    showCancelButton = false) {
    return Swal.fire({
      title: title,
      text: message,
      icon: icon,
      showCancelButton: showCancelButton
    })
  }

}
