import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateTicketTypeComponent } from './create-ticket-type.component';

describe('CreateTicketTypeComponent', () => {
  let component: CreateTicketTypeComponent;
  let fixture: ComponentFixture<CreateTicketTypeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateTicketTypeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateTicketTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
