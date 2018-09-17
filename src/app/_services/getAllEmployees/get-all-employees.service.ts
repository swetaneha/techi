import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';


import {EmployeesService} from '../../_modal/employees.service';
import {AdminConfigComponent} from '../../pages/adminConfig/admin-config.component';

const httpOptions = {

  headers: new HttpHeaders({ 'Content-Type': 'application/json'})
  
  };  

@Injectable({
  providedIn: 'root'
})

export class GetAllEmployessService {

private url: string;

constructor(private http:HttpClient) { 

this.url = "http://localhost:7090/employee";

}


public getEmployee() {

return this.http.get<EmployeesService[]>(this.url);

  }
}