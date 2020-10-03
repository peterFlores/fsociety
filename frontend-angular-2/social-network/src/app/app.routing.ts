import { NgModule } from '@angular/core';
import { CommonModule, } from '@angular/common';
import { BrowserModule  } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';
import { SocialLayoutComponent } from './layout/social-layout/social-layout.component';
import { AuthLayoutComponent } from './layout/auth-layout/auth-layout.component';


const routes: Routes = [
  {
    path: "",
    component: SocialLayoutComponent,
    children: [
      {
        path: "dashboard",
        loadChildren:
          "./layout/social-layout/social-layout.module#SocialLayoutModule"
      },
      // {
      //   path: "components",
      //   loadChildren: "./pages/components/components.module#ComponentsModule"
      // }
    ],
    //canActivate: [AuthGuard]
  },
  {
    path: "",
    component: AuthLayoutComponent,
    children: [
      {
        path: "auth",
        loadChildren:
          "./layout/auth-layout/auth-layout.module#AuthLayoutModule"
      }
    ]
  },
  {
    path: "**",
    redirectTo: ""
  }
];

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule.forRoot(routes,{
      useHash: true
    })
  ],
  exports: [RouterModule
  ],
})
export class AppRoutingModule { }
