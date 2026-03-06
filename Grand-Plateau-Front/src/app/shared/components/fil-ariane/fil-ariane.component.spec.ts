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
            { path: 'coureurs', component: DummyComponent, data: { filAriane: 'Coureurs' }, children: [
              { path: 'jean-bon', component: DummyComponent, data: { filAriane: 'J.Bon' } }
            ] },
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
      expect(filAriane.length).toEqual(1);
      expect(filAriane[0].label).toEqual('Administration');
    });

    it('should fallback to DEFAULT_FIL_ARIANE_STEP and redirect to /coureurs when navigating to an unknown route', async () => {
      // ACT
      await router.navigate(['/route-inconnue']);

      // ASSERT
      expect(router.url).toEqual('/coureurs');

      const filAriane = component.filAriane();
      expect(filAriane.length).toEqual(1);
      expect(filAriane[0].label).toEqual('Coureurs');
      expect(filAriane[0].url).toEqual('/coureurs');
    });

    it('should return default step if route exists but has no filAriane data', async () => {
      // ACT
      await router.navigate(['/sans-data']);

      const filAriane = component.filAriane();

      // ASSERT
      expect(filAriane).toEqual([DEFAULT_FIL_ARIANE_STEP]);
    });

    it('should handle multi steps when navigating to child route', async () => {
      // ACT
      // From /coureurs, navigating to Jean Bon personal page
      await router.navigate(["/coureurs", "jean-bon"]);

      const filAriane = component.filAriane();

      // ASSERT
      expect(filAriane.length).toEqual(2);
      expect(filAriane[0].label).toEqual('Coureurs');
      expect(filAriane[0].url).toEqual('/coureurs');
      expect(component.filAriane()[0].isLast).toBeFalse();
      expect(filAriane[1].label).toEqual('J.Bon');
      expect(filAriane[1].url).toEqual('/coureurs/jean-bon');
      expect(component.filAriane()[1].isLast).toBeTrue();
    });
  });
  
});

@Component({ template: '' }) class DummyComponent {}