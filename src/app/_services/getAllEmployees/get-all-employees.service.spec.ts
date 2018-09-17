import { TestBed, inject } from '@angular/core/testing';

import { GetAllEmployessService } from './get-all-employees.service';

describe('GetAllEmplyeesService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GetAllEmployessService]
    });
  });

  it('should be created', inject([GetAllEmployessService], (service: GetAllEmployessService) => {
    expect(service).toBeTruthy();
  }));
});
