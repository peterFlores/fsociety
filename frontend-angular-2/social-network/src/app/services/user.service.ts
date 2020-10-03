import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from 'app/models/user';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private _httpClient: HttpClient) { }

  getUserByID(id: number): Observable<User> {
    id = 2;
    return this._httpClient.get<User>(`${environment.apiURL}/search_user/${id}`);
  }
}
