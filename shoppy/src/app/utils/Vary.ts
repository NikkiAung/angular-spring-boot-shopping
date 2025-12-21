class Vary {
  static readonly BASE_URL = 'http://localhost:3000';
  static readonly API_URL = 'http://localhost:3000/api';
  static readonly CAT_URL = 'http://localhost:3000/admin/cats';
  static readonly SUBCAT_URL = 'http://localhost:3000/admin/subcats';
  static readonly CHILDCAT_URL = 'http://localhost:3000/admin/childcats';
  static readonly TAG_URL = 'http://localhost:3000/admin/tags';
  static readonly PRODUCT_URL = 'http://localhost:3000/admin/products';
  static readonly ROLE_URL = 'http://localhost:3000/admin/roles';
  static readonly USERS_URL = 'http://localhost:3000/admin/users';
  static getImage = (name: string) => Vary.BASE_URL + '/images/' + name;
}

export default Vary;
