import { Component, OnInit, ViewEncapsulation } from '@angular/core';


import { WorkflowCreate } from '../../_modal/model-workflow-create';
import { Status } from '../../_modal/model-workFlow-Status';

import { Router, RouterModule } from '@angular/router';
import { DropEvent } from 'ng-drag-drop';
import { WorkflowService } from '../../_services/workFlow/work-flow.service';
import { SharedWorkflowService } from '../../_services/shared-workflow/shared-workflow.service';



@Component({
  selector: 'app-workflow-create',
  templateUrl: './workflow-create.component.html',
  styles: [`
  div.scroll-list {
    overflow: auto;
    max-height: 70vh;
  }
  .drag-over {
    border: #ff525b dashed 2px;
  }
  .drag-hint {
    border: #ffc100 dashed 2px;
  }
  .drag-target-border {
    border: #00bfff dashed 2px;
  }
  .drag-target-border-green {
    border: #3c763d dashed 2px;
  }
`],
encapsulation: ViewEncapsulation.None
})
export class WorkflowCreateComponent implements OnInit{
  list1 = [ ];

  listTemp= [ ];

  list2 = [ ];
  temp = [ ];
  temp2 =[ ];
  // public dragList = [];
  // public dropList = [];
  public workflow:WorkflowCreate;
  
  statusList: Status[];

  // public selectedStatusList:Status[];
 constructor(private sharedWorkflowService:SharedWorkflowService,private router: Router,private statusService:WorkflowService) { }

  onSubmit() {

    console.log("hello");
    
    console.log(this.workflow);

    console.log(this.list2);

    //console.log(this.listTemp);
    var k=0;
    for (let i of this.list2){
      this.temp[k]= i['id']
      k++
    }




  

    for (var j=0;j<this.list2.length;j++){

      
      this.temp2[j]=j+1;
      
    }
    console.log(this.temp2);


    this.workflow.status=this.temp;
    this.workflow.ranks=this.temp2;

    this.sharedWorkflowService.createWorkflow(this.workflow).

    
    
    subscribe(data=>{
    
    console.log(data);

    


    
    this.router.navigateByUrl('/workflowDisplay');
    
    })
    
    }




  ngOnInit() {
    if (!sessionStorage.isLoggedIn  ) {
      this.router.navigateByUrl('/login');
    }


    this.workflow=new WorkflowCreate("","");
    this.getAllStatuses()
  }

  public getAllStatuses(){

    
    this.statusService.getAllStatuses().subscribe((data) => {
      data.forEach(itemList => {
        this.list1.push({
          id: itemList.statusId,
          name: itemList.status
        });
      });
     });
    }

 
    // for (let status of statusList) {
    //   console.log(status); 
    // }
  
    // onList1Drop(e: DropEvent) {
    //   this.dragList.push(e.dragData);
    //   this.removeItem(e.dragData, this.dropList);
    // }
  
    // onList2Drop(e: DropEvent) {
    //   this.dropList.push(e.dragData);
    //   this.removeItem(e.dragData, this.dragList);
    // }
  
    // removeItem(item: any, list: Array<any>) {
    //   let index = list.map(function (e) {
    //     return e.status
    //   }).indexOf(item.status);
    //   list.splice(index, 1);
    // }


  
  onList1Drop(e: DropEvent) {
    this.list1.push(e.dragData);
    this.removeItem(e.dragData, this.list2)
  }

  onList2Drop(e: DropEvent) {
    this.list2.push(e.dragData);
    this.removeItem(e.dragData, this.list1)
  }

  removeItem(item: any, list: Array<any>) {
    let index = list.map(function (e) {
      return e.name
    }).indexOf(item.name);
    list.splice(index, 1);
  }

  

}