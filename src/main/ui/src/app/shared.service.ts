import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SharedService {

  private itemsSource = new BehaviorSubject<any>([]);
  items$ = this.itemsSource.asObservable();

  updateItems(items: any) {
    this.itemsSource.next(items);
    console.log('itemSource updated: ', this.itemsSource);
  }
}