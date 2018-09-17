import { TestBed, inject } from '@angular/core/testing';

import { DisplayNameService } from './display-name.service';

describe('DisplayNameService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DisplayNameService]
    });
  });

  it('should be created', inject([DisplayNameService], (service: DisplayNameService) => {
    expect(service).toBeTruthy();
  }));
});
