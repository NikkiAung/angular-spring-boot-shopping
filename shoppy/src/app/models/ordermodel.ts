export interface OrderItem {
  id: number;
  count: number;
  name: string;
  price: number;
  images: string[];
  productId: number;
}

export interface Order {
  id: number;
  total: number;
  buyerId: number;
  buyerName: string;
  orderItem: OrderItem[];
}

