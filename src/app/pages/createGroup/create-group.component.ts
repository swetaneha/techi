import { Component, OnInit, ViewEncapsulation, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

import{ EmployeesService } from 'src/app/_modal/employees.service';
import { GetAllEmployessService } from 'src/app/_services/getAllEmployees/get-all-employees.service';
import {Group} from 'src/app/_modal/group.service';
import {CreateGroupService} from 'src/app/_services/createGroup/create-group.service';
import{ GetGroupsService } from 'src/app/_services/getGroups/get-groups.service';

import { NgxSpinnerService } from 'ngx-spinner';


@Component({
  selector: 'app-create-group',
  templateUrl: './create-group.component.html',
  styleUrls: ['./create-group.component.scss'],
  encapsulation: ViewEncapsulation.None 
})
 
export class CreateGroupComponent implements OnInit {

  employees : EmployeesService[];
  userForm: FormGroup;
  submitted = false;
  adminItemList = [];
  statusItemList = [];
  employeeSettings = {};
  statusSettings = {};
  adminSelectedItems = [];
  statusSelectedItems = [];  
  employeeSelectedItems = [];
  employeeItemList = [];
  adminSettings= {};
  groups: {};
  group : Group;
  a= [];
  adminDetails : EmployeesService;
  groupmembers : Array<EmployeesService> = [];
 

  constructor(
    private formBuilder: FormBuilder,
    private spinner: NgxSpinnerService,
    private route: ActivatedRoute,
    private getAllEmployessService : GetAllEmployessService,
    private getGroupsService : GetGroupsService,
    private createGroupService: CreateGroupService,
    private router: Router,
  ) {}


  ngOnInit() {

 
    this.userForm = this.formBuilder.group({
      'employeeList': ['', Validators.required],
      'adminList': ['', Validators.required],
      'groupName': ['', Validators.required],
      'groupdescription': ['', Validators.required],
      'statusList': ['', Validators.required]

    });
     
    
    this.spinner.show();
    setTimeout(() => {
    this.getAllEmployessService.getEmployee()
    .subscribe( (employees)=> { 

      this.spinner.hide();

      this.employees = employees;

      this.employees.forEach(employee => {
        this.adminItemList.push({
          'id' : employee.empId,
          'itemName' : employee.empName + "-" + employee.empId
        });
      
        this.employeeItemList.push({
          'id' : employee.empId,
          'itemName' : employee.empName + "-" + employee.empId
        });
        
      });
     });
    }, 500);

     this.statusItemList = [
      { id: 0, itemName : "Active"},
      { id: 1, itemName : "Inactive"}];

     this.employeeSettings = {
      singleSelection: false,
      text: "Select Members",
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      enableSearchFilter: true,
      badgeShowLimit: 5
    };
    
    this.adminSettings = {singleSelection: true, text: "Select Admin", enableSearchFilter: true };
    this.statusSettings = {singleSelection: true, text: "Select Status"};
    }

    onSubmit() {

      this.submitted = true;

      if (this.userForm.invalid) {
          return;
      }

      this.groups = this.userForm.value;

       this.adminDetails = new EmployeesService();

       this.adminDetails['empId'] = this.groups['adminList'][0]['id'];
       

        for(var i=0 ; i < this.groups['employeeList'].length; i++)
        {
          this.groupmembers[i] = new EmployeesService();
          this.groupmembers[i]['empId'] = this.groups['employeeList'][i]['id'];

        }

        this.adminDetails['empId'] = this.groups['adminList'][0]['id'];
        this.group = new Group( this.adminDetails , this.groupmembers);
        

        this.group['groupName']= this.groups['groupName'];
        this.group['description']= this.groups['groupdescription'];
        this.group['isArchived']= this.groups['statusList'][0]['id'];
        
        this.createGroupService.createGroup(this.group).subscribe((res)=> {

            this.router.navigateByUrl('/admin');
        });

  }
}
