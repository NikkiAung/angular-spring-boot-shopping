import { Component } from '@angular/core';
import { ProductModel } from '../models/productmodel';
import { Apiservice } from '../apiservice';
import Vary from '../utils/Vary';
import { CommonModule } from '@angular/common';
import { DBProductModel } from '../models/dbproductmodel';
import { ChangeService } from '../change-service';

@Component({
  selector: 'app-home',
  imports: [CommonModule],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {
  products: ProductModel[] = [];
  constructor(private apiService: Apiservice, private changeService: ChangeService) {}

  ngOnInit() {
    this._loadProducts();
  }

  _loadProducts() {
    this.products = [];
    this.apiService.getProducts().subscribe((cts) => {
      cts.forEach((product: ProductModel) => {
        let images: string[] = [];
        product.images.forEach((img) => {
          images.push(Vary.getImage(img));
        });
        product.images = images;
        this.products.push(product);
      });
    });
  }

  addToCart(product: ProductModel) {
    let dbProductsStr = localStorage.getItem('products') || '[]';
    let dbProducts: DBProductModel[] = JSON.parse(dbProductsStr);
    let existingProduct = dbProducts.find((p: DBProductModel) => p.id === product.id);

    if (existingProduct) {
      existingProduct.count++;
    } else {
      let newProduct: DBProductModel = {
        id: product.id,
        name: product.name,
        price: product.price,
        images: product.images,
        count: 1,
      };
      dbProducts.push(newProduct);
    }

    localStorage.setItem('products', JSON.stringify(dbProducts));
    this.changeService.updateProductCount(dbProducts.length);
  }
}
