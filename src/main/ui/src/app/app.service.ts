import { Params } from '@angular/router';
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(private http: HttpClient) { }

  rootURL = '/api';

  getPatients() {
    return this.http.get(this.rootURL + '/patients');
  }

  addPatient(user: any) {
    return this.http.post(this.rootURL + '/addpatient', user);
  }

  searchPatient(patient: any) {
    let queryParams: Params = {
      "oncId": patient.oncId,
      "dob": patient.dob
    };
    return this.http.get(this.rootURL + '/patient', { params: queryParams }).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 500) {
          console.error('Internal server error:', error);
        }
        return of(null);
      }));
  }

  searchPhase(data: any) {
    return this.http.get(this.rootURL + '/patient', {params: data });
  }

  addDiagnosisParameters(data: any) {
    return this.http.post(this.rootURL + '/addDiagnosisParameters', data);
  }

  addDiagnosis(data: any) {
    return this.http.post(this.rootURL + '/addDiagnosis', data);
  }

}
