import {Component, OnInit} from "@angular/core";
import {PostService} from "../../../../services/post.service";

@Component({
  selector: "photo-modal",
  templateUrl: "./photo-modal.component.html"
})
export class PhotoModalComponent implements OnInit{

  ngOnInit() {
  }
  constructor(private  postService: PostService){

  }

  close(){
    this.postService.showModal=false;
  }
}
