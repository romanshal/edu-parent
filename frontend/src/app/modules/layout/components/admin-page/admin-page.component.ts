import { Component, OnInit } from "@angular/core";
import{Post} from "../models/post";
import{PostService} from "../../../../services/post.service";
import {Subscription} from "rxjs";

@Component({
  selector: "admin-page",
  templateUrl: "./admin-page.component.html"
})

export class AdminPageComponent {

  public posts: Post[];
  public page: number = 1;

  private subscriptions: Subscription[] = [];

  constructor( private postService: PostService) {

  }

  private loadPost(): void {
    // Get data from BillingAccountService
    this.subscriptions.push(this.postService.getPosts(this.page).subscribe(posts => {
      // Parse json response into local array
      this.posts = posts as Post[];
      // Check data in console
      console.log(this.posts);// don't use console.log in angular :)
    },error => {
      console.log(error)
    }));
  }

  public _deletePost(postId: number): void{
    this.subscriptions.push(this.postService.deletePost(postId).subscribe(()=>{
      this._updatePost();
    }))
  }

  public _updatePost(): void {
    this.loadPost();
  }

  public nextPage(): void {
    this.subscriptions.push(this.postService.getPosts(this.page).subscribe((posts: Post[]) => {
      for (let post of posts) {
        this.posts.push(post);
      }
    }));
    this.page = this.page + 1;
  }
}
