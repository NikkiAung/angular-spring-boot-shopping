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

export const routes: Routes = [
  { path: '', component: Home },
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
];
