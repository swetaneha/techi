import { Component, OnInit } from '@angular/core';

import { Workflow } from '../../_modal/model-workflow';
import { Router, RouterModule } from '@angular/router';
import { WorkflowService } from '../../_services/workFlow/work-flow.service';


@Component({
selector: 'app-workflow',
templateUrl: './workflow.component.html',
styleUrls: ['./workflow.component.scss']
})
export class WorkflowComponent implements OnInit {

counter =0;

public workflowList: Workflow[];

constructor(private workflowService:WorkflowService,private router: Router) {} 

createWorkflow(){

this.router.navigateByUrl('/createWorkflowPage');

}


ngOnInit() {
  if (!sessionStorage.isLoggedIn  ) {
    this.router.navigateByUrl('/login');
  }

  var a = <HTMLElement>document.getElementsByClassName("selected")[2];
  a.style.backgroundColor = "#008B8B";

  this.getAllWorkflows()
}


incr(){
  if(this.counter == this.workflowList.length){
    return this.counter;
  }
  else{
    return ++this.counter;
  }
}


public getAllWorkflows(){

  this.workflowService.getAllWorkflows()
  
  .subscribe((data) => {
  
  this.workflowList =data ;
  
  console.log(data);
  
  })
  
  
  
  
  }


}