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
import { MatSidenavModule } from '@angular/material/sidenav';
import { SpinnerComponent } from './spinner/spinner.component';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatTableModule } from '@angular/material/table';
import { ChartPieComponent } from './chart-pie/chart-pie.component';
import { ChartsModule } from 'ng2-charts';
import { MatSortModule } from '@angular/material/sort';
import { FlexLayoutModule } from "@angular/flex-layout";
import { BignumberComponent } from './bignumber/bignumber.component';
import { ConfigloaderService } from './configloader.service';

import { TransferHttpModule, TransferHttpService } from '@gorniv/ngx-transfer-http';
import { TransferHttpCacheModule } from '@nguniversal/common';

@NgModule({
  declarations: [
    AppComponent,
    JobsComponent,
    SearchJobComponent,
    SpinnerComponent,
    ChartPieComponent,
    BignumberComponent
  ],
  imports: [
    FlexLayoutModule,
    BrowserModule.withServerTransition({ appId: 'serverApp' }),
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
    MatTableModule,
    ChartsModule,
    MatSortModule,
    TransferHttpModule,
    TransferHttpCacheModule,
  ],
  providers: [ConfigloaderService, TransferHttpService],
  bootstrap: [AppComponent]
})
export class AppModule { }
