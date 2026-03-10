import { Component, input, output } from '@angular/core';

@Component({
  selector: 'gp-input',
  imports: [],
  templateUrl: './input.component.html',
  styleUrl: './input.component.scss',
})
export class InputComponent {
  inputLabel = input.required<string>();
  isAutoComplete = input<boolean>(false);

  valueChange = output<string>();
}
