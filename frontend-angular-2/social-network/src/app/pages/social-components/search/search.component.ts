import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Users } from 'app/models/users';
import { UserItem } from 'app/models/user_item';
import { UserService } from 'app/services/user.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {

  users: UserItem[] = [];
  defaultBindingsList = [
    { value: 'byNickname', label: 'NICKNAME' },
    { value: 'byMail', label: 'EMAIL' },
    { value: 'byName', label: 'NAME'}
];
  topUsers: UserItem[] = [];
  visible = false;
  visibleTop = false;
  selectedCity = null;
  formGroup: FormGroup;
  constructor(private _formBuilder: FormBuilder, private _userService: UserService, private router: Router) { }

  ngOnInit(): void {
    this.formGroup = this._formBuilder.group({
      tipo: [null, [Validators.required]],
      busqueda: ['', [Validators.required, Validators.minLength(3)]]
    });
    this._userService.getTop5().subscribe(data => {
      this.topUsers = data;
      if (this.topUsers.length > 0) {
        this.visibleTop = true;
      }
    }, error => {
      this.visibleTop = false;
    });
  }
  onSubmit() {
    if (this.formGroup.invalid) {
      return;
    }
    let type = this.formGroup.get('tipo').value;
    let searchparam = this.formGroup.get('busqueda').value;
    this._userService.searchUsers(type, searchparam).subscribe(data => {
      this.formGroup.reset();
      if (data.code == "1") {
        this.visible = true;
        this.users = data.data;
      } else {
        this.visible = false;
      }
    }, error => {
      console.log(error);
    });
    console.log(this.formGroup);
  }

  goToRoute(id: number) {
    let url = `/dashboard/dashboard/${id}`;    
    this.router.navigateByUrl(url);
  }
}
