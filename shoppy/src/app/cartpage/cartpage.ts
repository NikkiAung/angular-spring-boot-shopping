import { Component } from '@angular/core';
import { DBProductModel } from '../models/dbproductmodel';

@Component({
  selector: 'app-cartpage',
  imports: [],
  templateUrl: './cartpage.html',
  styleUrl: './cartpage.css',
})
export class Cartpage {
  products: DBProductModel[] = [];
  constructor() {
    this.products = JSON.parse(localStorage.getItem('products') || '');
  }

  reduceQuantity(product: DBProductModel) {
    if (product.count > 1) {
      product.count--;
    } else {
      this.removeProduct(product);
    }
    this.uploadLocalStorage();
  }

  removeProduct(product: DBProductModel) {
    this.products = this.products.filter((p) => p.id !== product.id);
    this.uploadLocalStorage();
  }

  increaseQuantity(product: DBProductModel) {
    product.count++;
    this.uploadLocalStorage();
  }

  uploadLocalStorage() {
    localStorage.setItem('product', JSON.stringify(this.products));
  }
}
