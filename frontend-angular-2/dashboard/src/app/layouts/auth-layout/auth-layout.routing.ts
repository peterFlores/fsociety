import { Routes } from '@angular/router';
import { LoginComponent } from 'app/pages/auth/login/login.component';

import { DashboardComponent } from '../../pages/dashboard/dashboard.component';
import { AuthLayoutComponent } from './auth-layout.component';

export const AuthLayoutRoutes: Routes = [
  {
    path: "login",
    component: LoginComponent,
  },
  { path: "", redirectTo: "login" },
];
