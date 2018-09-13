import { Component, OnInit } from '@angular/core';
import {SharedTicketTypeService} from '../../_services/shared-ticket-type/sharedtickettype.service';
import { TicketType } from '../../_modal/model-ticket-type';
import { Router } from '@angular/router';
import {WorkFlowType} from '../../_modal/model-status';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-create-ticket-type',
  templateUrl: './create-ticket-type.component.html',
  styleUrls: ['./create-ticket-type.component.scss']
})
export class CreateTicketTypeComponent implements OnInit {

public  ticketType:TicketType;
public workFlowList:WorkFlowType[];
  archiveList:String[] = ['Active','InActive'];
  reachBy:number;
  

 // submitted = false;
  
  constructor(private sharedTicketTypeService:SharedTicketTypeService,private router: Router,private route: ActivatedRoute,) { 
    this.reachBy=+this.route.snapshot.paramMap.get('id');

  }
  ngOnInit(){
    this.ticketType=this.sharedTicketTypeService.getterTicketType();
    // this.reachBy=this.sharedTicketTypeService.getterReachBy();
    //this.reachBy=+this.route.snapshot.paramMap.get('id');
    console.log(this.ticketType);
    //console.log(this.reachBy);
    if(this.reachBy==0){
      console.log(this.reachBy);
      this.ticketType=new TicketType(0,"","","","");
    }
    else{
console.log(this.reachBy);
    }
    this.sharedTicketTypeService.getWorkFlow()
    .subscribe(data=>{
      console.log(data);
      this.workFlowList=data;
    })
   
  }

  


  updateTicketType(ticketType:TicketType)
{
  
  this.sharedTicketTypeService.updateTicketType(ticketType).
  subscribe(data=>{
    this.router.navigateByUrl('/dashboardtickettype');
  })
}



createTicketType(ticketType:TicketType)
{
  console.log("hello");
  console.log(this.ticketType);
  this.sharedTicketTypeService.createTicketTypes(this.ticketType).
  subscribe(data=>{
    this.router.navigateByUrl('/dashboardtickettype');
  })
}
}