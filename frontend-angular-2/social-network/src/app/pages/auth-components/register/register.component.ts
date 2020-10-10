import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../services/user.service';
import { HttpParams } from '@angular/common/http';

import { SocialAuthService, SocialUser } from "angularx-social-login";
import { FacebookLoginProvider } from "angularx-social-login";
import { stringify } from 'querystring';
import { promise } from 'protractor';


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

  constructor(private userService: UserService,
              private authService: SocialAuthService) { }

  //async onFacebookLogin(){
    //try{
      //this.userService.loginFacebook();
    //}
    //catch(error){console.log(error)}
  //}

  ngOnInit() {
    this.authService.authState.subscribe((user) => {
      this.user = user;
      console.log(this.user);
      this.loggedIn = (user != null);
    });
  }

  signInWithFB() {
    this.authService.signIn((FacebookLoginProvider.PROVIDER_ID))
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
      });
  }


  
}
