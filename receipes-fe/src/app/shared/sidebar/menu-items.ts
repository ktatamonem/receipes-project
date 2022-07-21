import { RouteInfo } from './sidebar.metadata';

export const ROUTES: RouteInfo[] = [
 
  {
    path: '/pages/ingredient/list',
    title: 'Ingredients',
    icon: 'bi bi-basket',
    class: '',
    extralink: false,
    submenu: []
  }, {
    path: '/pages/recipe/list',
    title: 'Recipes',
    icon: 'bi bi-clipboard',
    class: '',
    extralink: false,
    submenu: []
  }
 
];
