import { Routes } from "@angular/router";
import { ProfileComponent } from "app/examples/profile/profile.component";

export const SocialLayoutRoutes: Routes = [
    { path: 'dashboard',      component: ProfileComponent },

  { path: "", redirectTo: "dashboard" },
];
