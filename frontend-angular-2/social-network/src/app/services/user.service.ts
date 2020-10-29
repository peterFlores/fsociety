import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Response } from 'app/models/response';
import { User } from 'app/models/user';
import { Users } from 'app/models/users';
import { UserItem } from 'app/models/user_item';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private _httpClient: HttpClient) { }

  searchUsers(type: string, param: string): Observable<Users> {
    return this._httpClient.get<Users>(`${environment.apiURL}/search/${type}/${param}`);
  }
  getUserByID(id: number): Observable<User> {
    id = 2;
    return this._httpClient.get<User>(`${environment.apiURL}/search_user/${id}`);
  }

  updateUser(id: number, birthdate, name, nickname, gender): Observable<Response> {
    const body = new HttpParams()
    .set("idUser",id.toString())
    .set("userBirthDate", birthdate)
    .set("userName", name)
    .set("userNickname", nickname)
    .set("userGender", gender);
    return this._httpClient.put<Response>(`${environment.apiURL}/updateUser/${id}`, body);
  }

  getTop5(): Observable<UserItem[]> {
    return this._httpClient.get<UserItem[]>(`${environment.apiURL}/listTop5`);
  }

  insertVisit(id: number, idVisitor: number): Observable<Response> {
    return this._httpClient.get<Response>(`${environment.apiURL}/visit/${id}/${idVisitor}`);
  }

  updatePhoto(id: number, file: any): Observable<Response> {
    const body = new FormData()
        body.append("picture", file);
    return this._httpClient.post<Response>(`${environment.apiURL}/upload/picture/${id}`, body);
  }


  login(): Observable<any> {
    const body = new HttpParams()
      .set('username', 'PETER@GMAIL.COM')
      .set('password', '12345')
      .set('grant_type', 'password');
    return this._httpClient.post<any>(`${environment.apiURLOAUTH}/oauth/token`, body.toString(), {
      headers: new HttpHeaders()
        .set('Content-Type', 'application/x-www-form-urlencoded')
        .set("Authorization", `Basic ${btoa("socialapp:12345")}`)
    });
  }
}
