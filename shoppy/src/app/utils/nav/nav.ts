import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { ChangeService } from '../../change-service';

@Component({
  selector: 'app-nav',
  imports: [RouterLink],
  templateUrl: './nav.html',
  styleUrl: './nav.css',
})
export class Nav {
  productCount: number = 0;
  constructor(private changeService: ChangeService) {}

  ngOnInit() {
    this.changeService.productCounts.subscribe((count) => {
      this.productCount = count;
    });
  }
}
