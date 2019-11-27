import { Component, OnInit } from "@angular/core";
import {Post} from "../../../layout/components/models/post";
import {PostService} from "../../../../services/post.service";
import {FormGroup, NgModel} from "@angular/forms";
import {Subscription} from "rxjs";

@Component({
  selector: "app-add-post",
  templateUrl: "./add-post.component.html"
})
export class AddPostComponent implements OnInit {

  public post: Post = new Post();
  private PostService: PostService;
  private subscriptions: Subscription[] = [];

  constructor(private  postService: PostService) {

  }

  ngOnInit() {
  }

  close() {
    this.postService.showAddPost = false;
  }

  addPost(description: NgModel, userId: string): void {
    const fd = new FormData();
    this.post.userId = userId;
    fd.append('description', this.post.description);
    // this.post.id=20;
    // this.post.userId = userId;
    // this.post.description=description;
    // this.PostService.savePost(this.post).subscribe(res => {
    // });
    this.subscriptions.push(this.postService.savePost(fd).subscribe(() => {
    }));
  }
}
