import {Component, Input} from "@angular/core";

@Component({
  selector: "publication",
  templateUrl: "./publication.component.html",
})
export class PublicationComponent {

  format: string = 'd MMMM, h:mm:ss';

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

  @Input()
  timeCreation: string;

  constructor() {
  }
}
