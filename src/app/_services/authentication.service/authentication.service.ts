import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { HttpHeaders } from '@angular/common/http';
import {DisplayNameService} from '../displayName/display-name.service';



@Injectable()
export class AuthenticationService {


  response: {};
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
      'withCredentials': 'true'
    })
  };
  constructor(
    private http: HttpClient
  ) { }

  login(username: string, password: string): Observable<any> {
    const url = 'http://172.16.17.170:7090/login';
    const body =  { 'userName': username, 'password': password };
    return this.http.post( url, body, this.httpOptions).pipe(
      map(response => {
        if (response) {
         return response;
        }
      }),
      catchError(this.handleError));
  }


  private handleError(error) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // return an observable with a user-facing error message
    return throwError(
      'Something bad happened; please try again later.');
  }
}


