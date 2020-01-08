import { TestBed } from '@angular/core/testing';

import { TakequizService } from './takequiz.service';

describe('TakequizService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TakequizService = TestBed.get(TakequizService);
    expect(service).toBeTruthy();
  });
});
