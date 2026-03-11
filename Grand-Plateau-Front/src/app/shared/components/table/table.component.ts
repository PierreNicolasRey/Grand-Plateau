import { Component, computed, input, output, signal } from '@angular/core';
import { TableHeader } from '../../models/table-column.model';
import { ACTION_ICONS, ActionEnum } from '../../constants/table.constant';

@Component({
  selector: 'gp-table',
  imports: [],
  templateUrl: './table.component.html',
  styleUrl: './table.component.scss',
})
export class TableComponent<T extends Record<string, unknown>> {
  inputHeaders = input.required<TableHeader[]>();
  inputData = input.required<T[]>();
  inputActions = input<ActionEnum[]>([]);

  actionTriggered = output<{ action: ActionEnum, row: T }>();

  public sortState = signal<{ key: string, direction: 'asc' | 'desc' }>({
    key: '',
    direction: 'desc'
  });

  public sortedData = computed(() => {
    const data = [...this.inputData()];
    const { key, direction } = this.sortState();

    if (!key) return data;

    return data.sort((a, b) => {
      const aValue = String(a[key]);
      const bValue = String(b[key]);

      return direction === 'desc'
        ? aValue.localeCompare(bValue)
        : bValue.localeCompare(aValue);
    });
  });

  public handleAction(action: ActionEnum, row: T): void {
    this.actionTriggered.emit({ action, row });
  }

  public getActionIcon(action: ActionEnum): string {
    return ACTION_ICONS[action];
  }

  public handleSort(columnKey: string): void {
    const currentState = this.sortState();

    if (currentState.key === columnKey) {
      this.sortState.set({
        key: columnKey,
        direction: currentState.direction === 'asc' ? 'desc' : 'asc'
      });
    } else {
      this.sortState.set({
        key: columnKey,
        direction: 'asc'
      });
    }
  }

  public getSortIcon(columnKey: string): string {
    const state = this.sortState();

    if (state.key !== columnKey) {
      return ACTION_ICONS[ActionEnum.TRIER_BAS];
    }

    return state.direction === 'asc' ? ACTION_ICONS[ActionEnum.TRIER_HAUT] : ACTION_ICONS[ActionEnum.TRIER_BAS];
  }
}
