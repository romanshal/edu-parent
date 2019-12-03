import {Component, OnInit} from "@angular/core";
import {PostService} from "../../../../services/post.service";
import {AuthToken, UserService} from "../../../../services/user.service";
import {LoginModel} from "../../../layout/components/models/login.model";
import {StorageService} from "../../../../services/storage.service";
import {User} from "../../../layout/components/models/user";

@Component({
  selector: "app-window",
  templateUrl: "./window.component.html"
})
export class WindowComponent implements OnInit {

  public isLogin: boolean = true;
  public isRegistration: boolean = false;
  public forgotPass: boolean = false;

  public loginModel: LoginModel = {};
  public showCheckYourSetDataAlert: boolean = false;

  constructor(private storageService: StorageService,
              private userService: UserService) {
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

  public onSubmit(): void {
    this.userService.generateToken(this.loginModel)
      .subscribe((authToken: AuthToken) => {
        if (authToken.token) {
          this.storageService.setToken(authToken.token);
          this.userService.getAuthorizedUser()
            .subscribe((userModel: User) => {
              this.storageService.setCurrentUser(userModel);
            });
        }
      }, (error) => {
        if (error.status === 401) {
          this.showCheckYourSetDataAlert = true;
        } else {
          alert(error.message);
        }
      });
  }

  public logout(): void {
    this.storageService.clearToken();
    this.storageService.setCurrentUser(null);
  }
}




