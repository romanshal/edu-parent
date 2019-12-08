import {Component, Input, OnInit, Output, TemplateRef} from "@angular/core";
import {PostService} from "../../../../services/post.service";
import {Post} from "../models/post";
import {Subscription} from "rxjs";
import {User} from "../models/user";
import {StorageService} from "../../../../services/storage.service";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {Comment} from "../models/comment";

@Component({
  selector: "user-page",
  templateUrl: "./user-page.component.html"
})
export class UserPageComponent implements OnInit {

  public comment: Comment;
  private subscriptions: Subscription[] = [];
  public posts: Post[];
  public post: Post;
  public user: User;
  public modalRef: BsModalRef;

  constructor(public  postService: PostService,
              public storageService: StorageService,
              private modalService: BsModalService,) {
  }

  public openDialog() {
    this.postService.showAddPost = true;
  }

  ngOnInit(): void {
    this.subscriptions.push(this.postService.getPostById().subscribe(posts => {
      this.posts = posts;
      this.posts.forEach(post => {this.post=post})
    }, error => {
      console.log(error)
    }));
  }

  public _openPostModal(template: TemplateRef<any>): void {
    this.modalRef = this.modalService.show(template);
  }

  public _closeModal(): void {
    this.modalRef.hide();
  }
}
