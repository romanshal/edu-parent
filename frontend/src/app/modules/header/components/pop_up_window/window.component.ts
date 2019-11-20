import {Component, OnInit} from "@angular/core";

@Component({
  selector: "app-window",
  templateUrl: "./window.component.html"
})
export class WindowComponent implements OnInit {

  public isLogin: boolean = true;
  public isRegistration: boolean = false;
  public forgotPass: boolean = false;

  constructor() {
  }

  ngOnInit() {

  }

  public selectSection(section: string) {
    switch (section) {
      case "login": this.isLogin = true; this.forgotPass = this.isRegistration = false; break;
      case "registration": this.isRegistration = true; this.forgotPass = this.isLogin = false; break;
      case "password": this.forgotPass = true; this.forgotPass = this.isLogin = false; break;
    }
  }

  public showPopup: boolean = false;
}




