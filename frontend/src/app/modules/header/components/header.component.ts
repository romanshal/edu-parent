import {Component, OnInit} from "@angular/core";
import {StorageService} from "../../../services/storage.service";
import {Router} from "@angular/router";

@Component({
  selector: "app-header",
  templateUrl: "./header.component.html"
})
export class HeaderComponent {

  public showPopup: boolean = false;

  constructor(private storageService: StorageService,
              private router: Router){

  }

  close(){
   this.showPopup = false;
  }

  public logout(): void {
    this.storageService.clearToken();
    this.storageService.setCurrentUser(null);
    this.redirect();
  }

  public redirectToUserPage(): void {
    this.router.navigateByUrl("/userPage?id=" + this.storageService.getCurrentUser().id);
  }

  public redirect(): void {
    this.router.navigateByUrl("/home");
  }

  public _isCurrentUser(): boolean {
    return this.storageService.getCurrentUser()===null;
  }
}
