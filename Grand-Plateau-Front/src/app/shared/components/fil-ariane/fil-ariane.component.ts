import { Component, signal } from '@angular/core';
import { DEFAULT_FIL_ARIANE_STEP, FilArianeStep } from '../../models/fil-ariane-steps.model';
import { ActivatedRouteSnapshot, NavigationEnd, Router, RouterLink } from '@angular/router';
import { filter } from 'rxjs';

@Component({
  selector: 'gp-fil-ariane.component',
  imports: [RouterLink],
  templateUrl: './fil-ariane.component.html',
  styleUrl: './fil-ariane.component.scss',
})
export class FilArianeComponent {
  filAriane = signal<FilArianeStep[]>([DEFAULT_FIL_ARIANE_STEP]);

  constructor(private router: Router) {
    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe(() => this.filAriane.set(this.buildFilAriane(this.router.routerState.snapshot.root)));
  }

  private buildFilAriane(root: ActivatedRouteSnapshot): FilArianeStep[] {
    const steps: FilArianeStep[] = [];
    let current: ActivatedRouteSnapshot | null = root.firstChild;

    while (current) {
      if (current.data['filAriane']) {
        steps.push({
          label: current.data['filAriane'],
          url: '/' + current.pathFromRoot
            .filter(r => r.routeConfig?.path)
            .map(r => r.routeConfig!.path)
            .join('/'),
          isLast: false
        });
      }
      current = current.firstChild;
    }

    if (steps.length > 0) {
      steps[steps.length - 1].isLast = true;
      return steps;
    }

    return [DEFAULT_FIL_ARIANE_STEP];
  }
}
