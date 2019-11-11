import { Component, OnInit } from "@angular/core";
import {Subscription} from "rxjs";
import {User} from "../models/user";
import {BillingAccount} from "../models/billing-account";
import {UserService} from "../../../../services/user.service";
import {BillingAccountService} from "../../../../services/billing-account.service";

@Component({
  selector: "admin-page",
  templateUrl: "./admin-page.component.html"
})

export class AdminPageComponent implements OnInit {
  // public user: User;
  // public users: User[];
  //
  // public billingAccounts: BillingAccount[];
  //
  // private subscriptions: Subscription[] = [];
  //
  //
  // constructor(private userService: UserService,
  //             private billingAccountService: BillingAccountService,) {
  //
  // }

  ngOnInit() {

  }

  //  private loadBillingAccounts(): void {
  //    // Get data from BillingAccountService
  //    this.subscriptions.push(this.billingAccountService.getBillingAccounts().subscribe(accounts => {
  //      // Parse json response into local array
  //      this.billingAccounts = accounts as BillingAccount[];
  //      // Check data in console
  //      console.log(this.billingAccounts);// don't use console.log in angular :)
  //    }));
  // }
}
