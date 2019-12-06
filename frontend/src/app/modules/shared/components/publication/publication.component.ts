import {Component, Input, OnInit} from "@angular/core";
import {Post} from "../../../layout/components/models/post";
import {PostService} from "../../../../services/post.service";

@Component({
  selector: "publication",
  templateUrl: "./publication.component.html",
})
export class PublicationComponent {

  @Input()
  tag: string;

  @Input()
  description: string;

  @Input()
  countLike: number;

  constructor(public  postService: PostService) {
  }

  public openDialog() {
    this.postService.showModal = true;
  }
}
