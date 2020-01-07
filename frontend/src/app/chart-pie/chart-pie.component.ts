import { Component, OnInit } from '@angular/core';
import { ChartType, ChartOptions } from 'chart.js';
import { SingleDataSet, Label, monkeyPatchChartJsLegend, monkeyPatchChartJsTooltip } from 'ng2-charts';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-chart-pie',
  templateUrl: './chart-pie.component.html',
  styleUrls: ['./chart-pie.component.css']
})
export class ChartPieComponent implements OnInit {	
	 coloR = [];
	
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
		"fontColor":"#000"
	}];

	constructor(private apiService: ApiService) { 
		this.apiService.change.subscribe(data=>{	
			this.pieChartLabels=data.charts.pie.labels;	
			this.pieChartData=data.charts.pie.values;
			this.coloR=[];
			var n=0;
			for(var i in this.pieChartLabels){
				//this.coloR.push(dynamicColors());
				this.coloR.push(redColors(n));
				this.pieChartColors[0].backgroundColor=this.coloR;
				n++;
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

function redColors(n){
    var x = 0 + 50*n;	
    var r = 240;
    var g = x;
    var b = x;
    return "rgba(" + r + "," + g + "," + b + ",1)";
}


