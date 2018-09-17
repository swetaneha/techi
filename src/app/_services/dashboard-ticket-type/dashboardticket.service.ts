import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { HttpClient,HttpParams  } from '@angular/common/http';
import { TicketType } from '../../_modal/model-ticket-type';

@Injectable()
export class DashboardTicketService {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Access-Control-Allow-Origin': '*',
      'withCredentials': 'true'
    }),
    params:new HttpParams().set("page",'1').set("total", '5')

  };
  
  params = new HttpParams().set("page",'1').set("total", '5');

  constructor(private http: HttpClient) { }


  getAllTicketTypes() {
    return this.http.get<TicketType[]>("http://localhost:7090/tickettype",this.httpOptions)

}
}