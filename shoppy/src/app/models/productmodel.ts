import { Cat, CCat, SCat } from './categories';
import { TagModel } from './tagmodel';

export interface ProductModel {
  id: number;
  name: string;
  price: number;
  images: string[];
  category: Cat;
  subcat: SCat;
  childcat: CCat;
  tag: TagModel;
}
