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
import {AdminPageComponent} from "./components/admin-page/admin-page.component";
import {CommonModule} from "@angular/common";
import {PostService} from "../../services/post.service";


@NgModule({
  declarations: [
    HomeComponent,
    UserPageComponent,
    NotFoundComponent,
    BillingDetailsViewComponent,
    AdminPageComponent
  ],
  imports: [
    BillingAccountModule,
    HeaderModule,
    RouterModule,
    SharedModule,
    FooterModule,
    CommonModule
  ],
  providers: [PostService],
  exports: [HomeComponent, NotFoundComponent, BillingDetailsViewComponent,UserPageComponent,AdminPageComponent]
})
export class LayoutModule {}
