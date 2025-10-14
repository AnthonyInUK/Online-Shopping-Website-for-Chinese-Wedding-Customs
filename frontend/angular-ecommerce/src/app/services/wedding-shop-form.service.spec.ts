import { TestBed } from '@angular/core/testing';

import { WeddingShopFormService } from './wedding-shop-form.service';

describe('WeddingShopFormService', () => {
  let service: WeddingShopFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WeddingShopFormService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
