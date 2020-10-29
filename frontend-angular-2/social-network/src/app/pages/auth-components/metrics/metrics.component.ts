import { Component, OnInit } from "@angular/core";
import { Chart } from "../../../../../node_modules/chart.js";
import { UserService } from "../../../services/user.service";
import { HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { AuthService } from "app/services/auth.service.js";

@Component({
  selector: "app-metrics",
  templateUrl: "./metrics.component.html",
  styleUrls: ["./metrics.component.css"],
})
export class MetricsComponent implements OnInit {
  idUser: string;
  userNickname: string;
  numberPosts: string;

  

  constructor(public userService: AuthService) {}

  top5Week() {
    this.userService.get('http://3.22.230.92:40000/Top5Week').subscribe((res: any) => {
      console.log(res);
    });
    }



  ngOnInit(): void {
    var myChart = new Chart("myChart", {
      type: "bar",
      data: {
        labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"],
        datasets: [
          {
            label: "TOP 5",
            data: [12, 19, 3, 5, 2, 3],
            backgroundColor: [
              "rgba(255, 99, 132, 0.2)",
              "rgba(54, 162, 235, 0.2)",
              "rgba(255, 206, 86, 0.2)",
              "rgba(75, 192, 192, 0.2)",
              "rgba(153, 102, 255, 0.2)",
              "rgba(255, 159, 64, 0.2)",
            ],
            borderColor: [
              "rgba(255, 99, 132, 1)",
              "rgba(54, 162, 235, 1)",
              "rgba(255, 206, 86, 1)",
              "rgba(75, 192, 192, 1)",
              "rgba(153, 102, 255, 1)",
              "rgba(255, 159, 64, 1)",
            ],
            borderWidth: 1,
          },
        ],
      },
      options: {
        scales: {
          yAxes: [
            {
              ticks: {
                beginAtZero: true,
              },
            },
          ],
        },
      },
    });

    var myPieChart = new Chart('pieChart', {
      type: 'pie',
      data: {
        datasets: [{
            data: [10, 20, 30],
            backgroundColor: [
              "rgba(255, 99, 132, 0.2)",
              "rgba(54, 162, 235, 0.2)",
              "rgba(255, 206, 86, 0.2)",
            ],
            borderColor: [
              "rgba(255, 99, 132, 1)",
              "rgba(54, 162, 235, 1)",
              "rgba(255, 206, 86, 1)",
            ],
            borderWidth: 1,

        }],
    
        // These labels appear in the legend and in the tooltips when hovering different arcs
        labels: [
            'Red',
            'Blue',
            'Yellow'
        ],
    },
    options: {
      scales: {
        yAxes: [
          {
            ticks: {
              beginAtZero: true,
            },
          },
        ],
      },
    },
  });

  }


}
