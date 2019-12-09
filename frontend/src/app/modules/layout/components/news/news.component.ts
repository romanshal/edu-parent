import {Component, OnDestroy, OnInit} from "@angular/core";
import {Subscription} from "rxjs";
import {Post} from "../models/post";
import {PostService} from "../../../../services/post.service";

@Component({
  selector: "news-page",
  templateUrl: "./news.component.html"
})
export class NewsPageComponent implements OnInit,OnDestroy{

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

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

}
