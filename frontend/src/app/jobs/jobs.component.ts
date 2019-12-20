import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-jobs',
  templateUrl: './jobs.component.html',  	
  styleUrls: ['./jobs.component.css']
})
export class JobsComponent implements OnInit {

  jobs;
  displayedColumns: string[] = ['title', 'enterprise', 'description', 'siteSource', 'location', 'salary'];	
  columnsToDisplay: string[] = this.displayedColumns.slice();	
	
  constructor(private apiService: ApiService) { }

  ngOnInit() {	  
	this.apiService.change.subscribe(data=>{		
		this.jobs=data.jobs;
		//console.log(this.jobs);
	});		
  }
  
}
