import { Component, OnInit } from '@angular/core';
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
    constructor(private _userService: UserService) { }

    ngOnInit() {
        //this._userService.getUserByID(2).subscribe(data => {
          //  if (data.code == "1") {
            //    this.user = data.data;
           // }
      //  });
    }

}
