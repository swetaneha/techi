import { TestBed, inject } from '@angular/core/testing';

import { SharedWorkflowService } from './shared-workflow.service';

describe('SharedWorkflowService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SharedWorkflowService]
    });
  });

  it('should be created', inject([SharedWorkflowService], (service: SharedWorkflowService) => {
    expect(service).toBeTruthy();
  }));
});
