export enum ActionEnum {
    CONSULTER = 'consulter',
    MODIFIER = 'modifier',
    SUPPRIMER = 'supprimer',
    TRIER_BAS = 'trier_bas',
    TRIER_HAUT = 'trier_haut'
}

export const ACTION_ICONS: Record<string, string> = {
  [ActionEnum.CONSULTER]: 'pi pi-eye',
  [ActionEnum.MODIFIER]: 'pi pi-pencil',
  [ActionEnum.SUPPRIMER]: 'pi pi-trash',
  [ActionEnum.TRIER_BAS]: 'pi pi-angle-double-down',
  [ActionEnum.TRIER_HAUT]: 'pi pi-angle-double-up'
};