import { Injectable } from '@angular/core';

import { EmployeesService } from 'src/app/_modal/employees.service';


@Injectable({
  providedIn: 'root'
})

export class Group {

   groupId : number;
  groupadmin : EmployeesService;
  groupmembers : EmployeesService [];
  groupName : string;
  description : string;
  isArchived : string; 

  constructor( groupadmin : EmployeesService , groupmembers : EmployeesService []) {

  this.groupadmin = groupadmin ;
  this.groupmembers = groupmembers;

  }
}
