import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { AuthLayoutRoutes } from "./auth-layout.routing";

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from "app/pages/auth-components/login/login.component";
import { RegisterComponent } from "app/pages/auth-components/register/register.component";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule,
    RouterModule.forChild(AuthLayoutRoutes),
  ],
  declarations: [
    LoginComponent,
    RegisterComponent
  ]
})
export class AuthLayoutModule {}
