import { Routes } from '@angular/router';
import { Home } from './home/home';
import { Category } from './category/category';
import { Subcat } from './subcat/subcat';
import { Childcat } from './childcat/childcat';
import { Catall } from './category/catall/catall';
import { Catadd } from './category/catadd/catadd';
import { Catedit } from './category/catedit/catedit';
import { Subcatedit } from './subcat/subcatedit/subcatedit';
import { Subcatadd } from './subcat/subcatadd/subcatadd';
import { Subcatall } from './subcat/subcatall/subcatall';
import { Childcatall } from './childcat/childcatall/childcatall';
import { Childcatadd } from './childcat/childcatadd/childcatadd';
import { Childcatedit } from './childcat/childcatedit/childcatedit';
import { Tag } from './tag/tag';
import { Tagall } from './tag/tagall/tagall';
import { Tagadd } from './tag/tagadd/tagadd';
import { Tagedit } from './tag/tagedit/tagedit';
import { Product } from './product/product';
import { Productall } from './product/productall/productall';
import { Productadd } from './product/productadd/productadd';
import { Productedit } from './product/productedit/productedit';
import { Role } from './role/role';
import { Roleall } from './role/roleall/roleall';
import { Roleadd } from './role/roleadd/roleadd';
import { Roleedit } from './role/roleedit/roleedit';
import { Userall } from './users/userall/userall';
import { Useredit } from './users/useredit/useredit';
import { Login } from './login/login';
import { Register } from './register/register';
import { Cartpage } from './cartpage/cartpage';

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'login', component: Login },
  { path: 'register', component: Register },
  { path: 'cart', component: Cartpage },
  {
    path: 'cats',
    component: Category,
    children: [
      { path: '', component: Catall },
      { path: 'add', component: Catadd },
      { path: 'edit/:id', component: Catedit },
    ],
  },
  {
    path: 'subcats',
    component: Subcat,
    children: [
      { path: '', component: Subcatall },
      { path: 'add', component: Subcatadd },
      { path: 'edit/:id', component: Subcatedit },
    ],
  },
  {
    path: 'childcats',
    component: Childcat,
    children: [
      { path: '', component: Childcatall },
      { path: 'add', component: Childcatadd },
      { path: 'edit/:id', component: Childcatedit },
    ],
  },
  {
    path: 'tags',
    component: Tag,
    children: [
      { path: '', component: Tagall },
      { path: 'add', component: Tagadd },
      { path: 'edit/:id', component: Tagedit },
    ],
  },
  {
    path: 'products',
    component: Product,
    children: [
      { path: '', component: Productall },
      { path: 'add', component: Productadd },
      { path: 'edit/:id', component: Productedit },
    ],
  },
  {
    path: 'roles',
    component: Role,
    children: [
      { path: '', component: Roleall },
      { path: 'add', component: Roleadd },
      { path: 'edit/:id', component: Roleedit },
    ],
  },
  {
    path: 'users',
    component: Role,
    children: [
      { path: '', component: Userall },
      // { path: 'add', component: Useredit },
      { path: 'edit/:id', component: Useredit },
    ],
  },
];
