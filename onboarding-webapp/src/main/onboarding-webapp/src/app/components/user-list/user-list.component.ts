import {Component, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {UserService} from '../../services/user-service/user.service';
import {UserModel} from '../../models/user.model';
import {NavigationExtras, Router} from '@angular/router';

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
    this.router.navigateByUrl('/createUser');
  }

  onDelete(userId: string) {
    this.userService.delete(userId).subscribe(() => this.ngOnInit());
  }

  onEdit(userId: string) {
    const navigationExtras: NavigationExtras = {
      queryParams: {
        'userId': userId
      }
    };
    this.router.navigate(['/editUser'], navigationExtras);
  }


}
