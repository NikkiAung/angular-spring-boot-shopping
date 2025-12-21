import { RoleModel } from './rolemodel';

export interface UserModel {
  id: number;
  name: string;
  phone: string;
  roles: RoleModel[];
}
