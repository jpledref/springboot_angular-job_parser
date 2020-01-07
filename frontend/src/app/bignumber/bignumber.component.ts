import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-bignumber',
  templateUrl: './bignumber.component.html',
  styleUrls: ['./bignumber.component.css']
})
export class BignumberComponent implements OnInit {
  numberFound=0;
	
  constructor(private apiService: ApiService){
	this.apiService.change.subscribe(data=>{	
		this.numberFound=Object.keys(data.jobs).length;	
	}); 	
  }

  ngOnInit() {
  }

}
