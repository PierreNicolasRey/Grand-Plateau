import { Component, input, output} from '@angular/core';
import { TableHeader } from '../../models/table-column.model';

@Component({
  selector: 'gp-table',
  imports: [],
  templateUrl: './table.component.html',
  styleUrl: './table.component.scss',
})
export class TableComponent <T extends Record<string, unknown>> {
  inputHeaders = input.required<TableHeader[]>();
  inputData = input.required<T[]>();
  inputActions = input<string[]>([]);

  actionTriggered = output<{action: string, row: T}>();

  public handleAction(action: string, row: T): void {
    // Do some logic here after testing
  }
}
