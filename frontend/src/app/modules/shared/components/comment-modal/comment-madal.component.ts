import {Component, Input} from "@angular/core";
import {Data} from "@angular/router";

@Component({
  selector: "comment-modal",
  templateUrl: "./comment-modal.component.html",
  styleUrls: ['./comment-modal.component.css']
})
export class CommentModalComponent {

  format: string = 'd MMMM, h:mm:ss';

  @Input()
  userName: string;

  @Input()
  content: string;

  @Input()
  timeCreation:Data;

  constructor() {
  }


}
