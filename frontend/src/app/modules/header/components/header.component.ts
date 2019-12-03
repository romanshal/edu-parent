import {Component, OnInit} from "@angular/core";

@Component({
  selector: "app-header",
  templateUrl: "./header.component.html"
})
export class HeaderComponent {

  public isHome: boolean=true;
  public showPopup: boolean = false;

  close(){
   this.showPopup = false;
  }
}
