import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import Vary from '../../utils/Vary';
import { CommonModule } from '@angular/common';
import { ProductModel } from '../../models/productmodel';
import { ProductService } from '../product-service';

@Component({
  selector: 'app-productedit',
  imports: [CommonModule, RouterLink, ReactiveFormsModule],
  templateUrl: './productedit.html',
  styleUrl: './productedit.css',
})
export class Productedit {
  EditForm = new FormGroup({
    name: new FormControl(''),
    image: new FormControl(''),
  });

  selectedFile: File | null = null;
  isSubmitting = false;
  curImage = '';
  editId: number | null = null;

  constructor(
    private productService: ProductService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.editId = Number(this.route.snapshot.paramMap.get('id'));
  }

  ngOnInit() {
    // Load existing product data to populate the form
    if (this.editId != null) {
      this.productService.get(this.editId).subscribe({
        next: (product: ProductModel) => {
          // Fill form with existing product data
          this.EditForm.patchValue({
            name: product.name,
            image: product.images && product.images.length > 0 ? product.images[0] : '', // This is just for form state, not displayed
          });
          // Display current image - check if images array exists and has at least one image
          if (product.images && product.images.length > 0) {
            this.curImage = Vary.getImage(product.images[0]) || '';
          }
          console.log('Product loaded for editing:', product);
        },
        error: (error) => {
          console.error('Error loading product:', error);
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
      console.log(formData.get('name'));
      console.log(formData.get('image'));
      console.log(formData.get('file'));
      this.productService.update(this.editId, formData).subscribe({
        next: (response) => {
          this.router.navigate(['/cats']);
        },
        error: (error) => {
          console.error('Error updating product: ', error);
          this.isSubmitting = false;
        },
        complete: () => {
          this.isSubmitting = false;
        },
      });
    }
  }
}
