import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Sort } from '@angular/material/sort';

@Component({
  selector: 'app-jobs',
  templateUrl: './jobs.component.html',  	
  styleUrls: ['./jobs.component.css']
})
export class JobsComponent implements OnInit {

  jobs;
  defJobs;
  displayedColumns: string[] = ['title', 'enterprise', 'description', 'siteSource', 'location', 'salary'];	
  columnsToDisplay: string[] = this.displayedColumns.slice();	
  sort:Sort={active:"title",direction:"asc"};	
	
  constructor(private apiService: ApiService) { }

  ngOnInit() {	  
	this.apiService.change.subscribe(data=>{	
		this.jobs=data.jobs;
		this.defJobs=data.jobs;
		this.sortData(this.sort);
	});	
  }
  
   sortData(sort: Sort) {
    if(this.jobs===undefined)return;
    this.sort=sort;		   
    const data = this.jobs.slice();
    if (!sort.active || sort.direction === '') {
      this.jobs = this.defJobs;
      return;
    }

    this.jobs = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'title': return compare(a.title, b.title, isAsc);
        case 'enterprise': return compare(a.enterprise, b.enterprise, isAsc);
        case 'description': return compare(a.description, b.description, isAsc);
        case 'siteSource': return compare(a.siteSource, b.siteSource, isAsc);
        case 'location': return compare(a.location, b.location, isAsc);
        case 'salary': return compare(a.salary, b.salary, isAsc);
        default: return 0;
      }
    });
  }
    
}

function compare(a: number | string, b: number | string, isAsc: boolean) { 	
	if(typeof(a) == "string" && typeof(b) == "string"){
		if((a==null || a=="") && (b!=null && b!="")){return 1;}
		if((a==null || a=="") && (b==null || b=="")){return 0;}
		if((b==null || b=="") && (a!=null || a!="")){return -1;}			
		return (a.toUpperCase() < b.toUpperCase() ? -1 : 1) * (isAsc ? 1 : -1);
	}else if(typeof(a) == "number" && typeof(b) == "number"){
		 return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
	}else{
		 return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
	} 
}
