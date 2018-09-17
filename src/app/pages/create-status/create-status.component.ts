import { Component, OnInit } from '@angular/core';
import {Status} from 'src/app/_modal/status'
import { ActivatedRoute, Router } from '../../../../node_modules/@angular/router';
import { CreateServiceService } from 'src/app/_services/createStatus/create-status.service';

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
  constructor(private createService: CreateServiceService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {

  }

  addStatus(status){
    this.createService.addStatus(status).subscribe(data => {
      console.log("SUCCESS");
     
      console.log(data);
    });
  }
}