import { Injectable } from '@angular/core';
import { Cat, SCat } from '../models/categories';
import { HttpClient } from '@angular/common/http';
import Vary from '../utils/Vary';

@Injectable({
  providedIn: 'root',
})
export class ChildcatService {
  private readonly baseUrl = Vary.CHILDCAT_URL;
  constructor(private http: HttpClient) {}

  get(id: number) {
    return this.http.get<SCat>(this.baseUrl + '/' + id);
  }
  add(formData: FormData) {
    return this.http.post<SCat>(this.baseUrl, formData);
  }
  update(id: number, formData: FormData) {
    return this.http.put<SCat>(this.baseUrl + '/' + id, formData);
  }
  delete(id: number) {
    return this.http.delete(this.baseUrl + '/' + id);
  }
}
