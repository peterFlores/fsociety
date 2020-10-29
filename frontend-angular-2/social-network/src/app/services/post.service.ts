import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private _httpClient: HttpClient) { }

  createPost(id: number, post: string, image: string): Observable<any> {
    let body = new HttpParams()
      .set("userId", id.toString())
      .set("postContent", post)
      .set("imagePath", image)
     
    return this._httpClient.post<any>(`${environment.apiURL}/post/Createpost`, body);
  }

  deletePost(id: number): Observable<any> {
    return this._httpClient.delete<any>(`${environment.apiURL}/post/removePost/${id}`);
  }
}
