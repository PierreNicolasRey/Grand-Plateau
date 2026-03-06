import { Component, signal } from '@angular/core';
import { DEFAULT_FIL_ARIANE_STEP, FilArianeStep } from '../../models/fil-ariane-steps.model';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs';

@Component({
  selector: 'gp-fil-ariane.component',
  imports: [],
  templateUrl: './fil-ariane.component.html',
  styleUrl: './fil-ariane.component.scss',
})
export class FilArianeComponent {
  filAriane = signal<FilArianeStep[]>([DEFAULT_FIL_ARIANE_STEP]);

  constructor(private router: Router, private activatedRoute: ActivatedRoute) {
    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe(() => {
      this.filAriane.set(this.buildFilAriane(this.activatedRoute.root));
    });
  }

  private buildFilAriane(root: ActivatedRoute): FilArianeStep[] {
    const child = root.firstChild;
    if (child && child.snapshot.data['filAriane'] != null) {
      const routePath = child.snapshot.routeConfig?.path;

      return [
        {
          label: child.snapshot.data['filAriane'],
          url: `/${routePath}`,
          isLast: true,
        }
      ];
    }
    return [DEFAULT_FIL_ARIANE_STEP];
  }
}
