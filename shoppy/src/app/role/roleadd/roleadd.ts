import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { RoleService } from '../role-service';

@Component({
  selector: 'app-roleadd',
  imports: [RouterLink, ReactiveFormsModule],
  templateUrl: './roleadd.html',
  styleUrl: './roleadd.css',
})
export class Roleadd {
  AddForm = new FormGroup({
    name: new FormControl(''),
  });
  isSubmitting = false;
  constructor(private roleService: RoleService, private router: Router) {}

  onSubmit() {
    if (this.AddForm.valid) {
      this.isSubmitting = true;
      const formData = new FormData();
      formData.append('name', this.AddForm.get('name')?.value || '');

      this.roleService.add(formData).subscribe({
        next: (response) => {
          this.router.navigate(['/roles']);
        },
        error: (error) => {
          console.error('Error adding category: ', error);
        },
        complete: () => {
          this.isSubmitting = false;
          this.AddForm.reset();
        },
      });
    }
  }
}
