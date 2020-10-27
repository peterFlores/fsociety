import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
//import { User } from 'app/models/user';
import { environment } from "environments/environment";
import { from, Observable } from "rxjs";

import { auth } from "firebase/app";
import { AngularFireAuth } from "@angular/fire/auth";
import { User } from "firebase";
import { SocialAuthService } from "angularx-social-login";
import { Router } from '@angular/router';
// @ts-ignore  
import jwt_decode from "jwt-decode";


@Injectable({
  providedIn: "root",
})
export class AuthService {
  public user: User;


  TOKEN_KEY='token'

  constructor(
    private _httpClient: HttpClient,
    public afAuth: AngularFireAuth,
    private authService: SocialAuthService,
    private router: Router
  ) {}


  logout(){
    localStorage.removeItem('token');
    this.router.navigateByUrl('/home');
  }

  //Decoder
  getDecodedAccessToken(){
    var token = localStorage.getItem('token');
    var decoded = jwt_decode(token); 
    var idUser = decoded.id;
    console.log(idUser);   
  }

  

  get(url) {
    return this._httpClient.get(url);
  }

  post(url, body) {
    var headers_object = new HttpHeaders();
    headers_object.append("Content-Type", "application/x-www-form-urlencoded");
    headers_object.append("Authorization", `Basic ${btoa("socialapp:12345")}`);

    const httpOptions = {
      headers: headers_object,
    };

    return this._httpClient.post(url, body, httpOptions);
  }

  put(url, data) {
    return this._httpClient.put(url, data);
  }

  delete(url) {
    return this._httpClient.delete(url);
  }
}
