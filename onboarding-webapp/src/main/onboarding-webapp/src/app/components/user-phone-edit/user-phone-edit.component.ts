import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {PhoneService} from '../../services/phone.service';
import {ActivatedRoute, NavigationExtras, Router} from '@angular/router';
import {phoneNumberValidator} from '../../validators/phone-validator';
import {PhoneModel} from '../../models/phone.model';

@Component({
  selector: 'app-user-phone-edit',
  templateUrl: './user-phone-edit.component.html',
  styleUrls: ['./user-phone-edit.component.css']
})
export class UserPhoneEditComponent implements OnInit {
  phone: PhoneModel;
  userId: string;
  phoneId: string;
  formGroup = this.createFormGroup();

  constructor(private formBuilder: FormBuilder,
              private phoneService: PhoneService,
              private router: Router,
              private route: ActivatedRoute) {
    this.route.queryParams.subscribe(params => {
      this.phoneId = params['phoneId'];
      this.userId = params['userId'];
    });
  }

  ngOnInit() {
    this.phoneService.getUserPhone(this.userId, this.phoneId).subscribe(phone => {
      this.phone = phone;
      this.updateFormGroup(phone);
    } );
  }

  onSubmit() {
    const valueToSave = this.formGroup.value;
    const navigationExtras: NavigationExtras = {
      queryParams: {
        'userId': this.userId
      }
    };
    this.phoneService.update(this.userId, this.phoneId, valueToSave).subscribe(data => {
      this.router.navigate(['/userPhones'], navigationExtras);
    });
  }

  createFormGroup(): FormGroup {
    return this.formGroup = new FormGroup({
      phoneName: new FormControl(null, [Validators.required, Validators.maxLength(50)]),
      phoneNumber: new FormControl(null, [Validators.required, Validators.maxLength(11), phoneNumberValidator]),
      primaryPhoneNumber: new FormControl(false)
    });
  }

  private updateFormGroup(phone: PhoneModel): void {
    this.formGroup.patchValue({
      userId: this.phone.userId,
      phoneId: this.phone.phoneId,
      phoneName: this.phone.phoneName,
      phoneNumber: this.phone.phoneNumber,
      primaryPhoneNumber: this.phone.primaryPhoneNumber,
      phoneNumberVerified: this.phone.phoneNumberVerified,
    });
  }

  get phoneName() {
    return this.formGroup.get('phoneName');
  }

  get phoneNumber() {
    return this.formGroup.get('phoneNumber');
  }

}
