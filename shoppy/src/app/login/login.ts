import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { Apiservice } from '../apiservice';

@Component({
  selector: 'app-login',
  imports: [RouterLink, ReactiveFormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  LoginForm = new FormGroup({
    name: new FormControl(''),
    phone: new FormControl(''),
    password: new FormControl(''),
  });

  constructor(private apiService: Apiservice, private router: Router) {}

  isSubmitting = false;

  onSubmit() {
    if (this.LoginForm.valid) {
      this.isSubmitting = true;

      this.apiService.login(this.LoginForm.value).subscribe({
        next: (response) => {
          console.log(response.token);
          localStorage.setItem('token', response.token);
          this.router.navigate(['/']);
        },
        error: (error) => {
          console.error('Login Error: ', error);
        },
        complete: () => {
          this.isSubmitting = false;
          this.LoginForm.reset();
        },
      });
    }
  }
}
