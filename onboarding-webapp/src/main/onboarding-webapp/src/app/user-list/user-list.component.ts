import {Component, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {UserService} from "../user.service";
import {UserModel} from "../model/user.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {


  users: UserModel[] = [];
  loadingSubscription = Subscription.EMPTY;

  constructor(private userService: UserService,
              private router: Router) {
  }

  ngOnInit() {
    this.loadingSubscription = this.userService.findAll().subscribe(users => {
      this.users = users;
    });
  }

  onCreate() {
    this.router.navigateByUrl('/create');
  }

}
