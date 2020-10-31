import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'app/models/user';
import { UserItem } from 'app/models/user_item';
import { UserService } from 'app/services/user.service';

@Component({
    selector: 'app-profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.scss']
})

export class ProfileComponent implements OnInit {

    user: UserItem;
    constructor(private _userService: UserService,
      private router: Router) { }


    ngOnInit() {

      

        //this._userService.getUserByID(2).subscribe(data => {
          //  if (data.code == "1") {
            //    this.user = data.data;
           // }
      //  });
    }

    logout(){
      localStorage.removeItem('token');
      this.router.navigateByUrl('/home');
    }

}
