import {Component, Input} from "@angular/core";

@Component({
  selector: "comment-modal",
  templateUrl: "./comment-modal.component.html",
  styleUrls: ['./comment-modal.component.css']
})
export class CommentModalComponent {

  @Input()
  userName: string;

  @Input()
  content: string;

  constructor() {
  }


}
