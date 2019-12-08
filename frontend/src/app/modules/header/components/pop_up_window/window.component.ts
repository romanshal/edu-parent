import {Component, OnDestroy} from "@angular/core";
import {AuthToken, UserService} from "../../../../services/user.service";
import {LoginModel} from "../../../layout/components/models/login.model";
import {StorageService} from "../../../../services/storage.service";
import {User} from "../../../layout/components/models/user";
import {Subscription} from "rxjs";
import {NgModel} from "@angular/forms";

@Component({
  selector: "app-window",
  templateUrl: "./window.component.html"
})
export class WindowComponent implements OnDestroy{

  public isLogin: boolean = true;
  public isRegistration: boolean = false;
  public forgotPass: boolean = false;
  public newUser: User = new User();

  private subscriptions: Subscription[] = [];

  public loginModel: LoginModel = {};
  public showCheckYourSetDataAlert: boolean = false;

  constructor(private storageService: StorageService,
              private userService: UserService) {
  }

  public selectSection(section: string) {
    switch (section) {
      case "login":
        this.isLogin = true;
        this.forgotPass = this.isRegistration = false;
        break;
      case "registration":
        this.isRegistration = true;
        this.forgotPass = this.isLogin = false;
        break;
      case "password":
        this.forgotPass = true;
        this.forgotPass = this.isLogin = false;
        break;
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
              this.redirect();
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

  public addUser(login: NgModel, password: NgModel): void {
    this.subscriptions.push(this.userService.saveUser(this.newUser).subscribe(() => {
      this.redirect();
    }));
  }

  public logout(): void {
    this.storageService.clearToken();
    this.storageService.setCurrentUser(null);
  }

  // router
  public redirect(): void {
    window.location.href = '/userPage';
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

}




