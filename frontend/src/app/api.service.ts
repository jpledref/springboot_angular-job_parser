import { Injectable, Output, EventEmitter } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

@Output() 
change= new EventEmitter<Object>();
	
@Output() 
workingEvent= new EventEmitter<Boolean>();
	
  jobs;
	
  constructor(private httpClient: HttpClient) { }
  
    public getJobsWithParam(data){
	let parameters = new HttpParams({
	  fromObject: {
	    what: data.what,
	    where: data.where
	  }
	});
	
	var port = window.location.port;  
	var backend= "";   
	if(port=="4200"){
		backend="http://localhost";
	}  
	this.change.emit({"jobs":[],"charts":{"pie":{"values":[],"labels":[]}}});
	this.workingEvent.emit(true);
	this.httpClient.get(backend+'/jobs', { params: data}).subscribe((data)=>{
	      this.change.emit(data);	      
	      this.workingEvent.emit(false);	
	    });
   }
   
   public getAllJobs(){
	//console.log(this.jobs);
	return this.jobs;   
   }
  
  
}
