import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ApiService } from '../api.service';
import { ConfigloaderService } from '../configloader.service';

@Component({
  selector: 'app-search-job',
  templateUrl: './search-job.component.html',
  styleUrls: ['./search-job.component.css']
})
export class SearchJobComponent implements OnInit {

  formdata: FormGroup;

  constructor(private formBuilder: FormBuilder, private apiService: ApiService, private configloaderService: ConfigloaderService) { }

  ngOnInit() {
    this.initForm();
    this.configloaderService.loaded.subscribe(data => {
      this.onSubmit();
    });
  }

  onSubmit() {
    this.apiService.getJobsWithParamLazy(this.formdata.value);
  }

  initForm() {
    this.formdata = this.formBuilder.group({
      what: 'java',
      where: 'lyon'
    });
  }
}
