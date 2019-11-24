import { Component, OnInit } from "@angular/core";
import {Post} from "../../../layout/components/models/post";
import {PostService} from "../../../../services/post.service";
import {UserPageComponent} from "../../../layout/components/user-page/user-page.component";

@Component({
  selector: "app-add-post",
  templateUrl: "./add-post.component.html"
})
export class AddPostComponent implements OnInit {

  public post : Post;
  private PostService: PostService;
  constructor(private  postService: PostService){

  }

  ngOnInit() {
  }

  close(){
    this.postService.showAddPost=false;
  }

  addPost() {
    if (this.post.id && this.post.description) {
      this.PostService.addPost(this.post).subscribe(res => {
      });
    } else {
      alert('Tags and Description required');
    }
  }
}
