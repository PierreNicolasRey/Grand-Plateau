import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilArianeComponent } from './fil-ariane.component';
import { provideRouter, Router } from '@angular/router';
import { Component } from '@angular/core';
import { DEFAULT_FIL_ARIANE_STEP } from '../../models/fil-ariane-steps.model';

describe('FilArianeComponent', () => {
  let component: FilArianeComponent;
  let fixture: ComponentFixture<FilArianeComponent>;
  let router: Router;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        FilArianeComponent
      ],
      providers: [
        provideRouter(
          [
            { path: 'coureurs', component: DummyComponent, data: { filAriane: 'Coureurs' } },
            { path: 'administration', component: DummyComponent, data: { filAriane: 'Administration' } },
            { path: 'sans-data', component: DummyComponent },
            { path: '', redirectTo: 'coureurs', pathMatch: 'full' },
            { path: '**', redirectTo: 'coureurs' }
          ]
        )
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FilArianeComponent);
    component = fixture.componentInstance;
    router = TestBed.inject(Router);
    fixture.detectChanges();
  });

  describe('Component Initialization', () => {
    it('should create', () => {
      expect(component).toBeTruthy();
    });

    it('should be on Coureurs and have a length 1 list by default', () => {
      expect(component.filAriane().length).toEqual(1);
      expect(component.filAriane()[0].label).toEqual('Coureurs');
      expect(component.filAriane()[0].url).toEqual('/coureurs');
      expect(component.filAriane()[0].isLast).toBeTrue();
    })
  });

  describe('Navigation', () => {
    it('should react to navigation and find the label "Administration"', async () => {
      // ACT
      await router.navigate(['/administration']);
      
      const filAriane = component.filAriane();

      // ASSERT
      expect(filAriane.length).toBe(1);
      expect(filAriane[0].label).toBe('Administration');
    });

    it('should fallback to DEFAULT_FIL_ARIANE_STEP and redirect to /coureurs when navigating to an unknown route', async () => {
      // ACT
      await router.navigate(['/route-inconnue']);

      // ASSERT
      expect(router.url).toBe('/coureurs');

      const filAriane = component.filAriane();
      expect(filAriane.length).toBe(1);
      expect(filAriane[0].label).toBe('Coureurs');
      expect(filAriane[0].url).toBe('/coureurs');
    });

    it('should return default step if route exists but has no filAriane data', async () => {
      // ACT
      await router.navigate(['/sans-data']);

      const filAriane = component.filAriane();

      // ASSERT
      expect(filAriane).toEqual([DEFAULT_FIL_ARIANE_STEP]);
    });
  });
  
});

@Component({ template: '' }) class DummyComponent {}