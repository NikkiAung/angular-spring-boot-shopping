import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { ProductService } from '../product-service';

@Component({
  selector: 'app-productadd',
  imports: [RouterLink, ReactiveFormsModule],
  templateUrl: './productadd.html',
  styleUrl: './productadd.css',
})
export class Productadd {
  AddForm = new FormGroup({
    name: new FormControl(''),
    price: new FormControl(0),
    description: new FormControl(0),
    catId: new FormControl(0),
    subcatId: new FormControl(0),
    childcatId: new FormControl(0),
    tagId: new FormControl(0),
  });

  constructor(private productService: ProductService, private router: Router) {}

  selectedFiles: File[] | null = null;
  isSubmitting = false;

  onFileSelected(event: any) {
    this.selectedFiles = event.target.files;
  }

  onSubmit() {
    if (this.AddForm.valid && this.selectedFiles) {
      this.isSubmitting = true;
      const formData = new FormData();
      formData.append('name', this.AddForm.get('name')?.value || '');
      formData.append('price', String(this.AddForm.get('price')?.value || 0));
      formData.append('description', String(this.AddForm.get('description')?.value || ''));
      formData.append('catId', String(this.AddForm.get('catId')?.value || ''));
      formData.append('subcatId', String(this.AddForm.get('subcatId')?.value || ''));
      formData.append('childcatId', String(this.AddForm.get('childcatId')?.value || ''));
      formData.append('tagId', String(this.AddForm.get('tagId')?.value || ''));

      for (let file of this.selectedFiles) {
        formData.append('files', file);
      }

      this.productService.add(formData).subscribe({
        next: (response) => {
          this.router.navigate(['/cats']);
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
}
