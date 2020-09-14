import {Component, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {UserService} from "../user.service";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {


  users: User[] = [];
  loadingSubscription = Subscription.EMPTY;

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.loadingSubscription = this.userService.findAll().subscribe(users => {
      this.users = users;
    });
  }

}
