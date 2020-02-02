import { Component, OnInit } from '@angular/core';
import { ChartType, ChartOptions } from 'chart.js';
import { SingleDataSet, Label, monkeyPatchChartJsLegend, monkeyPatchChartJsTooltip } from 'ng2-charts';
import { ApiService } from '../api.service';

import { Inject } from '@angular/core';
import { PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';

@Component({
  selector: 'app-chart-pie',
  templateUrl: './chart-pie.component.html',
  styleUrls: ['./chart-pie.component.css']
})
export class ChartPieComponent implements OnInit {
  coloR = [];

  //Ng2-chart not fully supported on angular universal
  //https://github.com/angular/universal-starter/issues/538
  //Look at ngIf* into html file to remove 'NotYetImplemented' error message
  isBrowser;

  public pieChartOptions: ChartOptions = {
    responsive: true,
    legend: {
      labels: {
        "fontColor": "#000"
      }
    }
  };

  public pieChartLabels: Label[] = [];
  public pieChartData: SingleDataSet = [];
  public pieChartType: ChartType = 'pie';
  public pieChartLegend = true;
  public pieChartPlugins = [];
  public pieChartColors = [{
    "backgroundColor": this.coloR,
    "fontColor": "#000"
  }];

  constructor(private apiService: ApiService, @Inject(PLATFORM_ID) private platformId: Object) {
    isPlatformBrowser(platformId);

    this.apiService.change.subscribe(data => {
      this.pieChartLabels = data.charts.pie.labels;
      this.pieChartData = data.charts.pie.values;
      this.coloR = [];
      var n = 0;
      for (var i in this.pieChartLabels) {
        //this.coloR.push(dynamicColors());
        this.coloR.push(redColors(n));
        this.pieChartColors[0].backgroundColor = this.coloR;
        n++;
      }
    });

    this.apiService.append.subscribe(data => {
      var label = "";
      var nb = data.jobs.length;
      if (data.jobs[0] != null) label = data.jobs[0].siteSource;

      if (nb != 0) {
        this.pieChartLabels.push(label);
        this.pieChartData.push(nb);
        this.coloR = [];
        var n = 0;
        for (var i in this.pieChartLabels) {
          this.coloR.push(redColors(n));
          this.pieChartColors[0].backgroundColor = this.coloR;
          n++;
        }
      }
    });


  }

  ngOnInit() {
    //monkeyPatchChartJsTooltip();
    //monkeyPatchChartJsLegend();
  }

}

function dynamicColors() {
  var r = Math.floor(Math.random() * 255);
  var g = Math.floor(Math.random() * 255);
  var b = Math.floor(Math.random() * 255);
  return "rgba(" + r + "," + g + "," + b + ",1)";
};

function redColors(n) {
  var x = 0 + 50 * n;
  var r = 240;
  var g = x;
  var b = x;
  return "rgba(" + r + "," + g + "," + b + ",1)";
}
