import { NgModule } from "@angular/core";
import {BillingDetailsViewComponent} from "./components/billing-details/billing-details-view.component";
import {NotFoundComponent} from "./components/404/not-found.component";
import {HomeComponent} from "./components/home/home.component";
import {BillingAccountModule} from "../billing-account/billing-account.module";
import {HeaderModule} from "../header/header.module";
import {RouterModule} from "@angular/router";
import {UserPageComponent} from "./components/user-page/user-page.component";
import {SharedModule} from "../shared/shared.module";
import {FooterModule} from "../footer/footer.module";

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
    RouterModule,
    SharedModule,
    FooterModule
  ],
  providers: [],
  exports: [HomeComponent, NotFoundComponent, BillingDetailsViewComponent,UserPageComponent]
})
export class LayoutModule {}
