import { NgModule } from '@angular/core';
import { CommonModule, } from '@angular/common';
import { BrowserModule  } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './pages/auth-components/login/login.component';
import { RegisterComponent } from './pages/auth-components/register/register.component';
<<<<<<< HEAD
import { HomeComponent } from './pages/auth-components/home/home.component'
=======
import { ProtectedComponent } from '../app/protected/protected.component';
import { AuthGuard } from './helpers/auth.guard';
>>>>>>> 71890e68c1cf10d3266f75f5d8ef5af69e406b13


const appRoutes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'registro', component: RegisterComponent},
<<<<<<< HEAD
  {path: '', component: HomeComponent},
  {path: 'home', component: HomeComponent},
=======
  //{path: 'Protected', canActivate: [AuthGuard], component: ProtectedComponent }
>>>>>>> 71890e68c1cf10d3266f75f5d8ef5af69e406b13

  
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
