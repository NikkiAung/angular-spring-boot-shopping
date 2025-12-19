import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Apiservice } from '../../apiservice';
import { CCat } from '../../models/categories';
import { CommonModule } from '@angular/common';
import Vary from '../../utils/Vary';
import { ChildcatService } from '../childcat-service';

@Component({
  selector: 'app-childcatall',
  imports: [CommonModule, RouterLink],
  templateUrl: './childcatall.html',
  styleUrl: './childcatall.css',
})
export class Childcatall {
  categories: CCat[] = [];
  constructor(private apiService: Apiservice, private service: ChildcatService) {}

  ngOnInit() {
    this._loadCategories();
  }

  _loadCategories() {
    this.apiService.getChildCategories().subscribe((cts: CCat[]) => {
      // Map all categories at once with proper image URLs
      this.categories = cts.map((cat) => ({
        ...cat,
        image: Vary.getImage(cat.image),
      }));
      console.log('Categories loaded:', this.categories);
    });
  }

  getImageUrl(imagePath: string): string {
    const url = Vary.getImage(imagePath);
    console.log('Image URL:', url);
    return url;
  }

  deleteCat(id: number) {
    this.service.delete(id).subscribe(() => {
      this._loadCategories();
    });
  }
}
