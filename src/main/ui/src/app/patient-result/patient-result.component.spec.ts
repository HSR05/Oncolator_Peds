import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PatientResultComponent } from './patient-result.component';

describe('PatientResultComponent', () => {
  let component: PatientResultComponent;
  let fixture: ComponentFixture<PatientResultComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PatientResultComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PatientResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
