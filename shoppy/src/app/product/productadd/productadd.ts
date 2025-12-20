import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { ProductService } from '../product-service';
import { Cat, CCat, SCat } from '../../models/categories';
import { TagModel } from '../../models/tagmodel';
import { Apiservice } from '../../apiservice';
import Vary from '../../utils/Vary';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-productadd',
  imports: [CommonModule, RouterLink, ReactiveFormsModule],
  templateUrl: './productadd.html',
  styleUrl: './productadd.css',
})
export class Productadd {
  categories: Cat[] = [];
  subcats: SCat[] = [];
  chilcats: CCat[] = [];
  tags: TagModel[] = [];

  AddForm = new FormGroup({
    name: new FormControl(''),
    price: new FormControl(0),
    // description: new FormControl(''),
    catId: new FormControl(0),
    subcatId: new FormControl(0),
    childcatId: new FormControl(0),
    tagId: new FormControl(0),
  });

  selectedFiles: File[] | null = null;
  isSubmitting = false;

  constructor(
    private apiService: Apiservice,
    private productService: ProductService,
    private router: Router
  ) {}

  ngOnInit() {
    this._loadCats();
    this._loadSubcats();
    this._loadChildcats();
    this._loadTags();
  }

  onFileSelected(event: any) {
    this.selectedFiles = event.target.files;
  }

  onSubmit() {
    if (this.AddForm.valid && this.selectedFiles) {
      this.isSubmitting = true;
      const formData = new FormData();
      formData.append('name', this.AddForm.get('name')?.value || '');
      formData.append('price', String(this.AddForm.get('price')?.value || 0));
      // formData.append('description', String(this.AddForm.get('description')?.value || ''));
      formData.append('catId', String(this.AddForm.get('catId')?.value || ''));
      formData.append('subcatId', String(this.AddForm.get('subcatId')?.value || ''));
      formData.append('childcatId', String(this.AddForm.get('childcatId')?.value || ''));
      formData.append('tagId', String(this.AddForm.get('tagId')?.value || ''));

      for (let file of this.selectedFiles) {
        formData.append('files', file);
      }

      this.productService.add(formData).subscribe({
        next: (response) => {
          this.router.navigate(['/products']);
        },
        error: (error) => {
          console.error('Error adding category: ', error);
        },
        complete: () => {
          this.isSubmitting = false;
          this.AddForm.reset();
          this.selectedFiles = null;
        },
      });
    }
  }

  _loadCats() {
    this.categories = [];
    this.apiService.getCategories().subscribe((cts) => {
      cts.forEach((cat: Cat) => {
        cat.image = Vary.getImage(cat.image);
        this.categories.push(cat);
      });
    });
  }

  _loadSubcats() {
    this.subcats = [];
    this.apiService.getSubCategories().subscribe((cts) => {
      cts.forEach((cat: SCat) => {
        cat.image = Vary.getImage(cat.image);
        this.subcats.push(cat);
      });
    });
  }

  _loadChildcats() {
    this.chilcats = [];
    this.apiService.getChildCategories().subscribe((cts) => {
      cts.forEach((cat: CCat) => {
        cat.image = Vary.getImage(cat.image);
        this.chilcats.push(cat);
      });
    });
  }

  _loadTags() {
    this.tags = [];
    this.apiService.getTags().subscribe((cts) => {
      cts.forEach((tag: TagModel) => {
        tag.image = Vary.getImage(tag.image);
        this.tags.push(tag);
      });
    });
  }
}
