import {Component, EventEmitter, Input, OnInit, Output} from "@angular/core";
import {Post} from "../../../layout/components/models/post";
import {PostService} from "../../../../services/post.service";

@Component({
  selector: "publication",
  templateUrl: "./publication.component.html",
})
export class PublicationComponent {

  publication : Post;

  public showModal: boolean=false;

  constructor(public  postService: PostService){

  }
  public openDialog() {
    this.postService.showModal = true;
  }

  @Input()
  description: string;
  @Input()
  tag: string;
}
