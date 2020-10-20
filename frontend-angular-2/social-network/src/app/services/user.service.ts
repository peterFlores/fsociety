import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
//import { User } from 'app/models/user';
import { environment } from 'environments/environment';
import { from, Observable } from 'rxjs';

import { auth } from 'firebase/app';
import { AngularFireAuth } from '@angular/fire/auth';
import { User } from 'firebase';
import { SocialAuthService } from 'angularx-social-login';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  public user: User;

  constructor(private _httpClient: HttpClient,
              public afAuth: AngularFireAuth,
              private authService: SocialAuthService) { }

  
  async loginFacebook(){
    try{
      return this.afAuth.signInWithPopup(new auth.FacebookAuthProvider())
    }
    catch(error){console.log(error)}
    
  }

  getUserByID(id: number): Observable<User> {
    id = 2;
    return this._httpClient.get<User>(`${environment.apiURL}/search_user/${id}`);
  }

  get(url) {
    return this._httpClient.get(url);
  }

  post(url, body) {
    var headers_object = new HttpHeaders();
    headers_object.append('Content-Type', 'application/x-www-form-urlencoded');
    headers_object.append("Authorization", `Basic ${btoa("socialapp:12345")}`);

    const httpOptions = {
      headers: headers_object
    };
    
    return this._httpClient.post(url, body, httpOptions);
  }

  put(url, data) {
    return this._httpClient.put(url, data);
  }

  delete(url) {
    return this._httpClient.delete(url);
  }

  login(username: string, password: string): Observable<string>{
    return this._httpClient.get<string>(environment.backend + '/oauth/token' + '$username' + username + '$password' + password );
  }

}
