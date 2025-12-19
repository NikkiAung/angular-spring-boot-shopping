import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Apiservice } from '../../apiservice';
import { Cat } from '../../models/categories';
import { CommonModule } from '@angular/common';
import Vary from '../../utils/Vary';
import { ProductService } from '../product-service';
import { ProductModel } from '../../models/productmodel';

@Component({
  selector: 'app-productall',
  imports: [CommonModule, RouterLink],
  templateUrl: './productall.html',
  styleUrl: './productall.css',
})
export class Productall {
  products: ProductModel[] = [];
  constructor(private apiService: Apiservice, private productService: ProductService) {}

  ngOnInit() {
    this._loadCategories();
  }

  _loadCategories() {
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

  getImageUrl(imagePath: string): string {
    const url = Vary.getImage(imagePath);
    console.log('Image URL:', url);
    return url;
  }

  deleteCat(id: number) {
    this.productService.delete(id).subscribe(() => {
      this._loadCategories();
    });
  }
}
