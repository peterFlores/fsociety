import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../services/user.service';
import { HttpParams } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
 
  constructor(private userService: UserService) { }

  async onFacebookLogin(){
    try{
      this.userService.loginFacebook();
    }
    catch(error){console.log(error)}
  }

  ngOnInit(): void {
  }

}
