import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilArianeComponent } from './fil-ariane.component';
import { provideRouter, Router } from '@angular/router';
import { Component } from '@angular/core';

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
            { path: 'administration', component: DummyComponent, data: { filAriane: 'Administration' } }
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
      await router.navigate(['/administration']);
      
      const breadcrumbs = component.filAriane();
      expect(breadcrumbs.length).toBe(1);
      expect(breadcrumbs[0].label).toBe('Administration');
    });
  });
  
});

@Component({ template: '' }) class DummyComponent {}