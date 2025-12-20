import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { RoleService } from '../role-service';

@Component({
  selector: 'app-roleedit',
  imports: [CommonModule, RouterLink, ReactiveFormsModule],
  templateUrl: './roleedit.html',
  styleUrl: './roleedit.css',
})
export class Roleedit {
  EditForm = new FormGroup({
    name: new FormControl(''),
    image: new FormControl(''),
  });

  isSubmitting = false;
  editId: number | null = null;

  constructor(
    private roleService: RoleService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.editId = Number(this.route.snapshot.paramMap.get('id'));
  }

  ngOnInit() {
    // Load existing category data to populate the form
    if (this.editId != null) {
      this.roleService.get(this.editId).subscribe((role) => {
        this.EditForm.patchValue(role);
      });
    }
  }

  onSubmit() {
    if (this.editId && this.EditForm.valid) {
      this.isSubmitting = true;
      const formData = new FormData();
      formData.append('name', this.EditForm.get('name')?.value || '');

      this.roleService.update(this.editId, formData).subscribe({
        next: (response) => {
          this.router.navigate(['/roles']);
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
