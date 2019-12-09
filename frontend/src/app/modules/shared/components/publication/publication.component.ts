import {Component, Input, OnInit} from "@angular/core";
import {Post} from "../../../layout/components/models/post";
import {PostService} from "../../../../services/post.service";
import {Comment} from "../../../layout/components/models/comment";

@Component({
  selector: "publication",
  templateUrl: "./publication.component.html",
})
export class PublicationComponent {

  @Input()
  id: number;

  @Input()
  tag: string;

  @Input()
  description: string;

  @Input()
  countLike: number;

  constructor() {
  }

}
