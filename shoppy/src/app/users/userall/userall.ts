import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Apiservice } from '../../apiservice';
import { Cat } from '../../models/categories';
import { CommonModule } from '@angular/common';
import { Catservice } from '../../category/catservice';
import Vary from '../../utils/Vary';
import { UserModel } from '../../models/usermodel';
import { Userservice } from '../userservice';

@Component({
  selector: 'app-userall',
  imports: [CommonModule, RouterLink],
  templateUrl: './userall.html',
  styleUrl: './userall.css',
})
export class Userall {
  users: UserModel[] = [];
  constructor(private userService: Userservice) {}

  ngOnInit() {
    this._loadUsers();
  }

  _loadUsers() {
    this.users = [];
    this.userService.all().subscribe((cts) => {
      this.users = cts;
    });
  }

  getImageUrl(imagePath: string): string {
    const url = Vary.getImage(imagePath);
    console.log('Image URL:', url);
    return url;
  }
}
