import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'gp-main-menu-component',
  imports: [RouterLink],
  templateUrl: './main-menu-component.html',
  styleUrl: './main-menu-component.scss',
})
export class MainMenuComponent {

  menuItems = [
    { label: 'COUREURS', link: '/coureurs' },
    { label: 'ÉQUIPES', link: '/equipes' },
    { label: 'COURSES', link: '/courses' },
    { label: 'ADMINISTRATION', link: '/administration' }
  ];
}
