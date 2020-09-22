import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {PhoneModel} from '../models/phone.model';
import {HttpClient} from '@angular/common/http';

const BASE_URI = '/api/v1/users';

@Injectable({
  providedIn: 'root'
})
export class PhoneService {

  constructor(private http: HttpClient) { }

  getAllPhones(userId: string): Observable<PhoneModel[]> {
    return this.http.get<PhoneModel[]>(BASE_URI + '/' + userId + '/phones');
  }

  getUserPhone(userId: string, phoneId: string) {
    return this.http.get<PhoneModel>(BASE_URI + '/' + userId + '/phones/' + phoneId);
  }

  create(userId: string, phone: PhoneModel): Observable<PhoneModel> {
    return this.http.post<PhoneModel>(BASE_URI + '/' + userId + '/phones', phone);
  }

  delete(userId: string, phoneId: string): Observable<void> {
    return this.http.delete<void>(BASE_URI + '/' + userId + '/phones/' + phoneId);
  }

  update(userId: string, phoneId: string, phone: PhoneModel): Observable<PhoneModel> {
    return this.http.put<PhoneModel>(BASE_URI + '/' + userId + '/phones/' + phoneId, phone);
  }

  sendVerify(userId: string, phone: PhoneModel): Observable<void> {
    return this.http.post<void>(BASE_URI + '/' + userId + '/phones/' + phone.phoneId + '/sendVerify', phone);
  }

  checkVerify(userId: string, phone: PhoneModel, code: string): Observable<void> {
    return this.http.put<void>(BASE_URI + '/' + userId + '/phones/' + phone.phoneId + '/verify/' + code, phone);
  }
}
