import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
//import { User } from 'app/models/user';
import { environment } from "environments/environment";
import {  Observable, BehaviorSubject } from "rxjs";

import { AngularFireAuth } from "@angular/fire/auth";
import { User } from "firebase";
import { Router } from '@angular/router';
// @ts-ignore  
import jwt_decode from "jwt-decode";


@Injectable({
  providedIn: "root",
})



export class AuthService {
  public user: User;
  private currentUserSubject: BehaviorSubject<'token'>;
  public currentUser: Observable<'token'>;

  TOKEN_KEY='token'

  constructor(
    private _httpClient: HttpClient,
    public afAuth: AngularFireAuth,
    private router: Router
  ) {
    this.currentUserSubject = new BehaviorSubject<'token'>(JSON.parse(localStorage.getItem('token')));
    this.currentUser = this.currentUserSubject.asObservable();
  }


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
    this.currentUserSubject.next(idUser);
    return idUser;
  }

  public get currentUserValue(): 'token' {    
    return this.currentUserSubject.value;
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
