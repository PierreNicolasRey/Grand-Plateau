import { Component, signal } from '@angular/core';
import { FilArianeStep } from '../../models/fil-ariane-steps.model';

@Component({
  selector: 'gp-fil-ariane.component',
  imports: [],
  templateUrl: './fil-ariane.component.html',
  styleUrl: './fil-ariane.component.scss',
})
export class FilArianeComponent {
  filAriane = signal<FilArianeStep[]>([]);

}
