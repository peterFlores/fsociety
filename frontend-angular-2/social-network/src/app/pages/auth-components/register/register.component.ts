import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../services/user.service';
import { HttpParams } from '@angular/common/http';

import { SocialAuthService, SocialUser } from "angularx-social-login";
import { FacebookLoginProvider } from "angularx-social-login";
import { stringify } from 'querystring';
import { promise } from 'protractor';
import { AuthService } from 'app/services/auth.service';
import { subscribeOn } from 'rxjs-compat/operator/subscribeOn';
import { Router } from '@angular/router';


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
              private authService: SocialAuthService,
              private router: Router) { }


signInWithFB(): void {
  try {
    var data = this.authService.signIn(FacebookLoginProvider.PROVIDER_ID);
   localStorage.setItem('usuario', JSON.stringify(console.log(data)))
   this.router.navigateByUrl('/profile');
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
        localStorage.setItem('token', JSON.stringify(response))
        this.router.navigateByUrl('/profile');
      });
  }

  test : Date = new Date();
  focus;
  focus1;
}
