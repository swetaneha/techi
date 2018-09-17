import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TickettypedashboardComponent } from './tickettypedashboard.component';

describe('TickettypedashboardComponent', () => {
  let component: TickettypedashboardComponent;
  let fixture: ComponentFixture<TickettypedashboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TickettypedashboardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TickettypedashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
