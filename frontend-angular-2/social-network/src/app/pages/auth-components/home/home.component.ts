import { Component, OnInit } from '@angular/core';
import { NavigationStart } from '@angular/router';
import { HttpClient, HttpParams } from '@angular/common/http';
import { AuthService } from 'app/services/auth.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  ipVisitor: string;
  data = {
    pageDescription: 'MAIN_PAGE',
  };
  visitDate: string;

  
  url = 'http://3.22.230.92:40000/CreateVisitPage';

  constructor(private userService: AuthService,
              private http: HttpClient) { }

  ngOnInit(): void {
    this.visitas(); 
    }

   
  visitas(){
    this.http.post(this.url, this.data).subscribe (res =>{
      console.log(res);
    });
  }

  }
    


