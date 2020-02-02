import { Component, OnInit } from '@angular/core';
import { ConfigloaderService } from './configloader.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Angular-Job-Parser';

  configloaderService;

  ngOnInit() {
    //console.log("ngInit");
    //this.configloaderService.init();
  }

  constructor(configloaderService: ConfigloaderService) {
    this.configloaderService = configloaderService;
    configloaderService.init();
  }

}
