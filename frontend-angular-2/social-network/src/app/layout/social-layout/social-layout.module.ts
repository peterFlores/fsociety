import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SocialLayoutRoutes } from "./social-layout.routing";
import { ProfileComponent } from "app/examples/profile/profile.component";
import { SearchComponent } from "app/pages/social-components/search/search.component";
import { NgSelectModule } from "@ng-select/ng-select";

@NgModule({
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    NgSelectModule,
    NgbModule,
    RouterModule.forChild(SocialLayoutRoutes),
  ],
  declarations: [
    ProfileComponent,
    SearchComponent
  ]
})
export class SocialLayoutModule { }
