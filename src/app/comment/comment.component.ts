import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Comment} from 'src/app/comment/comment.model';
import { CommentsService } from 'src/app/service/comments.service';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {

  comments: Comment[];
  commentControl = new FormControl();


  constructor(
    private commentsService: CommentsService,
  ) { }

  ngOnInit() {

        // Load the comments on this article
        this.populateComments();

  }
  populateComments() {
    this.commentsService.getAllComment()
      .subscribe(comments => this.comments = comments);
  }

  // addComment() {
    

  //   const commentBody = this.commentControl.value;
  //   this.commentsService
  //     .add(comment)
  //     .subscribe(
  //       comment => {
  //         this.comments.unshift(comment);
  //         this.commentControl.reset('');
  //         this.isSubmitting = false;
  //       },
  //       errors => {
  //         this.isSubmitting = false;
  //         this.commentFormErrors = errors;
  //       }
  //     );
  // }

  // onDeleteComment(comment) {
  //   this.commentsService.destroy(comment.id, this.article.slug)
  //     .subscribe(
  //       success => {
  //         this.comments = this.comments.filter((item) => item !== comment);
  //       }
  //     );
  // }
}
