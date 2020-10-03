import { Routes } from "@angular/router";
import { LoginComponent } from "app/pages/auth-components/login/login.component";
import { RegisterComponent } from "app/pages/auth-components/register/register.component";

export const AuthLayoutRoutes: Routes = [
  {
    path: "login",
    component: LoginComponent,
  },
  {
    path: "register",
    component: RegisterComponent,
  },
  { path: "", redirectTo: "login" },
];
