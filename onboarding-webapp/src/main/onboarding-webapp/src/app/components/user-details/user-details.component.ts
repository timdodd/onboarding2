import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {UserService} from '../../services/user-service/user.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  formGroup = this.createFormGroup();

  constructor(private formBuilder: FormBuilder,
              private userService: UserService,
              private router: Router) {
  }

  ngOnInit() {
  }

  onSubmit() {
    const valueToSave = this.formGroup.value;
    this.userService.save(valueToSave).subscribe(data => {
      this.router.navigateByUrl('');
    });
  }

  private createFormGroup(): FormGroup {
    return this.formGroup = this.formBuilder.group({
      firstName: null,
      lastName: null,
      username: null
    });
  }


}
