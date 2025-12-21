import { Component } from '@angular/core';
import { RoleModel } from '../../models/rolemodel';
import { Userservice } from '../userservice';
import { RoleService } from '../../role/role-service';
import { ActivatedRoute } from '@angular/router';
import { UserModel } from '../../models/usermodel';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-useredit',
  imports: [CommonModule],
  templateUrl: './useredit.html',
  styleUrl: './useredit.css',
})
export class Useredit {
  roles: RoleModel[] = [];
  userId = 0;
  user: UserModel | null = null;
  constructor(
    private userService: Userservice,
    private roleService: RoleService,
    private route: ActivatedRoute
  ) {
    this.userId = this.route.snapshot.params['id'];
  }

  ngOnInit() {
    this._loadRoles();
  }

  addRole(roleId: number) {
    this.userService.addRole(this.userId, roleId).subscribe(() => {
      this._loadRoles();
    });
  }

  removeRole(roleId: number) {
    this.userService.removeRole(this.userId, roleId).subscribe(() => {
      this._loadRoles();
    });
  }

  _loadRoles() {
    this.roles = [];
    this.roleService.all().subscribe((cts) => {
      this.roles = cts;
      this._loadUser();
    });
  }

  _loadUser() {
    this.userService.get(this.userId).subscribe((user) => {
      this.user = user;
      this.user.roles.forEach((userRole) => {
        this.roles = this.roles.filter((r) => r.id !== userRole.id);
      });
    });
  }
}
