import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import Vary from '../../utils/Vary';
import { CommonModule } from '@angular/common';
import { Cat, SCat } from '../../models/categories';
import { Apiservice } from '../../apiservice';
import { ChildcatService } from '../childcat-service';

@Component({
  selector: 'app-childcatedit',
  imports: [CommonModule, RouterLink, ReactiveFormsModule],
  templateUrl: './childcatedit.html',
  styleUrl: './childcatedit.css',
})
export class Childcatedit {
  EditForm = new FormGroup({
    name: new FormControl(''),
    image: new FormControl(''),
    catId: new FormControl(0),
  });

  selectedFile: File | null = null;
  isSubmitting = false;
  curImage = '';
  editId: number | null = null;

  cats: SCat[] = [];

  constructor(
    private service: ChildcatService,
    private router: Router,
    private route: ActivatedRoute,
    private apiService: Apiservice
  ) {
    this.editId = Number(this.route.snapshot.paramMap.get('id'));
  }

  ngOnInit() {
    this._loadCategories();
    if (this.editId != null) {
      this.service.get(this.editId).subscribe({
        next: (category: SCat) => {
          // Fill form with existing category data
          this.EditForm.patchValue({
            name: category.name,
            image: category.image, // This is just for form state, not displayed
          });
          // Display current image
          this.curImage = Vary.getImage(category.image) || '';
          console.log('Category loaded for editing:', category);
        },
        error: (error) => {
          console.error('Error loading category:', error);
        },
      });
    }
  }

  onFileSelected(event: any) {
    const file = event.target.files[0];
    console.log('onFileSelected', file);
    if (file) {
      this.selectedFile = file;
      this.EditForm.patchValue({ image: file.name });
      console.log('EditForm', this.EditForm.value);
    }
  }

  onSubmit() {
    if (this.editId && this.EditForm.valid) {
      this.isSubmitting = true;
      const formData = new FormData();
      formData.append('name', this.EditForm.get('name')?.value || '');

      // Only append file if a new one was selected
      if (this.selectedFile) {
        formData.append('file', this.selectedFile);
      }

      this.service.update(this.editId, formData).subscribe({
        next: (response) => {
          this.router.navigate(['/subcats']);
        },
        error: (error) => {
          console.error('Error updating category: ', error);
          this.isSubmitting = false;
        },
        complete: () => {
          this.isSubmitting = false;
        },
      });
    }
  }

  _loadCategories() {
    this.apiService.getSubCategories().subscribe((cts: SCat[]) => {
      cts.forEach((cat: SCat) => {
        cat.image = Vary.getImage(cat.image);
        this.cats.push(cat);
      });
    });
    console.log('loading cats', this.cats);
  }
}
