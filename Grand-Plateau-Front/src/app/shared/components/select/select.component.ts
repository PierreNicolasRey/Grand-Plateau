import { Component, computed, input, output } from '@angular/core';
import { ItemOption } from '../../models/item-option.model';

@Component({
  selector: 'gp-select-component',
  imports: [],
  templateUrl: './select.component.html',
  styleUrl: './select.component.scss',
})
export class SelectComponent {
  // TODO : Custom dropdown component
  inputOptions = input.required<ItemOption[]>();
  includeAllOption = input<boolean>(false);
  inputLabel = input.required<string>();

  selectedValueChange = output<string>();

  idSelect = computed(() => { return `id-select-${this.inputLabel()}`; });

  finalOptions = computed(() => {
    const options = [...this.inputOptions()];

    if (this.includeAllOption()) {
      options.unshift({ label: 'Tous', value: '' });
    }

    return options;
  });

  public handleSelectionChange(event: Event): void {
    if (event) {
      const target = event.target as HTMLSelectElement;
      this.selectedValueChange.emit(target.value);
    }
  }
}
