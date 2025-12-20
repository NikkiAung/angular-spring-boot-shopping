import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import Vary from '../utils/Vary';
import { RoleModel } from '../models/rolemodel';

@Injectable({
  providedIn: 'root',
})
export class RoleService {
  private readonly baseUrl = Vary.ROLE_URL;
  constructor(private http: HttpClient) {}

  get(id: number) {
    return this.http.get<RoleModel>(this.baseUrl + '/' + id);
  }

  all() {
    return this.http.get<RoleModel[]>(this.baseUrl);
  }
  add(formData: FormData) {
    return this.http.post<RoleModel>(this.baseUrl, formData);
  }
  update(id: number, formData: FormData) {
    return this.http.patch<RoleModel>(this.baseUrl + '/' + id, formData);
  }
  delete(id: number) {
    return this.http.delete(this.baseUrl + '/' + id);
  }
}
