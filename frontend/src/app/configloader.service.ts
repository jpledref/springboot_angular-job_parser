import { Injectable, Output, EventEmitter } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { TransferHttpService } from '@gorniv/ngx-transfer-http';
import { environment } from './../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ConfigloaderService {

  @Output()
  loaded = new EventEmitter<Boolean>();

  names;
  port = "";

  public init() {
    var location = window.location;
    var backend = location.protocol + '//' + location.hostname + (location.port ? ':' + location.port : '');
    var port = location.port;
    if (port == "4200" || port == "4000" || backend == "about://") {
      backend = environment.api_url;
    } else {
      backend = backend + environment.api_root;
    }
    this.http.get(backend + '/definitions/names').subscribe((data) => {
      this.names = data;
      this.loaded.emit(true);
    });
  }

  constructor(private httpClient: HttpClient, private http: TransferHttpService) { }

  public getAllNames() {
    return this.names;
  }
}
