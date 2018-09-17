import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Comment } from 'src/app/comment/comment.model';
@Injectable({
  providedIn: 'root'
})
export class CommentsService {

  constructor(
    private http: HttpClient  ) { }

    getAllComment(): Observable<any> {
      return this.http.get('//localhost:7090/listcomment');
    }
}
