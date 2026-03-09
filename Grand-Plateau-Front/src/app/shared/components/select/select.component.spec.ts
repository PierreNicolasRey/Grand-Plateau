import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectComponent } from './select.component';
import { ItemOption } from '../../models/item-option.model';

describe('SelectComponent', () => {
  let component: SelectComponent;
  let fixture: ComponentFixture<SelectComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SelectComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SelectComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  describe('Initialization', () => {
    it('should create', () => {
      expect(component).toBeTruthy();
    });
  });

  it('should include "Tous" option when includeAllOption is true', () => {
    const mockOptions: ItemOption[] = [{ value: '1', label: 'Option 1' }];
    
    fixture.componentRef.setInput('inputOptions', mockOptions);
    fixture.componentRef.setInput('includeAllOption', true);
    fixture.detectChanges();

    const options = component.finalOptions();
    expect(options.length).toBe(2);
    expect(options[0].label).toBe('Tous');
    expect(options[0].value).toBe('');
  });

  it('should not include extra options when includeAllOption is false', () => {
    const mockOptions: ItemOption[] = [{ value: '1', label: 'Option 1' }];
    
    fixture.componentRef.setInput('inputOptions', mockOptions);
    fixture.componentRef.setInput('includeAllOption', false);
    fixture.detectChanges();

    const options = component.finalOptions();
    expect(options.length).toBe(1);
    expect(options[0].label).toBe('Option 1');
  });
  
});
