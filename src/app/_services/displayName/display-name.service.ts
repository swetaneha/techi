import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { EmpName } from '../../_modal/empName';
import { Observable, throwError } from 'rxjs';
@Injectable()
export class DisplayNameService {

  username: string;

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
      'withCredentials': 'true'
    })
  };

  constructor(private http: HttpClient) { }
  displayName(username: string) {
    const url = 'http://localhost:7090/empName/' + username;
    return this.http.get<EmpName>(url);
    }
    public setUsername(username: string) {
      this.username = username;
    }
    public getUsername() {
      return this.username;
    }



}
