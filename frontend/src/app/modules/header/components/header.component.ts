import {Component, OnInit} from "@angular/core";
import {StorageService} from "../../../services/storage.service";

@Component({
  selector: "app-header",
  templateUrl: "./header.component.html"
})
export class HeaderComponent {

  public isHome: boolean = true;
  public showPopup: boolean = false;

  constructor(private storageService: StorageService,){

  }

  close(){
   this.showPopup = false;
  }

  public logout(): void {
    this.storageService.clearToken();
    this.storageService.setCurrentUser(null);
    this.redirect();
  }

  public redirect(): void {
    window.location.href = '/home';
  }
}
