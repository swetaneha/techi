import { Component, OnInit } from '@angular/core';
import {Status} from 'src/app/_modal/status'
import { ActivatedRoute, Router } from '../../../../node_modules/@angular/router';
import { CreateServiceService } from 'src/app/_services/createStatus/create-status.service';
import { NotifierService } from 'angular-notifier';

@Component({
  selector: 'app-create-status',
  templateUrl: './create-status.component.html',
  styleUrls: ['./create-status.component.scss']
})
export class CreateStatusComponent implements OnInit {

  sta:string;
   status: Status ={
     id : null,
     status: "",
     isArchived: ""

   }

   private notifier: NotifierService;

  constructor(private createService: CreateServiceService,
    private route: ActivatedRoute,
    private router: Router,
    notifier: NotifierService) {
      this.notifier = notifier;
     }

  ngOnInit() {

  }

  addStatus(status){
    this.createService.addStatus(status).subscribe(data => {
      // console.log(data['success']);
     if(data['success']=='false')
     {
      this.showNotification( 'info', 'Status Already Exists!' )
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