import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {UserService} from '../../services/user-service/user.service';
import {ActivatedRoute, Router} from '@angular/router';
import {UserModel} from '../../models/user.model';

@Component({
  selector: 'app-user-details-edit',
  templateUrl: './user-details-edit.component.html',
  styleUrls: ['./user-details-edit.component.css']
})
export class UserDetailsEditComponent implements OnInit {
  user: UserModel;
  userId: string;
  formGroup = this.createFormGroup();

  constructor(private formBuilder: FormBuilder,
              private userService: UserService,
              private router: Router,
              private route: ActivatedRoute) {
    this.route.queryParams.subscribe(params => {
      this.userId = params['userId'];
    });
  }

  ngOnInit() {
    this.userService.find(this.userId).subscribe(user => {
      this.user = user;
      this.updateFormGroup(user);
    } );
  }

  onSubmit() {
    this.formGroup.patchValue({userId: this.userId});
    const valueToSave: UserModel = this.formGroup.value;
    this.userService.update(valueToSave).subscribe(data => {
      this.router.navigateByUrl('');
    } );
  }

  private createFormGroup(): FormGroup {
    return this.formGroup = new FormGroup({
      userId: new FormControl(null, [Validators.required] ),
      firstName: new FormControl(null, [Validators.required, Validators.maxLength(50)] ),
      lastName: new FormControl(null, [Validators.required, Validators.maxLength(50)] ),
      username: new FormControl(null, [Validators.required, Validators.maxLength(50)] )
    });
  }

  private updateFormGroup(user: UserModel): void {
    this.formGroup.patchValue({
      userId: this.user.userId,
      firstName: this.user.firstName,
      lastName: this.user.lastName,
      username: this.user.username
    });
  }

  get firstName() {
    return this.formGroup.get('firstName');
  }

  get lastName() {
    return this.formGroup.get('lastName');
  }

  get username() {
    return this.formGroup.get('username');
  }
}
