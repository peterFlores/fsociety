import { NgModule } from '@angular/core';
import { CommonModule, } from '@angular/common';
import { BrowserModule  } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';
//import { SocialLayoutComponent } from './layout/social-layout/social-layout.component';
//import { AuthLayoutComponent } from './layout/auth-layout/auth-layout.component';
import { LoginComponent } from './pages/auth-components/login/login.component';
import { RegisterComponent } from './pages/auth-components/register/register.component';


const appRoutes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'registro', component: RegisterComponent},

  //{
    //path: "",
    //component: SocialLayoutComponent,
    //children: [
      //{
        //path: "dashboard",
        //loadChildren:
       //   "./layout/social-layout/social-layout.module#SocialLayoutModule"
      //},
      // {
      //   path: "components",
      //   loadChildren: "./pages/components/components.module#ComponentsModule"
      // }
    //],
    //canActivate: [AuthGuard]
  //},
 // {
   // path: "",
    //component: AuthLayoutComponent,
    //children: [
     // {
      //  path: "auth",
       // loadChildren:
        //  "./layout/auth-layout/auth-layout.module#AuthLayoutModule"
      //}
    //]
  //},
  //{
    //path: "**",
    //redirectTo: ""
  //}
];

@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    RouterModule.forRoot(
      appRoutes,
      {enableTracing: true}
    )
  ],
  exports: [RouterModule
  ],
})
export class AppRoutingModule { }
