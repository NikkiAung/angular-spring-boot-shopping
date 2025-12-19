import { Injectable } from '@angular/core';
import { Cat } from '../models/categories';
import { HttpClient } from '@angular/common/http';
import Vary from '../utils/Vary';
import { ProductModel } from '../models/productmodel';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private readonly baseUrl = Vary.PRODUCT_URL;
  constructor(private http: HttpClient) {}

  get(id: number) {
    return this.http.get<ProductModel>(this.baseUrl + '/' + id);
  }
  add(formData: FormData) {
    return this.http.post<ProductModel>(this.baseUrl, formData);
  }
  update(id: number, formData: FormData) {
    return this.http.put<ProductModel>(this.baseUrl + '/' + id, formData);
  }
  delete(id: number) {
    return this.http.delete(this.baseUrl + '/' + id);
  }
}
