import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BignumberComponent } from './bignumber.component';

describe('BignumberComponent', () => {
  let component: BignumberComponent;
  let fixture: ComponentFixture<BignumberComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BignumberComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BignumberComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
