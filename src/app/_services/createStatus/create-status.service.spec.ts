import { TestBed, inject } from '@angular/core/testing';
 import { CreateServiceService } from './create-status.service';
 CreateServiceService
describe('CreateStatusService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CreateServiceService]
    });
  });

  it('should be created', inject([CreateServiceService], (service: CreateServiceService) => {
    expect(service).toBeTruthy();
  }));
});
