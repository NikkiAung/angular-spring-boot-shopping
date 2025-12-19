import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Apiservice } from '../../apiservice';
import { SCat } from '../../models/categories';
import { CommonModule } from '@angular/common';
import Vary from '../../utils/Vary';
import { Subcatservice } from '../subcatservice';

@Component({
  selector: 'app-subcatall',
  imports: [CommonModule, RouterLink],
  templateUrl: './subcatall.html',
  styleUrl: './subcatall.css',
})
export class Subcatall {
  categories: SCat[] = [];
  constructor(private apiService: Apiservice, private subcatService: Subcatservice) {}

  ngOnInit() {
    this._loadCategories();
  }

  _loadCategories() {
    this.apiService.getSubCategories().subscribe((cts: SCat[]) => {
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
    this.subcatService.delete(id).subscribe(() => {
      this._loadCategories();
    });
  }
}
