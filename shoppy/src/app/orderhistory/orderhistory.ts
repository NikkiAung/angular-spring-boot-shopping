import { Component } from '@angular/core';
import { Apiservice } from '../apiservice';
import { Order } from '../models/ordermodel';
import { CommonModule } from '@angular/common';
import Vary from '../utils/Vary';

@Component({
  selector: 'app-orderhistory',
  imports: [CommonModule],
  templateUrl: './orderhistory.html',
  styleUrl: './orderhistory.css',
})
export class Orderhistory {
  orderHistories: Order[] = [];
  constructor(private apiService: Apiservice) {}
  ngOnInit() {
    this.apiService.getMyOrders().subscribe(
      (response) => {
        this.orderHistories = response as Order[];
      },
      (error) => {
        console.log(error);
      }
    );
  }

  getImage(image: string) {
    return Vary.getImage(image);
  }
}
