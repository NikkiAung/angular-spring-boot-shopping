import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Apiservice } from '../../apiservice';
import { Cat } from '../../models/categories';
import { CommonModule } from '@angular/common';
import Vary from '../../utils/Vary';
import { RoleService } from '../role-service';
import { RoleModel } from '../../models/rolemodel';

@Component({
  selector: 'app-roleall',
  imports: [CommonModule, RouterLink],
  templateUrl: './roleall.html',
  styleUrl: './roleall.css',
})
export class Roleall {
  roles: RoleModel[] = [];
  constructor(private apiService: Apiservice, private roleService: RoleService) {}

  ngOnInit() {
    this._loadCategories();
  }

  _loadCategories() {
    this.roles = [];
    this.roleService.all().subscribe((cts) => {
      this.roles = cts;
    });
  }

  getImageUrl(imagePath: string): string {
    const url = Vary.getImage(imagePath);
    console.log('Image URL:', url);
    return url;
  }

  deleteCat(id: number) {
    this.roleService.delete(id).subscribe(() => {
      this._loadCategories();
    });
  }
}
