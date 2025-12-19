class Vary {
  static readonly BASE_URL = 'http://localhost:3000';
  static readonly API_URL = 'http://localhost:3000/api';
  static readonly CAT_URL = 'http://localhost:3000/admin/cats';
  static readonly SUBCAT_URL = 'http://localhost:3000/admin/subcats';
  static readonly CHILDCAT_URL = 'http://localhost:3000/admin/childcats';
  static getImage = (name: string) => Vary.BASE_URL + '/images/' + name;
}

export default Vary;
