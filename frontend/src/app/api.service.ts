import { Injectable, Output, EventEmitter } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { ConfigloaderService } from './configloader.service';
//import { Observable } from 'rxjs/Observable';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

@Output() 
change= new EventEmitter<Object>();
	
@Output() 
append= new EventEmitter<Object>();	
		
@Output() 
workingEvent= new EventEmitter<Boolean>();
	
  jobs;
  names;
	
  constructor(private httpClient: HttpClient,private configloaderService: ConfigloaderService) {}
  
    public getJobsWithParam(data){
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
   
   public async getJobsWithParamLazy(data){
	var port = window.location.port;  
	var backend= "";   
	if(port=="4200"){
		backend="http://localhost";
	}  
	this.change.emit({"jobs":[],"charts":{"pie":{"values":[],"labels":[]}}});
	this.workingEvent.emit(true);		
	this.names=this.configloaderService.getAllNames();	
	
	/*for( var n in this.names){
		this.httpClient.get(backend+'/search/'+this.names[n], { params: data}).subscribe((data)=>{
			this.append.emit(data); 
		});
	}*/
	
	//TODO Use promises
	await this.callHttp(data,this.names,backend);
	 
	this.workingEvent.emit(false);
   }
   
   public getAllJobs(){
	return this.jobs;   
   }
  
   public async callHttp(data,names,backend){
	for( var n in this.names){
		this.httpClient.get(backend+'/search/'+this.names[n], { params: data}).subscribe((data)=>{
			this.append.emit(data); 
		});
	} 
   }
   
}
