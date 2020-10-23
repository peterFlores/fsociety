import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../services/user.service';
import { HttpParams } from '@angular/common/http';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'environments/environment';
import { Observable} from 'rxjs'

import { SocialAuthService, SocialUser } from "angularx-social-login";
import { FacebookLoginProvider } from "angularx-social-login";

import * as jwt_decode from 'jwt-decode';
import { AuthService } from 'app/services/auth.service';

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

  
 
  constructor(private userService: AuthService,
              private _httpClient: HttpClient,
              private authService: SocialAuthService) { }

  async onFacebookLogin(){
    try{
      this.userService.loginFacebook();
    }
    catch(error){console.log(error)}
  }

  //signInWithFB() {
    //this.authService.signIn((FacebookLoginProvider.PROVIDER_ID))
  //}

  loginUser() {

    const url = '/oauth/token';

    const body = new HttpParams()
      .set('username', this.username)
      .set('password', this.password)
      .set('grant_type', 'password');

    this.userService.post(url, body)
      .subscribe(response => {
        console.log(response)
        localStorage.setItem('token', JSON.stringify(response)) 
      });
  }
decodificar(){
  var token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJQRVRFUkBHTUFJTC5DT00iLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiXSwibmlja25hbWUiOiJURVNUMDAxIiwiY3JlYXRlZF9hdCI6MTYwMjI4ODAwMDAwMCwicHJvZmlsZV9waWN0dXJlIjoiaHR0cDovLzMuMjIuMjMwLjkyL0ltYWdlcy9Qcm9maWxlL2RzZHNkLmpwZyIsImV4cCI6MTYwMzQxNTg1NCwiYXV0aG9yaXRpZXMiOlsiU09DSUFMIl0sImp0aSI6ImYyYjkwNWRhLTU1OWYtNDE4NC1hZjFjLWJiM2ViMjNkODI5YyIsImNsaWVudF9pZCI6InNvY2lhbGFwcCJ9.CRHSdrFglhFfWyNZM05_jcyBdsj_7y7-nb_1IF6pJ68";
  var decoded = jwt_decode(token);

console.log(decoded);
}

  decodetoken(){
    var token = localStorage.getItem('token');

   var decoded = jwt_decode(token)

    console.log(decoded)
  }
  

  ngOnInit(): void {
  }

}
