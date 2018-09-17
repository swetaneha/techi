import { Component, OnInit } from '@angular/core';
import { Status } from 'src/app/_modal/status';
import { ActivatedRoute, Router } from '../../../../node_modules/@angular/router';
import { Location } from '@angular/common';
import { UpdateStatusService } from 'src/app/_services/updateStatus/update-status.service';

@Component({
  selector: 'app-update-status',
  templateUrl: './update-status.component.html',
  styleUrls: ['./update-status.component.scss']
})
export class UpdateStatusComponent implements OnInit {

  status : Status;

  constructor(
  private updateService: UpdateStatusService,
  private route: ActivatedRoute,
  private router: Router,
  private location: Location
  ) { }

  ngOnInit() {
    if (!sessionStorage.isLoggedIn  ) {
      this.router.navigateByUrl('/login');
    }
    this.status =this.updateService.getSelectedStatus();
    console.log(this.status);
  }
  updateStatus(status){
    console.log(status)
    this.updateService.updateStatus(status).subscribe(data => {
      console.log("SUCCESS");
     
      console.log(data);
    });

}
}