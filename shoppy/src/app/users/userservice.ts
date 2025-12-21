import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import Vary from '../utils/Vary';
import { UserModel } from '../models/usermodel';

@Injectable({
  providedIn: 'root',
})
export class Userservice {
  private readonly baseUrl = Vary.USERS_URL;
  constructor(private http: HttpClient) {}

  get(id: number) {
    return this.http.get<UserModel>(this.baseUrl + '/' + id);
  }
  all() {
    return this.http.get<UserModel[]>(this.baseUrl);
  }
  addRole(userId: number, roleId: number) {
    return this.http.patch<any>(this.baseUrl + '/add/role/' + userId + '/' + roleId, {});
  }
  removeRole(userId: number, roleId: number) {
    return this.http.patch<any>(this.baseUrl + '/remove/role/' + userId + '/' + roleId, {});
  }
}
