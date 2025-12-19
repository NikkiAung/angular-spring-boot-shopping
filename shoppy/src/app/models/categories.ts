export interface Cat {
  id: number;
  name: string;
  image: string;
  subcats: SCat[];
}

export interface SCat {
  id: number;
  name: string;
  image: string;
  catId: number;
  childcats: CCat[];
}

export interface CCat {
  id: number;
  name: string;
  image: string;
  subcatId: number;
}
