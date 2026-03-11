import { Component, input, output} from '@angular/core';
import { TableHeader } from '../../models/table-column.model';
import { ACTION_ICONS, ActionEnum } from '../../constants/table.constant';

@Component({
  selector: 'gp-table',
  imports: [],
  templateUrl: './table.component.html',
  styleUrl: './table.component.scss',
})
export class TableComponent <T extends Record<string, unknown>> {
  inputHeaders = input.required<TableHeader[]>();
  inputData = input.required<T[]>();
  inputActions = input<ActionEnum[]>([]);

  actionTriggered = output<{action: ActionEnum, row: T}>();

  public handleAction(action: ActionEnum, row: T): void {
    this.actionTriggered.emit({action, row});
  }

  public getActionIcon(action: ActionEnum): string {
    return ACTION_ICONS[action] || '';
  }
}
