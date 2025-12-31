import { Component } from '@angular/core';
import { DBProductModel } from '../models/dbproductmodel';
import { ChangeService } from '../change-service';
import { Apiservice } from '../apiservice';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cartpage',
  imports: [CommonModule],
  templateUrl: './cartpage.html',
  styleUrl: './cartpage.css',
})
export class Cartpage {
  products: DBProductModel[] = [];
  constructor(
    private apiService: Apiservice,
    private changeService: ChangeService,
    private rotuer: Router
  ) {
    this.products = JSON.parse(localStorage.getItem('products') || '[]');
  }

  ngOnInit() {
    this.changeService.updateProductCount(this.products.length);
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
    this.changeService.updateProductCount(this.products.length);
  }

  increaseQuantity(product: DBProductModel) {
    product.count++;
    this.uploadLocalStorage();
  }

  uploadLocalStorage() {
    localStorage.setItem('products', JSON.stringify(this.products));
  }

  getTotalPrice(): number {
    return this.products.reduce((total, product) => total + product.price * product.count, 0);
  }

  checkOut() {
    let items = this.products.map((product) => ({
      id: product.id,
      count: product.count,
    }));

    let orderData = {
      items: items,
      total: this.getTotalPrice(),
    };

    this.apiService.sendOrder(orderData).subscribe({
      next: (response) => {
        this.clearCart();
        this.rotuer.navigate(['/history']);
      },
      error: (error) => {
        console.log(error);
      },
    });
  }

  clearCart() {
    this.products = [];
    this.uploadLocalStorage();
    this.changeService.updateProductCount(0);
  }
}
