import { NgModule } from "@angular/core";
import {BillingDetailsViewComponent} from "./components/billing-details/billing-details-view.component";
import {NotFoundComponent} from "./components/404/not-found.component";
import {HomeComponent} from "./components/home/home.component";
import {BillingAccountModule} from "../billing-account/billing-account.module";
import {HeaderModule} from "../header/header.module";
import {RouterModule} from "@angular/router";
import {UserPageComponent} from "./components/user-page/user-page.component";

@NgModule({
  declarations: [
    HomeComponent,
    UserPageComponent,
    NotFoundComponent,
    BillingDetailsViewComponent
  ],
  imports: [
    BillingAccountModule,
    HeaderModule,
    RouterModule
  ],
  providers: [],
  exports: [HomeComponent, NotFoundComponent, BillingDetailsViewComponent,UserPageComponent]
})
export class LayoutModule {}
