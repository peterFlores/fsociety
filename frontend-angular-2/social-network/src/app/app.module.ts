import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RouterModule, Routes } from '@angular/router';
import { AppRoutingModule } from './app.routing';
import { HttpClientModule } from "@angular/common/http";

import { AppComponent } from './app.component';

import { ComponentsModule } from './components/components.module';
import { SocialLayoutComponent } from './layout/social-layout/social-layout.component';
import { AuthLayoutComponent } from './layout/auth-layout/auth-layout.component';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './pages/auth-components/login/login.component';
import { RegisterComponent } from './pages/auth-components/register/register.component';

//firebase
import { AngularFireModule } from '@angular/fire';
import { AngularFireAuth, AngularFireAuthModule } from '@angular/fire/auth';
import { environment } from 'environments/environment';


@NgModule({
  declarations: [
    SocialLayoutComponent, AuthLayoutComponent, AppComponent, LoginComponent, RegisterComponent
  ],

  exports:[
    LoginComponent, RegisterComponent
  ],

  imports: [
    BrowserModule,
    NgbModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot([]),
    ComponentsModule,
    AppRoutingModule,
    ReactiveFormsModule,
    AngularFireModule.initializeApp(environment.firebaseConfig),
    AngularFireAuthModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
