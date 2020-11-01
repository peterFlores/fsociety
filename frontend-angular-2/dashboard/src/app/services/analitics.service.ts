import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Analitic } from 'app/model/analitic';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AnaliticsService {

  constructor(private httpClient: HttpClient) { }

  getTop5post(): Observable<Analitic[]> {
    return this.httpClient.get<Analitic[]>(`${environment.apiURL}/AnaliticsTop5Post`);
  }

  getGenderRegistred(): Observable<Analitic[]>  {
    return this.httpClient.get<Analitic[]>(`${environment.apiURL}/GenderRegistred`);
  }

  getRegistredByDay(): Observable<Analitic[]>  {
    return this.httpClient.get<Analitic[]>(`${environment.apiURL}/RegistredByDay`);

  }

  getVisitByDay(): Observable<Analitic[]>  {
    return this.httpClient.get<Analitic[]>(`${environment.apiURL}/VisitByDay`);

  }
  getTop5Week(): Observable<Analitic[]>  {
    return this.httpClient.get<Analitic[]>(`${environment.apiURL}/Top5Week`);
  }


}
