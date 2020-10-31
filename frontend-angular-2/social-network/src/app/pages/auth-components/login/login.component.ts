import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../services/user.service';
import { HttpParams, JsonpClientBackend, JsonpInterceptor } from '@angular/common/http';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'environments/environment';

import { SocialAuthService, SocialUser } from "angularx-social-login";
import { FacebookLoginProvider } from "angularx-social-login";
import { from, Observable, BehaviorSubject, observable } from "rxjs";

import { AuthService } from 'app/services/auth.service';
import * as JwtDecode from 'jwt-decode';
import { userInfo } from 'os';
import { JsonPipe } from '@angular/common';
import { Token, TokenizeResult } from '@angular/compiler/src/ml_parser/lexer';
import { access } from 'fs';
import { jsonpFactory } from '@angular/http/src/http_module';


import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import * as JWT from 'jwt-decode';

// @ts-ignore  
import jwt_decode from "jwt-decode";




@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username: string;
  password: string;
  grant_type: string;  

  user: SocialUser;
  loggedIn: boolean;

  focus;
  focus1;
  test : Date = new Date();
 
  constructor(private userService: AuthService,
              private _httpClient: HttpClient,
              private authService: SocialAuthService,
              private router: Router) {
                if (this.userService.currentUserValue) { 
                  this.router.navigate(['/home']);
              }
               }



  signInWithFB(): void {
    try {
      var data = this.authService.signIn(FacebookLoginProvider.PROVIDER_ID);
     localStorage.setItem('usuario', JSON.stringify(console.log(data)))
     this.router.navigateByUrl('/profile');
    } catch (e) {
      console.log(e);
    }
  }



  loginUser() {
    const url = '/oauth/token';
    const body = new HttpParams()
      .set('username', this.username)
      .set('password', this.password)
      .set('grant_type', 'password');

    this.userService.post(url, body)
      .subscribe(response => {
        const token = localStorage.setItem('token', JSON.stringify(response)) 
        this.router.navigateByUrl('/profile');
        });
  }

    getDecodedAccessToken(){
    var token = localStorage.getItem('token');
    var decoded = jwt_decode(token); 
    var idUser = decoded.id;
    const id = console.log(idUser); 
  }


  logout(){
    localStorage.removeItem('token');
    this.router.navigateByUrl('/home');
  }


  ngOnInit() {
    this.authService.authState.subscribe((user) => {
      this.user = user;
      console.log(this.user);
      this.loggedIn = (user != null);
    });


  }
}
