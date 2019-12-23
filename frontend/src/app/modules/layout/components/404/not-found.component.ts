import {Component, OnInit} from '@angular/core';
import {User} from "../models/user";
import {StorageService} from "../../../../services/storage.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-not-found',
  templateUrl: './not-found.component.html'
})
export class NotFoundComponent implements OnInit {

  private currentUser: User;

  constructor(private storageService: StorageService,
              private router: Router,) {
  }

  ngOnInit() {
    this.currentUser = this.storageService.getCurrentUser();
  }

  public redirectToFriendPage(): void {
    this.router.navigateByUrl("/userPage?id=" + this.currentUser.id);
  }
}
