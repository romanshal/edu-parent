import { Component, OnInit } from "@angular/core";
import {PostService} from "../../../../services/post.service";
import {Post} from "../models/post";
import {Subscription} from "rxjs";

@Component({
  selector: "user-page",
  templateUrl: "./user-page.component.html"
})
export class UserPageComponent implements OnInit{

  private subscriptions: Subscription[] = [];

  public posts: Post[];

  constructor(public  postService: PostService){
  }

  public openDialog() {
    this.postService.showAddPost = true;
  }

  ngOnInit(): void {
      // Get data from BillingAccountService
      this.subscriptions.push(this.postService.getPosts().subscribe(posts => {
        // Parse json response into local array
        this.posts = posts as Post[];
        // Check data in console
        console.log(this.posts);// don't use console.log in angular :)
      },error => {
        console.log(error)
      }));
  }
}
