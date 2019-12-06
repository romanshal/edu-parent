import {Component, EventEmitter, Input, OnInit, Output} from "@angular/core";

@Component({
  selector: "button",
  templateUrl: "./button.component.html"
})
export class ButtonComponent {

  @Input()
  type: string;

  @Input()
  ngClass: string;

  @Input()
  disabled: string;

  @Input()
  nameBtn: string;

  @Output()
  emitter: EventEmitter<void> = new EventEmitter<void>();
}
