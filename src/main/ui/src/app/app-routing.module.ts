import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PatientInputComponent } from './patient-input/patient-input.component';
import { DiagnosisInputComponent } from './diagnosis-input/diagnosis-input.component';
import { ResultTableComponent } from './result-table/result-table.component';
import { PatientResultComponent } from './patient-result/patient-result.component';
import { AboutUsComponent } from './about-us/about-us.component';

const routes: Routes = [
  {path: 'patient-input', component: PatientInputComponent},
  {path: 'diagnosis-input', component: DiagnosisInputComponent},
  {path: 'result-table', component: ResultTableComponent},
  {path: 'patient-result', component: PatientResultComponent},
  {path: 'about-us', component: AboutUsComponent},
  {path: '',   redirectTo: '/patient-input', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: false})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
