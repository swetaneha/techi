import { Injectable } from '@angular/core';
import { HttpClient } from '../../../../node_modules/@angular/common/http';
import { Status }  from 'src/app/_modal/status';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class UpdateStatusService {
  private url: String;
  status: Status;

  constructor(private http:HttpClient) { 
    this.url ="http://localhost:7090/updatestatus";
  }

  public setSelectedStatus(status : Status){
    this.status=status;
  }
  
  public getSelectedStatus(){
    return this.status;
  }

  updateStatus(status: Status): Observable<any> {
    return this.http.post<String>('//localhost:7090/updatestatus',status);
  }
}