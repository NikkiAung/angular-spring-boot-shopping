import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Nav } from './utils/nav/nav';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Nav],
  template: '<div class="container"> <app-nav> </app-nav> <router-outlet> </router-outlet></div>'
})
export class App {
  protected readonly title = signal('shoppy');
}
