import { Injectable } from '@angular/core';

import { HttpHeaders } from '@angular/common/http';

import { HttpClient} from '@angular/common/http';

import { Workflow } from '../../_modal/model-workflow';

import { Status } from '../../_modal/model-workFlow-Status';

@Injectable({
  providedIn: 'root'
})
export class WorkflowService {


  httpOptions = {

    headers: new HttpHeaders({
    
    'Content-Type': 'application/json',
    
    'Access-Control-Allow-Origin': '*',
    
    'withCredentials': 'true',
    
    })
  }

  constructor(private http: HttpClient) { }





  getAllWorkflows() {
  
  return this.http.get<Workflow[]>("http://localhost:7090/workflows",this.httpOptions)
  
  }

  getAllStatuses() {
  
  return this.http.get<Status[]>("http://localhost:7090/statuses",this.httpOptions)
    
  }

  }