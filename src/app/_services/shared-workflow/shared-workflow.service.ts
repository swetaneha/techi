import { Injectable } from '@angular/core';


import { HttpClient,HttpParams } from '@angular/common/http';

import { WorkflowCreate } from '../../_modal/model-workflow-create';

import { Router } from '@angular/router';




@Injectable()


export class SharedWorkflowService {

  constructor(private http:HttpClient,private router:Router) { }

  public workflow:WorkflowCreate;

  createWorkflow(workflow:WorkflowCreate) {

    console.log(workflow);
    

    return this.http.post("http://localhost:7090/workflow",workflow)
    
    }


}