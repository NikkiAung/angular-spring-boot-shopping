import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import Vary from '../../utils/Vary';
import { CommonModule } from '@angular/common';
import { Cat } from '../../models/categories';
import { Tagservice } from '../tagservice';

@Component({
  selector: 'app-tagedit',
  imports: [CommonModule, RouterLink, ReactiveFormsModule],
  templateUrl: './tagedit.html',
  styleUrl: './tagedit.css',
})
export class Tagedit {
  EditForm = new FormGroup({
    name: new FormControl(''),
    image: new FormControl(''),
  });

  selectedFile: File | null = null;
  isSubmitting = false;
  curImage = '';
  editId: number | null = null;

  constructor(
    private tagService: Tagservice,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.editId = Number(this.route.snapshot.paramMap.get('id'));
  }

  ngOnInit() {
    // Load existing category data to populate the form
    if (this.editId != null) {
      this.tagService.get(this.editId).subscribe({
        next: (category: Cat) => {
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
      console.log(formData.get('name'));
      console.log(formData.get('image'));
      console.log(formData.get('file'));
      this.tagService.update(this.editId, formData).subscribe({
        next: (response) => {
          this.router.navigate(['/tags']);
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
}
