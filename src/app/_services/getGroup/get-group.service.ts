import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

import {Group} from 'src/app/_modal/group.service';

const httpOptions = {  headers: new HttpHeaders({ 'Content-Type': 'application/json'}) };

@Injectable({
  providedIn: 'root'
})

export class GetGroupService {

      private url: string;
      group : Group;

      constructor( private http:HttpClient , private route: ActivatedRoute ) {     

       }

        public getGroup(id) {

         
          this.url = "http://localhost:7090/group/" + id;

          return this.http.get<Group>(this.url);
          
        }
}
