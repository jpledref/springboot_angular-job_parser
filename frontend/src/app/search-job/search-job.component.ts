import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ApiService } from '../api.service';
import { ConfigloaderService } from '../configloader.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-search-job',
  templateUrl: './search-job.component.html',
  styleUrls: ['./search-job.component.css']
})
export class SearchJobComponent implements OnInit {

  formdata: FormGroup;

  constructor(private formBuilder: FormBuilder, private apiService: ApiService, private configloaderService: ConfigloaderService, private actRoute: ActivatedRoute) { }

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
    var q = "informatique", l = "lyon";
    this.actRoute.queryParams.subscribe(params => {
      if (params['q']) q = params['q'];
      if (params['l']) l = params['l'];
      this.formdata = this.formBuilder.group({
        what: q,
        where: l
      });
    });

    this.formdata = this.formBuilder.group({
      what: q,
      where: l
    });
  }
}
