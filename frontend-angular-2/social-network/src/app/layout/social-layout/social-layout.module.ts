import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SocialLayoutRoutes } from "./social-layout.routing";
import { ProfileComponent } from "app/examples/profile/profile.component";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule,
    RouterModule.forChild(SocialLayoutRoutes),
  ],
  declarations: [
    ProfileComponent
  ]
})
export class SocialLayoutModule { }
