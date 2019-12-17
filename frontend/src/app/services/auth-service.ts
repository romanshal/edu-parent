import {Injectable} from "@angular/core";
import {StorageService} from "./storage.service";

@Injectable({providedIn: "root"})
export class AuthService {

  constructor(private storageService: StorageService) {
  }

  public isAuthenticated(): boolean {
    const token = localStorage.getItem("token");
    return token && token !== "null" && !!this.storageService.getCurrentUser();
  }

  public isAdmin(): boolean {
    if (this.storageService.getCurrentUser().role.roleName.toLowerCase() === 'admin') {
      return true;
    }
    return false;
  }

}
