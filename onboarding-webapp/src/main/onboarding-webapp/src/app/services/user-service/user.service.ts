import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {UserModel} from '../../models/user.model';
import {HttpClient} from '@angular/common/http';

const BASE_URI = './api/v1/users';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  find(userId: string): Observable<UserModel> {
    return this.http.get<UserModel>(BASE_URI + '/' + userId);
  }

  findAll(): Observable<UserModel[]> {
    return this.http.get<UserModel[]>(BASE_URI);
  }

  save(user: UserModel): Observable<UserModel> {
    return this.http.post<UserModel>(BASE_URI, user);
  }

  delete(userId: string): Observable<void> {
    return this.http.delete<void>(BASE_URI + '/' + userId);
  }

  update(user: UserModel): Observable<UserModel> {
    return this.http.put<UserModel>(BASE_URI + '/' + user.userId, user);
  }

}
