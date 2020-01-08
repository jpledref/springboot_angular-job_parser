import { TestBed } from '@angular/core/testing';

import { ConfigloaderService } from './configloader.service';

describe('ConfigloaderService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ConfigloaderService = TestBed.get(ConfigloaderService);
    expect(service).toBeTruthy();
  });
});
