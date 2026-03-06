import { Component } from '@angular/core';
import { MainMenuComponent } from "./shared/components/main-menu/main-menu.component";
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, MainMenuComponent],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
}
