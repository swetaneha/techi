import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Status } from 'src/app/_modal/status';

@Injectable({
  providedIn: 'root'
})
export class CreateServiceService {

  constructor(private http: HttpClient) { }

  getAllStatus(): Observable<any> {
    return this.http.get('//localhost:7090/liststatus');
  }

  addStatus(stat: Status): Observable<any> {
    return this.http.post<String>('//localhost:7090/status',stat);
  }
}