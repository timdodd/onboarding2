import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, NavigationExtras, Router} from '@angular/router';
import {PhoneService} from '../../services/phone.service';
import {phoneNumberValidator} from '../../validators/phone-validator';


@Component({
  selector: 'app-user-phone-details',
  templateUrl: './user-phone-create.component.html',
  styleUrls: ['./user-phone-create.component.css']
})
export class UserPhoneCreateComponent implements OnInit {

  userId: string;
  formGroup = this.createFormGroup();

  constructor(private formBuilder: FormBuilder,
              private phoneService: PhoneService,
              private router: Router,
              private route: ActivatedRoute) {
    this.route.queryParams.subscribe(params => {
      this.userId = params['userId'];
    });
  }

  ngOnInit() {
  }

  onSubmit() {
    const valueToSave = this.formGroup.value;
    const navigationExtras: NavigationExtras = {
      queryParams: {
        'userId': this.userId
      }
    };
    this.phoneService.create(this.userId, valueToSave).subscribe(data => {
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

  get phoneName() {
    return this.formGroup.get('phoneName');
  }

  get phoneNumber() {
    return this.formGroup.get('phoneNumber');
  }

}
