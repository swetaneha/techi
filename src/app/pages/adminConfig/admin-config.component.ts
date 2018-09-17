import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit ,ChangeDetectorRef} from '@angular/core';
import { GetGroupsService } from 'src/app/_services/getGroups/get-groups.service';
import {Group} from 'src/app/_modal/group.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { GetGroupService } from '../../_services/getGroup/get-group.service';

@Component({
  selector: 'app-admin-config',
  templateUrl: './admin-config.component.html',
  styleUrls: ['./admin-config.component.scss']
})
export class AdminConfigComponent implements OnInit {

  serialNumber: number;
  edit: number;
  groups : Group[];
  group : Group;
  counter = 0;
  v={};
  
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private spinner: NgxSpinnerService,
    private getGroupsService : GetGroupsService,
    private getGroupService : GetGroupService,
    private changeDetectorRef : ChangeDetectorRef
  ) { }

  ngOnInit() {
    
    if (!sessionStorage.isLoggedIn  ) {
      this.router.navigateByUrl('/login');
    }

      this.spinner.show();
      setTimeout(() => {

        var a = <HTMLElement>document.getElementsByClassName("selected")[1];
         a.style.backgroundColor = "#008B8B";

          this.getGroupsService.getGroup()
           .subscribe( (groups)=> { 
                this.spinner.hide();
                this.groups = groups;
        });
      }, 500);
    }

   
     addGroup() {
      this.router.navigateByUrl('group');
    }
 }