import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {UserModel} from "./model/user.model";
import {HttpClient} from "@angular/common/http";

const BASE_URI = './api/v1/users';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  findAll(): Observable<UserModel[]> {
    return this.http.get<UserModel[]>(BASE_URI);
  }

  save(user: UserModel): Observable<UserModel> {
    return this.http.post<UserModel>(BASE_URI, user);
  }
}
