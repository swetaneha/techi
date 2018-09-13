import { TestBed, inject } from '@angular/core/testing';

import { GetGroupsService } from './get-groups.service';

describe('GetRequestService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GetGroupsService]
    });
  });

  it('should be created', inject([GetGroupsService], (service: GetGroupsService) => {
    expect(service).toBeTruthy();
  }));
});
