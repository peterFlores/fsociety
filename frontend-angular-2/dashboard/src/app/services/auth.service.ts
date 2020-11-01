import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { OAuth } from 'app/model/auth';
import { environment } from 'environments/environment';
import { map, catchError } from 'rxjs/operators';
import { BehaviorSubject, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private currentTokenSubject: BehaviorSubject<OAuth>;
  public  currentToken: Observable<OAuth>;

  constructor(private httpClient: HttpClient) {
    this.currentTokenSubject = new BehaviorSubject<OAuth>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentToken = this.currentTokenSubject.asObservable();
  }

  public get currentUserValue(): OAuth {
    return this.currentTokenSubject.value;
}
  login(user: string, pass: string): Observable<OAuth> {
    const usr = "socialapp";
    const pss = "12345";
    const body = new HttpParams()
      .set("username", user)
      .set("password", pass)
      .set("grant_type", "password");
    return this.httpClient.post<OAuth>(`${environment.apiOAUTH}`, body, {
      headers: new HttpHeaders()
        .set("Authorization", `Basic ${btoa(`${usr}:${pss}`)}`)
    }).pipe(map(user => {
      localStorage.setItem('currentUser', JSON.stringify(user));


      this.currentTokenSubject.next(user);
      return user;
    }), catchError(this.handleError));
  }
  handleError(error) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // client-side error
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(errorMessage);
  }
  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    this.currentTokenSubject.next(null);
}
}
