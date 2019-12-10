import {Component, Input} from "@angular/core";

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

  @Input()
  image: string;

  constructor() {
  }
}
