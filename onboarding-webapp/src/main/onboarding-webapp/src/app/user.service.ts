import { Injectable } from '@angular/core';
import {of} from "rxjs";
import {delay} from "rxjs/operators";
import {UserModel} from "./model/user.model";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  users: User[] = [];

  constructor() {
    this.users.push({
      userId: "123",
      firstName: "Tim",
      lastName: "Dodd"
    });
  }


  findAll(): Observable<UserModel[]> {
    return of(this.users).pipe(
      delay(2000)
    );
  }

  save(user: UserModel): Observable<UserModel> {
    this.users.push(user);
    return of(user);
  }
}
