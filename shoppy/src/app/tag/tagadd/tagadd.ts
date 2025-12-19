import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { Tagservice } from '../tagservice';
@Component({
  selector: 'app-tagadd',
  imports: [RouterLink, ReactiveFormsModule],
  templateUrl: './tagadd.html',
  styleUrl: './tagadd.css',
})
export class Tagadd {
  AddForm = new FormGroup({
    name: new FormControl(''),
    image: new FormControl(''),
  });

  constructor(private tagService: Tagservice, private router: Router) {}

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

      this.tagService.add(formData).subscribe({
        next: (response) => {
          this.router.navigate(['/tags']);
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
