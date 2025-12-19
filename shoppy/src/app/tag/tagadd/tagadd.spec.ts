import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Tagadd } from './tagadd';

describe('Tagadd', () => {
  let component: Tagadd;
  let fixture: ComponentFixture<Tagadd>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Tagadd]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Tagadd);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
