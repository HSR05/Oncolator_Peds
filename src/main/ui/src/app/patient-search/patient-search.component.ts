import { Component, OnDestroy } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AppService } from '../app.service';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { DataService } from './../data.service';
import { Router } from '@angular/router';
import { DialogMessageComponent } from '../dialog-message/dialog-message.component';

@Component({
  selector: 'app-patient-search',
  templateUrl: './patient-search.component.html',
  styleUrls: ['./patient-search.component.css']
})
export class PatientSearchComponent implements OnDestroy {

  constructor(private appService: AppService, public dataservice: DataService, public router: Router) { }

  searchForm = new FormGroup({
    oncId: new FormControl('', Validators.nullValidator && Validators.required),
    dob: new FormControl('', Validators.nullValidator && Validators.required)
  });
  oncId;

  destroy$: Subject<boolean> = new Subject<boolean>();

  onPatientSearch() {
    this.appService.searchPatient(this.searchForm.value).pipe(takeUntil(this.destroy$)).subscribe((data: any) => {
      this.searchForm.reset();
      console.log('search result::::', data);
      if (data === null) {
        console.error('Patient not found');
        this.searchForm.reset();
      }
      else if (data.phaseInfo === null) {
        this.dataservice.patientData = data;
        this.router.navigate(['/diagnosis-input']);
      }
      else {
        this.dataservice.dosageData = data;
        this.router.navigate(['/patient-result']);
      }
    });
  }


  ngOnDestroy() {
    this.destroy$.next(true);
    this.destroy$.unsubscribe();
  }
}
