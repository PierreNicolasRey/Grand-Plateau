export interface FilArianeStep {
  label: string;
  url: string;
  isLast: boolean;
}

export const DEFAULT_FIL_ARIANE_STEP: FilArianeStep = {
  label: 'Coureurs',
  url: '/coureurs',
  isLast: true
}