import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { Apiservice } from '../apiservice';

@Component({
  selector: 'app-register',
  imports: [RouterLink, ReactiveFormsModule],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {
  RegisterForm = new FormGroup({
    name: new FormControl(''),
    phone: new FormControl(''),
    password: new FormControl(''),
  });

  constructor(private apiService: Apiservice, private router: Router) {}

  isSubmitting = false;

  onSubmit() {
    if (this.RegisterForm.valid) {
      this.isSubmitting = true;

      this.apiService.register(this.RegisterForm.value).subscribe({
        next: (response) => {
          this.router.navigate(['/login']);
        },
        error: (error) => {
          console.error('Registration Error: ', error);
        },
        complete: () => {
          this.isSubmitting = false;
          this.RegisterForm.reset();
        },
      });
    }
  }
}
