import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectComponent } from './select.component';
import { ItemOption } from '../../models/item-option.model';

describe('SelectComponent', () => {
  let component: SelectComponent;
  let fixture: ComponentFixture<SelectComponent>;

  const mockOptions: ItemOption[] = [{ value: '1', label: 'Option 1' }];

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SelectComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SelectComponent);
    component = fixture.componentInstance;
    
    fixture.componentRef.setInput('inputOptions', mockOptions);
    fixture.componentRef.setInput('includeAllOption', false);
    fixture.componentRef.setInput('inputLabel', 'Test');

    fixture.detectChanges();
  });

  describe('Initialization', () => {
    it('should create', () => {
      expect(component).toBeTruthy();
    });

    it('should include "Tous" option when includeAllOption is true', () => {
      // ACT
      fixture.componentRef.setInput('includeAllOption', true);
      fixture.detectChanges();

      // ASSERT
      const options = component.finalOptions();
      expect(options.length).toEqual(2);
      expect(options[0].label).toEqual('Tous');
      expect(options[0].value).toEqual('');
    });

    it('should not include extra options when includeAllOption is false', () => {
      // component already initialized with includeAllOption = false

      // ASSERT
      const options = component.finalOptions();
      expect(options.length).toEqual(1);
      expect(options[0].label).toEqual('Option 1');
    });
  });

  describe('Event emission', () => {
    it('should emit a value when the user selects an option', () => {
      // ARRANGE
      let emittedValue: string | undefined;

      component.selectedValueChange.subscribe((value) => {
        emittedValue = value;
      });

      const selectElement: HTMLSelectElement = fixture.nativeElement.querySelector('select');

      // ACT
      selectElement.value = '1';
      selectElement.dispatchEvent(new Event('change'));
      fixture.detectChanges();

      // ASSERT
      expect(emittedValue).toEqual('1');
    });
  });
});
