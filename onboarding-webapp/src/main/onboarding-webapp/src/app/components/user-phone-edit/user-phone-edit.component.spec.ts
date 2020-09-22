import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserPhoneEditComponent } from './user-phone-edit.component';

describe('UserPhoneEditComponent', () => {
  let component: UserPhoneEditComponent;
  let fixture: ComponentFixture<UserPhoneEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserPhoneEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserPhoneEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
