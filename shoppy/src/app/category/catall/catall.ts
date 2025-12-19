import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Apiservice } from '../../apiservice';
import { Cat } from '../../models/categories';
import { CommonModule } from '@angular/common';
import { Catservice } from '../../category/catservice';
import Vary from '../../utils/Vary';

@Component({
  selector: 'app-catall',
  imports: [CommonModule, RouterLink],
  templateUrl: './catall.html',
  styleUrl: './catall.css',
})
export class Catall implements OnInit {
  categories: Cat[] = [];
  constructor(private apiService: Apiservice, private catService: Catservice) {}

  ngOnInit() {
    this._loadCategories();
  }

  _loadCategories() {
    this.apiService.getCategories().subscribe((cts: Cat[]) => {
      // Map all categories at once with proper image URLs
      this.categories = cts.map((cat) => ({
        ...cat,
        image: Vary.getImage(cat.image),
      }));
    });
  }

  getImageUrl(imagePath: string): string {
    const url = Vary.getImage(imagePath);
    console.log('Image URL:', url);
    return url;
  }

  deleteCat(id: number) {
    this.catService.delete(id).subscribe(() => {
      this._loadCategories();
    });
  }
}
