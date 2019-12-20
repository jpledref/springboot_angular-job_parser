import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-search-job',
  templateUrl: './search-job.component.html',
  styleUrls: ['./search-job.component.css']
})
export class SearchJobComponent implements OnInit {

	formdata: FormGroup;

	constructor(private formBuilder: FormBuilder,private apiService: ApiService	) { }

	ngOnInit() {
		this.initForm();	
		this.onSubmit();
	}
	  
	onSubmit(){
		this.apiService.getJobsWithParam(this.formdata.value);
	}

	initForm() {
	   this.formdata = this.formBuilder.group({
		 what: 'java',
		 where: 'lyon'
	      });
	  }
}

