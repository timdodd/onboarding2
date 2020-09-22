import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserPhoneVerificationModalComponent } from './user-phone-verification-modal.component';

describe('UserPhoneVerificationModalComponent', () => {
  let component: UserPhoneVerificationModalComponent;
  let fixture: ComponentFixture<UserPhoneVerificationModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserPhoneVerificationModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserPhoneVerificationModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
