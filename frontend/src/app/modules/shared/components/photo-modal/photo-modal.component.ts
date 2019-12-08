import {Component, Input, OnInit, Output} from "@angular/core";
import {Comment} from "../../../layout/components/models/comment";
import {Subscription} from "rxjs";
import {CommentService} from "../../../../services/comment.service";
import {PostService} from "../../../../services/post.service";
import {Post} from "../../../layout/components/models/post";

@Component({
  selector: "photo-modal",
  templateUrl: "./photo-modal.component.html"
})
export class PhotoModalComponent implements OnInit {

  @Input()
  postId: number;

  public post: Post;
  public comment: Comment;
  private subscriptions: Subscription[] = [];

  constructor(public postService: PostService) {
  }

  ngOnInit(): void {
    console.log(this.postId);
    // this.subscriptions.push(this.postService.getPostById(this.postId).subscribe(post => {
    //   this.post = post;
    // }, error => {
    //   console.log(error)
    // }));
  }

  close() {
    this.postService.showModal = false;
  }
}
