import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-spinner',
  templateUrl: './spinner.component.html',
  styleUrls: ['./spinner.component.css']
})
export class SpinnerComponent implements OnInit {
  
  color = 'primary';
  mode = 'indeterminate';
  value = 50;
  isWorking=false;	
	
  constructor(private apiService: ApiService) { }

  ngOnInit() {
	this.apiService.workingEvent.subscribe(data=>{
		this.isWorking=data;
	});	  
  }

}
