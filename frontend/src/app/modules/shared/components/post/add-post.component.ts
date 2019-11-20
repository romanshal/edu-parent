import { Component, OnInit } from "@angular/core";
import { ViewChild, ElementRef } from '@angular/core';
import {Post} from "../../../layout/components/models/post";
import {PostService} from "../../../../services/post.service";

@Component({
  selector: "app-add-post",
  templateUrl: "./add-post.component.html"
})
export class AddPostComponent implements OnInit {

  @ViewChild('closeBtn') closeBtn: ElementRef;
  public post : Post;
  private PostService: PostService;
  constructor( ) {}

  ngOnInit() {

  }
  public showAddPost: boolean = false;

  addPost() {
    if (this.post.tags && this.post.description) {
      this.PostService.addPost(this.post).subscribe(res => {
        this.closeBtn.nativeElement.click();
      });
    } else {
      alert('Tags and Description required');
    }
  }
}
