import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import Vary from '../../utils/Vary';
import { CommonModule } from '@angular/common';
import { ProductModel } from '../../models/productmodel';
import { ProductService } from '../product-service';
import { Apiservice } from '../../apiservice';
import { Cat, CCat, SCat } from '../../models/categories';
import { TagModel } from '../../models/tagmodel';

@Component({
  selector: 'app-productedit',
  imports: [CommonModule, RouterLink, ReactiveFormsModule],
  templateUrl: './productedit.html',
  styleUrl: './productedit.css',
})
export class Productedit {
  categories: Cat[] = [];
  subcats: SCat[] = [];
  chilcats: CCat[] = [];
  tags: TagModel[] = [];

  EditForm = new FormGroup({
    name: new FormControl(''),
    price: new FormControl(0),
    description: new FormControl(''),
    catId: new FormControl(0),
    subcatId: new FormControl(0),
    childcatId: new FormControl(0),
    tagId: new FormControl(0),
  });

  selectedFiles: File[] | null = null;
  isSubmitting = false;
  curImage = '';
  editId: number = 0;

  constructor(
    private apiService: Apiservice,
    private productService: ProductService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.editId = Number(this.route.snapshot.paramMap.get('id'));
  }

  ngOnInit() {
    this._loadCats();
    this._loadSubcats();
    this._loadChildcats();
    this._loadTags();
    if (this.editId != null) {
      this.productService.get(this.editId).subscribe((product) => {
        this.EditForm.patchValue({ name: product.name });
        this.EditForm.patchValue({ price: product.price });
        this.EditForm.patchValue({ catId: product.category.id });
        this.EditForm.patchValue({ subcatId: product.subcat.id });
        this.EditForm.patchValue({ childcatId: product.childcat.id });
        this.EditForm.patchValue({ tagId: product.tag.id });
        this.curImage = Vary.getImage(product.images[0]) || '';
      });
    }
  }

  onFileSelected(event: any) {
    this.selectedFiles = event.target.files;
  }

  onSubmit() {
    if (this.EditForm.valid && this.selectedFiles) {
      this.isSubmitting = true;
      const formData = new FormData();
      formData.append('name', this.EditForm.get('name')?.value || '');
      formData.append('price', String(this.EditForm.get('price')?.value || 0));
      formData.append('description', String(this.EditForm.get('description')?.value || ''));
      formData.append('catId', String(this.EditForm.get('catId')?.value || ''));
      formData.append('subcatId', String(this.EditForm.get('subcatId')?.value || ''));
      formData.append('childcatId', String(this.EditForm.get('childcatId')?.value || ''));
      formData.append('tagId', String(this.EditForm.get('tagId')?.value || ''));

      for (let file of this.selectedFiles) {
        formData.append('files', file);
      }

      this.productService.update(this.editId, formData).subscribe({
        next: (response) => {
          this.router.navigate(['/products']);
        },
        error: (error) => {
          console.error('Error adding category: ', error);
        },
        complete: () => {
          this.isSubmitting = false;
          this.EditForm.reset();
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
