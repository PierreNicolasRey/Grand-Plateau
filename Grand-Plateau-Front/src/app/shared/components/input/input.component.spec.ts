import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InputComponent } from './input.component';

describe('InputComponent', () => {
  let component: InputComponent;
  let fixture: ComponentFixture<InputComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InputComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InputComponent);
    component = fixture.componentInstance;

    fixture.componentRef.setInput('inputLabel', 'Test');

    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
    expect(component.inputLabel()).toEqual('Test');
  });

  describe('Event emission', () => {
    it('should emit value when user input a new value', () => {
      // ARRANGE
      let emittedValue: string | undefined;

      component.valueChange.subscribe((value) => {
        emittedValue = value;
      });

      const inputElement: HTMLInputElement = fixture.nativeElement.querySelector('input');

      // ACT
      inputElement.value = 'test';
      inputElement.dispatchEvent(new Event('input'));
      fixture.detectChanges();

      // ASSERT
      expect(emittedValue).toEqual('test');
    });

    it('should emit empty string when user clear the input', () => {
      // ARRANGE
      let emittedValue: string | undefined = 'initialValue';

      component.valueChange.subscribe((value) => {
        emittedValue = value;
      });

      const inputElement: HTMLInputElement = fixture.nativeElement.querySelector('input');

      // ACT
      inputElement.value = '';
      inputElement.dispatchEvent(new Event('input'));
      fixture.detectChanges();

      // ASSERT
      expect(emittedValue).toEqual('');
    });

    it('should emit trimmed value', () => {
      // ARRANGE
      let emittedValue: string | undefined;

      component.valueChange.subscribe((value) => {
        emittedValue = value;
      });

      const inputElement: HTMLInputElement = fixture.nativeElement.querySelector('input');

      // ACT
      inputElement.value = '   test   ';
      inputElement.dispatchEvent(new Event('input'));
      fixture.detectChanges();

      // ASSERT
      expect(emittedValue).toEqual('test');
    });
  });
});
