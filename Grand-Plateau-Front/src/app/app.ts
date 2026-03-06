import { Component } from '@angular/core';
import { MainMenuComponent } from "./shared/components/main-menu/main-menu.component";
import { RouterOutlet } from '@angular/router';
import { FilArianeComponent } from "./shared/components/fil-ariane/fil-ariane.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, MainMenuComponent, FilArianeComponent],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
}
