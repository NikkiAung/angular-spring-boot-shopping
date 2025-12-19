import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Catservice } from '../catservice';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-catadd',
  imports: [RouterLink, ReactiveFormsModule],
  templateUrl: './catadd.html',
  styleUrl: './catadd.css',
})
export class Catadd {
  AddForm = new FormGroup({
    name: new FormControl(''),
    image: new FormControl(''),
  });

  constructor(private catService: Catservice, private router: Router) {}

  selectedFile: File | null = null;
  isSubmitting = false;

  onFileSelected(event: any) {
    const file = event.target.files[0];
    if (file) {
      this.selectedFile = file;
      this.AddForm.patchValue({ image: file.name });
    }
  }

  onSubmit() {
    if (this.AddForm.valid && this.selectedFile) {
      this.isSubmitting = true;
      const formData = new FormData();
      formData.append('name', this.AddForm.get('name')?.value || '');
      formData.append('file', this.selectedFile);

      this.catService.add(formData).subscribe({
        next: (response) => {
          this.router.navigate(['/cats']);
        },
        error: (error) => {
          console.error('Error adding category: ', error);
        },
        complete: () => {
          this.isSubmitting = false;
          this.AddForm.reset();
          this.selectedFile = null;
        },
      });
    }
  }
}
