export enum ActionEnum {
    CONSULTER = 'consulter',
    MODIFIER = 'modifier',
    SUPPRIMER = 'supprimer'
}

export const ACTION_ICONS: Record<string, string> = {
  [ActionEnum.CONSULTER]: 'pi pi-eye',
  [ActionEnum.MODIFIER]: 'pi pi-pencil',
  [ActionEnum.SUPPRIMER]: 'pi pi-trash'
};