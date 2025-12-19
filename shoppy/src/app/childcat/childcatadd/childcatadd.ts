import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { Apiservice } from '../../apiservice';
import { SCat } from '../../models/categories';
import Vary from '../../utils/Vary';
import { CommonModule } from '@angular/common';
import { ChildcatService } from '../childcat-service';

@Component({
  selector: 'app-childcatadd',
  imports: [CommonModule, RouterLink, ReactiveFormsModule],
  templateUrl: './childcatadd.html',
  styleUrl: './childcatadd.css',
})
export class Childcatadd {
  AddForm = new FormGroup({
    name: new FormControl(''),
    image: new FormControl(''),
    catId: new FormControl(0),
  });

  selectedFile: File | null = null;
  isSubmitting = false;
  cats: SCat[] = [];

  constructor(
    private service: ChildcatService,
    private apiService: Apiservice,
    private router: Router
  ) {}

  ngOnInit() {
    console.log('loading');
    this._loadCategories();
  }

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

      this.service.add(formData).subscribe({
        next: (response) => {
          this.router.navigate(['/childcats']);
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

  _loadCategories() {
    this.apiService.getSubCategories().subscribe((cts) => {
      cts.forEach((cat: SCat) => {
        cat.image = Vary.getImage(cat.image);
        this.cats.push(cat);
      });
    });
  }
}
