import { Injectable, Output, EventEmitter } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ConfigloaderService  {
	
	@Output() 
	loaded= new EventEmitter<Boolean>();

	names; 

	public init(){
		var port = window.location.port;  
		var backend= "";   
		if(port=="4200"){
			backend="http://localhost";
		}
		this.httpClient.get(backend+'/definitions/names').subscribe((data)=>{		          
		      this.names=data;
		      this.loaded.emit(true);
		});
	}	
	
	constructor(private httpClient: HttpClient) { 
	}
	
	public getAllNames(){
		return this.names;   
	}
}
