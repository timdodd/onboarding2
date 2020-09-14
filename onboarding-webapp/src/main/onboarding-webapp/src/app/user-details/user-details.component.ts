import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl} from "@angular/forms";
import {UserService} from "../user.service";

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  formGroup = this.createFormGroup();

  constructor(private formBuilder: FormBuilder,
              private userService: UserService) {
  }

  ngOnInit() {
  }

  onSubmit() {
    const valueToSave = this.formGroup.value;
    this.userService.save(valueToSave);
  }

  private createFormGroup(): FormGroup {
    return this.formGroup = this.formBuilder.group({
      firstName : null,
      lastName : null,
      username : null
    });
  }



}
