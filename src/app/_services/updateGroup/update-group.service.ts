import { Injectable } from '@angular/core';
import { Group } from 'src/app/_modal/group.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { map } from 'rxjs/operators';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json'})
  };

  
@Injectable({
  providedIn: 'root'
})
export class UpdateGroupService {

  private group : Group;
  private updateGroupUrl : string;

  constructor(private http: HttpClient,
    private route: ActivatedRoute,
    private router: Router) {}

    updateGroup(group: Group){
      
      this.group = group;
      this.updateGroupUrl = 'http://localhost:7090/group/update/' + this.group['groupId'] ;

      const httpHeaders = new HttpHeaders({
        'Content-Type' : 'application/json',
        'Allow-Control-Allow-Origin': '*',
        'WithCredentials': 'true'
        });
      const options = { headers : httpHeaders };

      return this.http.put(this.updateGroupUrl, group, options);

  }
}
