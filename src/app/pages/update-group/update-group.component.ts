import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { NgxSpinnerService } from 'ngx-spinner';

import { Group } from 'src/app/_modal/group.service';
import { GetGroupsService } from 'src/app/_services/getGroups/get-groups.service';
import { GetAllEmployessService } from 'src/app/_services/getAllEmployees/get-all-employees.service';
import { EmployeesService } from 'src/app/_modal/employees.service';
import { GetGroupService } from '../../_services/getGroup/get-group.service';
import { UpdateGroupService } from '../../_services/updateGroup/update-group.service';


@Component({
  selector: 'app-update-group',
  templateUrl: './update-group.component.html',
  styleUrls: ['./update-group.component.scss']
})
export class UpdateGroupComponent implements OnInit {

  group: Group;
  employees : EmployeesService[];
  adminItemList = [];
  adminSelectedItems = [];
  adminSettings= {};
  submitted = false;
  userForm: FormGroup;
  statusSettings = {};
  employeeSettings = {};
  employeeSelectedItems = [];
  employeeItemList = [];
  statusItemList = [];
  statusSelectedItems = []; 
  groups: {};
  id =0;
  adminDetails : EmployeesService;
  groupmembers : Array<EmployeesService> = [];

  constructor(
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private getGroupsService: GetGroupsService,
    private updateGroupService : UpdateGroupService,
    private getGroupService : GetGroupService,
    private spinner: NgxSpinnerService,
    private getAllEmployessService : GetAllEmployessService,
    private location: Location,
    private router: Router,
  ) { }

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
            
          
            this.id = +this.route.snapshot.paramMap.get('id');

            this.getGroupService.getGroup(this.id)
            .subscribe( (group)=> { 
                this.group = group;
                this.spinner.hide();
             
                this.adminSelectedItems = [{
                  id: this.group['groupadmin']['empId'],
                  itemName: this.group['groupadmin']['empName'] + "-" + this.group['groupadmin']['empId']
                }];
            
                for(var i=0 ; i < this.group['groupmembers'].length; i++)
                this.employeeSelectedItems.push({
                  'id' : this.group['groupmembers'][i]['empId'],
                  'itemName' : this.group['groupmembers'][i]['empName'] + "-" + this.group['groupmembers'][i]['empId']
                });

                if(this.group['isArchived'] === '0'){
                  this.statusSelectedItems = [{
                    id: 0,
                    itemName: "Active"
                  }]
                }
                else{
                  this.statusSelectedItems = [{
                    id: 1,
                    itemName: "Inactive"
                  }]
                }
        });

    this.getAllEmployessService.getEmployee()
    .subscribe( (employees)=> { 
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
     this.statusItemList = [
      { id: 0, itemName : "Active"},
      { id: 1, itemName : "Inactive"}];

    this.adminSettings = {singleSelection: true, text: "Select Admin", enableSearchFilter: true };

     this.employeeSettings = {
      singleSelection: false,
      text: "Select Members",
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      enableSearchFilter: true,
      badgeShowLimit: 5
    };
    
    this.statusSettings = {singleSelection: true, text: "Select Status"};
    }, 500);
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
      
      this.group['groupId'] = this.id;
      this.group['groupName'] = this.groups['groupName'];
      this.group['description'] = this.groups['groupdescription'];
      this.group['isArchived'] = this.groups['statusList'][0]['id'];
      

      this.updateGroupService.updateGroup(this.group).subscribe((res)=> {

          this.router.navigateByUrl('/admin');
      });

  }

}
