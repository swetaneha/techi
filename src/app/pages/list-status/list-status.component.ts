import { Component, OnInit } from '@angular/core';
import {AdminConfigComponent } from 'src/app/pages/adminConfig/admin-config.component';

import {Status} from 'src/app/_modal/status';
import { ActivatedRoute, Router } from '../../../../node_modules/@angular/router';
import { CreateServiceService } from 'src/app/_services/createStatus/create-status.service';
import { UpdateStatusService } from 'src/app/_services/updateStatus/update-status.service';
@Component({
  selector: 'app-list-status',
  templateUrl: './list-status.component.html',
  styleUrls: ['./list-status.component.scss']
})
export class ListStatusComponent implements OnInit {
 
  statusList: Array<Status>;
  counter =0;
  status: Status;
 
  constructor(private createService: CreateServiceService,
  private updateService: UpdateStatusService,
  private route: ActivatedRoute,
  private router: Router) {  }


  ngOnInit() {
    if (!sessionStorage.isLoggedIn  ) {
      this.router.navigateByUrl('/login');
    }

    var a = <HTMLElement>document.getElementsByClassName("selected")[3];
    a.style.backgroundColor = "#008B8B";
    
    this.createService.getAllStatus().subscribe(data => {
      this.statusList =  data;
    });
  }

  incr(){
    if(this.counter == this.statusList.length){
      return this.counter;
    }
    else{
      return ++this.counter;
    }
  }

  updateStatus(status) {

    this.updateService.setSelectedStatus(status);
    
    this.router.navigateByUrl('updateStatus');
    
    }
}