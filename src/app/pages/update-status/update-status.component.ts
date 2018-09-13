import { Component, OnInit } from '@angular/core';
import { Status } from 'src/app/_modal/status';
import { ActivatedRoute, Router } from '../../../../node_modules/@angular/router';
import { Location } from '@angular/common';
import { UpdateStatusService } from 'src/app/_services/updateStatus/update-status.service';
import { NotifierService } from 'angular-notifier';

@Component({
  selector: 'app-update-status',
  templateUrl: './update-status.component.html',
  styleUrls: ['./update-status.component.scss']
})
export class UpdateStatusComponent implements OnInit {

  status : Status;

  statusSettings = {};
  selectedStatus={}; 
  statusItemList = [
    { id: 0, itemName : "Active"},
    { id: 1, itemName : "Inactive"}];

  private notifier: NotifierService;


  constructor(
  private updateService: UpdateStatusService,
  private route: ActivatedRoute,
  private router: Router,
  private location: Location,
  notifier: NotifierService
  ) {
    this.notifier = notifier;
   }

  ngOnInit() {
    if (!sessionStorage.isLoggedIn  ) {
      this.router.navigateByUrl('/login');
    }
    this.status =this.updateService.getSelectedStatus();
    this.statusSettings = {singleSelection: true, text: "Select Status"};
  }
  updateStatus(status){
    console.log(status)
    this.updateService.updateStatus(status).subscribe(data => {
      console.log("SUCCESS");
     
      if(data['success']=='false')
     {
      this.showNotification( 'info', 'Status in use!' )
     }
     else{
     this.router.navigateByUrl('/liststatus');
     }
    });

}

public showNotification( type: string, message: string ): void {
  // console.log("success");
  // console.log( type)
  this.notifier.notify( type, message );
}
}