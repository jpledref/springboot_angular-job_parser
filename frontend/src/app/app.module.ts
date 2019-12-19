import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { JobsComponent } from './jobs/jobs.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { SearchJobComponent } from './search-job/search-job.component'; 
import { MatInputModule } from '@angular/material';
import { MatFormFieldModule } from '@angular/material/form-field'; 
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatSidenavModule} from '@angular/material/sidenav';
import { SpinnerComponent } from './spinner/spinner.component'; 
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner'; 
import {MatTableModule} from '@angular/material/table'; 

@NgModule({
  declarations: [
    AppComponent,
    JobsComponent,
    SearchJobComponent,
    SpinnerComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    MatIconModule,	
    MatInputModule,
    MatToolbarModule,	
    MatFormFieldModule,	
    BrowserAnimationsModule,
    MatButtonModule,
    FormsModule,
    ReactiveFormsModule,
    MatSidenavModule,
    MatProgressSpinnerModule,
    MatTableModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
