import { NgModule } from '@angular/core';
import { CommonModule, } from '@angular/common';
import { BrowserModule  } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './pages/auth-components/login/login.component';
import { RegisterComponent } from './pages/auth-components/register/register.component';
import { HomeComponent } from './pages/auth-components/home/home.component'


const appRoutes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'registro', component: RegisterComponent},
  {path: '', component: HomeComponent},
  {path: 'home', component: HomeComponent},

  
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
