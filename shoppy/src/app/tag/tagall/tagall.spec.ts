import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Tagall } from './tagall';

describe('Tagall', () => {
  let component: Tagall;
  let fixture: ComponentFixture<Tagall>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Tagall]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Tagall);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
