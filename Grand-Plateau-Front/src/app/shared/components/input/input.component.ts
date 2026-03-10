import { Component, computed, input, output } from '@angular/core';

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

  idInput = computed(() => {return `id-input-${this.inputLabel()}`});

  public handleInput(event: Event) {
    const value = (event.target as HTMLInputElement).value;
    this.valueChange.emit(value);
  }
}
