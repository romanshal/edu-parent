import { Component, OnInit } from "@angular/core";
import{Post} from "../models/post";
import{PostService} from "../../../../services/post.service";
import {Subscription} from "rxjs";

@Component({
  selector: "admin-page",
  templateUrl: "./admin-page.component.html"
})

export class AdminPageComponent implements OnInit {

  public posts: Post[];

  private subscriptions: Subscription[] = [];

  constructor( private postService: PostService) {

  }

  ngOnInit() {

  }

  //  private loadBillingAccounts(): void {
  //    // Get data from BillingAccountService
  //    this.subscriptions.push(this.billingAccountService.getBillingAccounts().subscribe(accounts => {
  //      // Parse json response into local array
  //      this.billingAccounts = accounts as BillingAccount[];
  //      // Check data in console
  //      console.log(this.billingAccounts);// don't use console.log in angular :)
  //    }));
  // }

  private loadPost(): void {
    // Get data from BillingAccountService
    this.subscriptions.push(this.postService.getPosts().subscribe(posts => {
      // Parse json response into local array
      this.posts = posts as Post[];
      // Check data in console
      console.log(this.posts);// don't use console.log in angular :)
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
}
