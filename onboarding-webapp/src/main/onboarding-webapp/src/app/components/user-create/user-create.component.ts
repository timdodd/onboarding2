import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {UserService} from '../../services/user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-create.component.html',
  styleUrls: ['./user-create.component.css']
})
export class UserCreateComponent implements OnInit {

  formGroup = this.createFormGroup();

  constructor(private formBuilder: FormBuilder,
              private userService: UserService,
              private router: Router) {
  }

  ngOnInit() {
  }

  onSubmit() {
    const valueToSave = this.formGroup.value;
    this.userService.create(valueToSave).subscribe(() => {
      this.router.navigateByUrl('');
    });
  }

  private createFormGroup(): FormGroup {
    return this.formGroup = new FormGroup({
      firstName: new FormControl(null, [Validators.required, Validators.maxLength(50)] ),
      lastName: new FormControl(null, [Validators.required, Validators.maxLength(50)] ),
      username: new FormControl(null, [Validators.required, Validators.maxLength(50)] )
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
