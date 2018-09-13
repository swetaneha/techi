import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';


import {Group} from 'src/app/_modal/group.service';


const httpOptions = {

headers: new HttpHeaders({ 'Content-Type': 'application/json'})

};


@Injectable({

providedIn: 'root'

})

export class GetGroupsService {

private url: string;
private urlforgroup: string; 
group : Group;
id;

constructor(private http:HttpClient) { 

this.url = "http://localhost:7090/group";

}


public getGroup () {

return this.http.get<Group[]>(this.url);

}

public setSelectedGroup(group : Group )
{
  this.group= group;
}

public getSelectedGroup() {

    return this.group;
}

}
