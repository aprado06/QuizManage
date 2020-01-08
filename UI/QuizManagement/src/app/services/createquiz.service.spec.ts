import { TestBed } from '@angular/core/testing';

import { CreatequizService } from './createquiz.service';

describe('CreatequizService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CreatequizService = TestBed.get(CreatequizService);
    expect(service).toBeTruthy();
  });
});
