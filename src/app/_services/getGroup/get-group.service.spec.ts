import { TestBed, inject } from '@angular/core/testing';

import { GetGroupService } from './get-group.service';

describe('GetGroupService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GetGroupService]
    });
  });

  it('should be created', inject([GetGroupService], (service: GetGroupService) => {
    expect(service).toBeTruthy();
  }));
});
