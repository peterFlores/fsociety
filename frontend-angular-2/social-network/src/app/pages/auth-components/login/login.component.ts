import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../services/user.service';
import { HttpParams } from '@angular/common/http';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'environments/environment';
import { from, Observable} from 'rxjs'

import { SocialAuthService, SocialUser } from "angularx-social-login";
import { FacebookLoginProvider } from "angularx-social-login";

import { LocalStorageService } from '../../../services/local-storage.service';

import { JwtHelperService } from '@auth0/angular-jwt';
import { first } from 'rxjs-compat/operator/first';

import { Route, Router } from '@angular/router';
import { Validator } from '@angular/forms';
import { auth } from 'firebase';

import { jwtDecode } from 'jwt-js-decode';

//import { jwt_decode } from '../../../../../node_modules/jwt-decode';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  private router$:Router;
  private auth$: UserService;


  username: string;
  password: string;
  grant_type: string;  

  user: SocialUser;
  loggedIn: boolean;
 
  constructor(private userService: UserService,
              private _httpClient: HttpClient,
              private authService: SocialAuthService,
              public localStorage: LocalStorageService,
              protected auth: UserService) { 
                this.auth$ = auth;

              }

  async onFacebookLogin(){
    try{
      this.userService.loginFacebook();
    }
    catch(error){console.log(error)}
  }

  signInWithFB() {
  this.authService.signIn((FacebookLoginProvider.PROVIDER_ID))
  }


  login(): Observable<any> {
    debugger
    const body = new HttpParams()
      .set('username', 'peter@gmail.com')
      .set('password', '12345')
      .set('grant_type', 'password');
    return this._httpClient.post<any>(`${environment.apiURL}/oauth/token`, body.toString(), {
      headers: new HttpHeaders()
        .set('Content-Type', 'application/x-www-form-urlencoded')
        .set("Authorization", `Basic ${btoa("socialapp:12345")}`)
    });
  }

  loginUser() {

    const url = '/oauth/token';

    const body = new HttpParams()
      .set('username', this.username)
      .set('password', this.password)
      .set('grant_type', 'password');

   var token = this.userService.post(url, body)
      .subscribe(response => {
        console.log(response);
      });
    
      

      this.auth$.login('username', 'password')
        .subscribe(response => {
          console.log(response);

          if (response){
            localStorage.setItem('token', response);

            this.router$.navigate(['/Protected']);
          }
        });

  }



  ngOnInit(): void {
  }

}
