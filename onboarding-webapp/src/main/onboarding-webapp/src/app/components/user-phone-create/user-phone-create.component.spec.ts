import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserPhoneCreateComponent } from './user-phone-create.component';

describe('UserPhoneDetailsComponent', () => {
  let component: UserPhoneCreateComponent;
  let fixture: ComponentFixture<UserPhoneCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserPhoneCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserPhoneCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
