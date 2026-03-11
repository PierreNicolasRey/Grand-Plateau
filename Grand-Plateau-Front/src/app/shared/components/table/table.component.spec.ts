import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TableComponent } from './table.component';
import { TableHeader } from '../../models/table-column.model';
import { ActionEnum } from '../../constants/table.constant';

describe('TableComponent', () => {
  let component: TableComponent<Record<string, string>>;
  let fixture: ComponentFixture<TableComponent<Record<string, string>>>;

  const mockHeaders: TableHeader[] = [
    {key: 'c1', label: 'Column 1'},
    {key: 'c2', label: 'Column 2'},
    {key: 'c3', label: 'Column 3'}
  ];
  const mockData = [
    {c1:'ValueA1', c2:'ValueA2', c3: 'ValueA3'},
    {c1:'ValueB1', c2:'ValueB2', c3: 'ValueB3'}
  ];

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TableComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TableComponent<Record<string, string>>);
    component = fixture.componentInstance;

    fixture.componentRef.setInput('inputHeaders', mockHeaders);
    fixture.componentRef.setInput('inputData', mockData);

    fixture.detectChanges();
  });

  describe('Component Initalization', () => {
    it('should create', () => {
      expect(component).toBeTruthy();
    });

    it('should render correct number of columns and rows', () => {
      // ASSERT - Columns
      const headers = fixture.nativeElement.querySelectorAll('th');
      expect(headers.length).toBe(3);
      expect(headers[0].textContent).toContain('Column 1');
      
      // ASSERT - Rows
      const rows = fixture.nativeElement.querySelectorAll('tbody tr');
      expect(rows.length).toBe(2);
    });

    it('should render empty cell when missing data for a column', () => {
      // ARRANGE
      fixture.componentRef.setInput('inputHeaders', [{ key: 'c1', label: 'Column 1' }]);
      fixture.componentRef.setInput('inputData', [{ c1: '' }]);
      fixture.detectChanges();

      // ASSERT
      const cell = fixture.nativeElement.querySelector('td');
      expect(cell.textContent.trim()).toBe('');
    });

    it('should render correct picto for each possible actions', () => {
      // ARRANGE
      const mockHeaders = [{key: 'c1', label: 'Column 1'}, {key: 'actions', label: ''}];
      const mockData = [{c1: 'Value1'}];
      const mockActions = [ActionEnum.CONSULTER, ActionEnum.MODIFIER, ActionEnum.SUPPRIMER];

      fixture.componentRef.setInput('inputHeaders', mockHeaders);
      fixture.componentRef.setInput('inputData', mockData);
      fixture.componentRef.setInput('inputActions', mockActions);
      fixture.detectChanges();

      const icons: HTMLElement[] = fixture.nativeElement.querySelectorAll('button i');

      // ASSERT
      expect(icons.length).toEqual(3);
      expect(icons[0]?.className).toContain('pi-eye');
      expect(icons[1]?.className).toContain('pi-pencil');
      expect(icons[2]?.className).toContain('pi-trash');
    });
  });

  describe('Event Emission', () => {
    it('should emit corresponding action with selected row on click on action button', () => {
      // ARRANGE
      const mockHeaders = [{key: 'c1', label: 'Column 1'}, {key: 'actions', label: ''}];
      const mockData = [{c1: 'Value1'}];
      const mockActions = ['consulter'];

      fixture.componentRef.setInput('inputHeaders', mockHeaders);
      fixture.componentRef.setInput('inputData', mockData);
      fixture.componentRef.setInput('inputActions', mockActions);
      fixture.detectChanges();

      let emittedValue: unknown;
      component.actionTriggered.subscribe((value) => {
        emittedValue = value;
      });

      const buttonElement: HTMLButtonElement = fixture.nativeElement.querySelector('button');

      // ACT
      buttonElement.click();
      fixture.detectChanges();

      // ASSERT
      expect(emittedValue).toEqual({action: 'consulter', row: {c1: 'Value1'}});
    });
  });
});
