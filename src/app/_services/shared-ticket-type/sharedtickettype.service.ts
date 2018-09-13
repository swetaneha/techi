import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { HttpClient,HttpParams  } from '@angular/common/http';
import {TicketType } from '../../_modal/model-ticket-type';
import {WorkFlowType} from '../../_modal/model-status';
import { Router } from '@angular/router';

@Injectable()
export class SharedTicketTypeService {

  
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Access-Control-Allow-Origin': '*',
      'withCredentials': 'true'
    })
    

  };
  constructor(private http:HttpClient,private router:Router) { }
  
  public ticketType:TicketType;
  reachBy:number;
  path:string;

  createTicketTypes(ticketType:TicketType) {
    console.log(ticketType);
    
    return this.http.post("http://localhost:7090/tickettype",ticketType)
  }


  updateTicketType(ticketType:TicketType) {
    console.log(this.ticketType);
    
    return this.http.put("http://localhost:7090/tickettype",this.ticketType)
    
  }

  getWorkFlow()
  {
    return this.http.get<WorkFlowType[]>("http://localhost:7090/workflows")
  }


  setter(ticketType:TicketType,reachBy:number){

    this.ticketType=ticketType;
    this.reachBy=reachBy;
    this.path="/createtickettype/"+reachBy;
    //console.log(this.path);
    this.router.navigateByUrl("createtickettype/"+reachBy);
    }
    
    getterTicketType(){
    //console.log(this.ticketType);
    return this.ticketType;
    
    }

    getterReachBy(){
    
      return this.reachBy;
      
      }
    




}