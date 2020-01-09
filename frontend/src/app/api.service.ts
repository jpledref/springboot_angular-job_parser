import { Injectable, Output, EventEmitter } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { ConfigloaderService } from './configloader.service';

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
  isWorking=false;
	
  constructor(private httpClient: HttpClient,private configloaderService: ConfigloaderService) {}
  
    public getJobsWithParam(data){
	if(this.isWorking)return;   
	var port = window.location.port;  
	var backend= "";   
	if(port=="4200"){
		backend="http://localhost";
	}  
	this.change.emit({"jobs":[],"charts":{"pie":{"values":[],"labels":[]}}});
	this.isWorking=true;
	this.workingEvent.emit(true);
	this.httpClient.get(backend+'/jobs', { params: data}).subscribe((data)=>{
	      this.change.emit(data);	      
	      this.workingEvent.emit(false);		
	      this.isWorking=false;
	    });
   }
   
   public async getJobsWithParamLazy(data){
	if(this.isWorking)return;   
	var port = window.location.port;  
	var backend= "";   
	if(port=="4200"){
		backend="http://localhost";
	}  
	this.jobs=[];
	this.change.emit({"jobs":[],"charts":{"pie":{"values":[],"labels":[]}}});
	this.workingEvent.emit(true);        
	this.isWorking=true;	
	this.names=this.configloaderService.getAllNames();	
	
	await Promise.all(this.callHttp(data,this.names,backend))
			.then(data=>{
				this.workingEvent.emit(false);
				this.isWorking=false;	
			});
   }
   
   public getAllJobs(){
	return this.jobs;   
   }
  
   public callHttp(data,names,backend){
	let ret=[];   
	for( var n in this.names){
		var promise =new Promise((resolve,reject) => {
			this.httpClient.get(backend+'/search/'+this.names[n], { params: data}).toPromise().then(data=>{resolve();this.append.emit(data);})
		});
		ret.push(promise);
	}
	return ret	;
   }
   
}
