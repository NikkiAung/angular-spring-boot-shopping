import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ChangeService {
  private productCountSubject = new BehaviorSubject<number>(0);

  get productCounts(): Observable<number> {
    return this.productCountSubject.asObservable();
  }

  updateProductCount(count: number): void {
    this.productCountSubject.next(count);
  }
}
