import { Component } from '@angular/core';
import { ConfigloaderService } from './configloader.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Angular-Job-Parser';
	
	constructor(configloaderService: ConfigloaderService) {
		configloaderService.init();
		
	}
	
	
	
}
