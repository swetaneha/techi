import { Component, OnInit } from '@angular/core';
import { DashboardTicketService } from '../../_services/dashboard-ticket-type/dashboardticket.service';
import { TicketType } from '../../_modal/model-ticket-type';
import {SharedTicketTypeService} from '../../_services/shared-ticket-type/sharedtickettype.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-tickettypedashboard',
  templateUrl: './tickettypedashboard.component.html',
  styleUrls: ['./tickettypedashboard.component.scss']
})
export class TicketTypeDashboardComponent implements OnInit {
  
  private ticketType:TicketType;
  private ticketTypeList: TicketType[];
  counter =0;
  constructor(private dashboardTicketService:DashboardTicketService,private sharedTicketTypeService: SharedTicketTypeService,private router: Router ) {} 

  ngOnInit() {
    if (!sessionStorage.isLoggedIn  ) {
      this.router.navigateByUrl('/login');
    }

    var a = <HTMLElement>document.getElementsByClassName("selected")[4];
    a.style.backgroundColor = "#008B8B";
    this.getAllTicketTypes();

 

  }

  incr(){
    if(this.counter == this.ticketTypeList.length){
      return this.counter;
    }
    else{
      return ++this.counter;
    }
  }

  public getAllTicketTypes(){
    this.dashboardTicketService.getAllTicketTypes()
      .subscribe((data) => {
      this.ticketTypeList =data ;
      
    })
  }
 
  public callCreate(ticketType:TicketType,reachBy:number){
    if(reachBy==0)
     ticketType=new TicketType(0,"","","","Active");
  
    this.sharedTicketTypeService.setter(ticketType,reachBy);
  
  }

}