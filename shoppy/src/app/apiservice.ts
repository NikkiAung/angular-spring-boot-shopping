import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import Vary from './utils/Vary';
import { Cat, CCat, SCat } from './models/categories';

@Injectable({
  providedIn: 'root',
})
export class Apiservice {
  private readonly baseUrl = Vary.API_URL;
  constructor(private http: HttpClient) {}

  getCategories() {
    return this.http.get<Cat[]>(this.baseUrl + '/cats');
  }

  getSubCategories() {
    return this.http.get<SCat[]>(this.baseUrl + '/subcats');
  }

  getChildCategories() {
    return this.http.get<CCat[]>(this.baseUrl + '/childcats');
  }
}
