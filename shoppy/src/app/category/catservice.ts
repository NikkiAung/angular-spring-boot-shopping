import { Injectable } from '@angular/core';
import { Cat } from '../models/categories';
import { HttpClient } from '@angular/common/http';
import Vary from '../utils/Vary';

@Injectable({
  providedIn: 'root',
})
export class Catservice {
  private readonly baseUrl = Vary.CAT_URL;
  constructor(private http: HttpClient) {}

  get(id: number) {
    return this.http.get<Cat>(this.baseUrl + '/' + id);
  }
  add(formData: FormData) {
    return this.http.post<Cat>(this.baseUrl, formData);
  }
  update(id: number, formData: FormData) {
    return this.http.put<Cat>(this.baseUrl + '/' + id, formData);
  }
  delete(id: number) {
    return this.http.delete(this.baseUrl + '/' + id);
  }
}
