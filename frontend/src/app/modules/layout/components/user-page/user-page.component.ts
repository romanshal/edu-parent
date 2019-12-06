import {Component, Input, OnInit, Output} from "@angular/core";
import {PostService} from "../../../../services/post.service";
import {Post} from "../models/post";
import {Subscription} from "rxjs";
import {User} from "../models/user";
import {UserService} from "../../../../services/user.service";

@Component({
  selector: "user-page",
  templateUrl: "./user-page.component.html"
})
export class UserPageComponent implements OnInit {

  private subscriptions: Subscription[] = [];

  public posts: Post[];
  public user: User;

  constructor(public  postService: PostService,
              public userService: UserService) {
  }

  public openDialog() {
    this.postService.showAddPost = true;
  }

  ngOnInit(): void {
    // Get data from BillingAccountService
    this.subscriptions.push(this.postService.getPosts().subscribe(posts => {
      // Parse json response into local array
      this.posts = posts;
      // Check data in console
      console.log(this.posts);// don't use console.log in angular :)
    }, error => {
      console.log(error)
    }));
    // this.subscriptions.push(this.userService.getUsers().subscribe(getUser=>
    // this.user=getUser;
    // ));
  }

  @Input()
  description: string;
}
