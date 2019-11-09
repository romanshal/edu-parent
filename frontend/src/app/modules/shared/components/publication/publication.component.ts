import {Component, EventEmitter, Input, OnInit, Output} from "@angular/core";

@Component({
  selector: "publication",
  templateUrl: "./publication.component.html"
})
export class PublicationComponent {
  @Input()
  userName: string;

  @Input()
  imgSrc: string;

  @Input()
  id: string;

  @Input()
  description: string;

  @Output()
  sendBtn: EventEmitter<void> = new EventEmitter<void>();
}
