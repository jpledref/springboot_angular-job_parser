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
			"fontColor": "white"
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
		"fontColor":"white"
	}];

	constructor(private apiService: ApiService) { 
		this.apiService.change.subscribe(data=>{	
			this.pieChartLabels=data.charts.pie.labels;	
			this.pieChartData=data.charts.pie.values;
			this.coloR=[];
			for(var i in this.pieChartLabels){
				this.coloR.push(dynamicColors());
				this.pieChartColors[0].backgroundColor=this.coloR;
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
