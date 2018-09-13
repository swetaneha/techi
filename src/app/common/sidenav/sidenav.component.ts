import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss']
})
export class SidenavComponent implements OnInit {

  constructor(
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
  }

  public selectedAdmin(){

    this.router.navigateByUrl('admin');
  }

  
  public selectedWorkflow(){

    this.router.navigateByUrl('workflowDisplay');
  }

  public selectedStatus(){

    this.router.navigateByUrl('liststatus');
  }

  public selectedTickeType(){

    this.router.navigateByUrl('dashboardtickettype');
  }

}
