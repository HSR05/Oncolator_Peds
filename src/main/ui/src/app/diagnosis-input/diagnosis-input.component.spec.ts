import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DiagnosisInputComponent } from './diagnosis-input.component';

describe('DiagnosisInputComponent', () => {
  let component: DiagnosisInputComponent;
  let fixture: ComponentFixture<DiagnosisInputComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DiagnosisInputComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DiagnosisInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
