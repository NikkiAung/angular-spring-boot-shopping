import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Tagedit } from './tagedit';

describe('Tagedit', () => {
  let component: Tagedit;
  let fixture: ComponentFixture<Tagedit>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Tagedit]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Tagedit);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
