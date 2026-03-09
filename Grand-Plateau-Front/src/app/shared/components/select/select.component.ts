import { Component, input, signal } from '@angular/core';
import { ItemOption } from '../../models/item-option.model';

@Component({
  selector: 'gp-select-component',
  imports: [],
  templateUrl: './select.component.html',
  styleUrl: './select.component.scss',
})
export class SelectComponent {
  inputOptions = input.required<ItemOption[]>();
  includeAllOption = input<boolean>(false);

  finalOptions = signal<ItemOption[]>([]);

}
