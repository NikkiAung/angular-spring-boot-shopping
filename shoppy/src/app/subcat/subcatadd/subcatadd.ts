import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { Subcatservice } from '../subcatservice';
import { Apiservice } from '../../apiservice';
import { Cat } from '../../models/categories';
import Vary from '../../utils/Vary';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-subcatadd',
  imports: [CommonModule, RouterLink, ReactiveFormsModule],
  templateUrl: './subcatadd.html',
  styleUrl: './subcatadd.css',
})
export class Subcatadd {
  AddForm = new FormGroup({
    name: new FormControl(''),
    image: new FormControl(''),
    catId: new FormControl(0),
  });

  selectedFile: File | null = null;
  isSubmitting = false;
  cats: Cat[] = [];

  constructor(
    private subcatService: Subcatservice,
    private apiService: Apiservice,
    private router: Router
  ) {}

  ngOnInit() {
    console.log('loading');
    this._loadCategories();
    // console.log(this.cats);
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

      this.subcatService.add(formData).subscribe({
        next: (response) => {
          this.router.navigate(['/subcats']);
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
    this.apiService.getCategories().subscribe((cts: Cat[]) => {
      cts.forEach((cat: Cat) => {
        cat.image = Vary.getImage(cat.image);
        this.cats.push(cat);
      });
    });
    console.log('loading cats', this.cats);
  }
}
