import { Routes } from "@angular/router";
import { ProfileComponent } from "app/examples/profile/profile.component";
import { SearchComponent } from "app/pages/social-components/search/search.component";

export const SocialLayoutRoutes: Routes = [
    { path: 'dashboard/:id',      component: ProfileComponent },
    { path: 'dashboard',      component: ProfileComponent },
    
    { path: 'search',      component: SearchComponent },
  { path: "", redirectTo: "dashboard" },
];
