import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../services/user.service';
import { HttpParams } from '@angular/common/http';

import { SocialAuthService, SocialUser } from "angularx-social-login";
import { FacebookLoginProvider } from "angularx-social-login";
import { stringify } from 'querystring';
import { promise } from 'protractor';
import { AuthService } from 'app/services/auth.service';
import { subscribeOn } from 'rxjs-compat/operator/subscribeOn';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  userMail: string;
  userNickname: string;
  userPassword: string;

  user: SocialUser;
  loggedIn: boolean;

  constructor(private userService: AuthService,
              private authService: SocialAuthService) { }


signInWithFB(): void {
  try {
      var data = this.authService.signIn(FacebookLoginProvider.PROVIDER_ID);        
      console.log(data)
      } catch (e) {
      console.log(e);
      }
}


  ngOnInit() {
    this.authService.authState.subscribe((user) => {
      this.user = user;
      console.log(this.user);
      this.loggedIn = (user != null);
    });
  }

  createUser() {
    const url = 'http://3.22.230.92:40000/createUser';
    
    const body = new HttpParams()
    .set('userMail', this.userMail)
    .set('userNickname', this.userNickname)
    .set('userPassword', this.userPassword);

    this.userService.post(url, body)
      .subscribe(response => {
        console.log(response);
        localStorage.setItem('user', JSON.stringify(response))
      });
  }


  
}
