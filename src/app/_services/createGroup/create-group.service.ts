import { Group } from 'src/app/_modal/group.service';
import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { map } from 'rxjs/operators';



const httpOptions = {
 headers: new HttpHeaders({ 'Content-Type': 'application/json'})
 };

@Injectable({
  providedIn: 'root'
})
export class CreateGroupService {

  private group: Group;
  private createGroupUrl: string;
  constructor(private http: HttpClient,
    private route: ActivatedRoute,
    private router: Router) {
    this.createGroupUrl = 'http://localhost:7090/groups';
   }

  createGroup(group: Group){
      debugger;
      this.group= group;
      const httpHeaders = new HttpHeaders({
        'Content-Type' : 'application/json',
        'Allow-Control-Allow-Origin': '*',
        'WithCredentials': 'true'
        });
      const options = { headers : httpHeaders };
      console.log(this.group);
      return this.http.post(this.createGroupUrl, group, options);

  }
}