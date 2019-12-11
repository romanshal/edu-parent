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
    const currentUser = this.storageService.getCurrentUser();
    if (currentUser.role === 'admin') {
      return true;
    }
    return false;
  }

}
