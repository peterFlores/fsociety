import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../services/user.service';
import { HttpParams } from '@angular/common/http';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  userMail: string;
  userNickname: string;
  userPassword: string;

  constructor(private userService: UserService) { }

  async onFacebookLogin(){
    try{
      this.userService.loginFacebook();
    }
    catch(error){console.log(error)}
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


  ngOnInit() {} 
}
