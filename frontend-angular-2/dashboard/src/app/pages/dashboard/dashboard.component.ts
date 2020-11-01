import { Component, OnInit } from '@angular/core';
import { Analitic } from 'app/model/analitic';
import { AnaliticsService } from 'app/services/analitics.service';
import Chart from 'chart.js';


@Component({
    selector: 'dashboard-cmp',
    moduleId: module.id,
    templateUrl: 'dashboard.component.html'
})

export class DashboardComponent implements OnInit{

  public canvas : any;
  public ctx;
  public chartColor;
  public chartEmail;
  public chartHours;

  analitic1: Analitic[] = [];
  analitic2: Analitic[] = [];
  analitic3: Analitic[] = [];
  analitic4: Analitic[] = [];
  analitic5: Analitic[] = [];
  view: any[] = [700, 300];

  // options
  legend: boolean = true;
  showLabels: boolean = true;
  animations: boolean = true;
  xAxis: boolean = true;
  yAxis: boolean = true;
  showYAxisLabel: boolean = true;
  showXAxisLabel: boolean = true;
  timeline: boolean = true;
  showXAxis = true;
  showYAxis = true;
  gradient = false;
  showLegend = true;
  colorScheme = {
    domain: ['#5AA454', '#E44D25', '#CFC0BB', '#7aa3e5', '#a8385d', '#aae3f5']
  };
  constructor(private service: AnaliticsService) {

  }
  ngOnInit(){
    this.service.getGenderRegistred().subscribe(data => {
      this.analitic1 = data;
    });

    this.service.getRegistredByDay().subscribe(data => {
      console.log(data);
      this.analitic2 = data;

    });
    this.service.getVisitByDay().subscribe(data => {
      this.analitic3 = data;

    });
    this.service.getTop5Week().subscribe(data => {
      this.analitic4 = data;

    });
    this.service.getTop5post().subscribe(data => {
      console.log(data);
      this.analitic5 = data;
    });
  }
}
