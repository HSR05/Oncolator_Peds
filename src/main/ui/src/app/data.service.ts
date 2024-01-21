import { Injectable } from '@angular/core';
import { patientData } from './patient-data';
import { dosageData } from './dosage-data';
@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor() { }
  public patientData: patientData;
  public dosageData: dosageData;
}