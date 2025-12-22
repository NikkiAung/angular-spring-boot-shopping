import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Userall } from './userall';

describe('Userall', () => {
  let component: Userall;
  let fixture: ComponentFixture<Userall>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Userall]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Userall);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
