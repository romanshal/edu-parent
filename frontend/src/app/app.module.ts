import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { BsDropdownModule } from "ngx-bootstrap/dropdown";
import { TooltipModule } from "ngx-bootstrap/tooltip";
import { ModalModule } from "ngx-bootstrap/modal";
import { FormsModule } from "@angular/forms";
import { AppComponent } from "./app.component";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {Ng4LoadingSpinnerModule} from "ng4-loading-spinner";
import {RouterModule, Routes} from "@angular/router";
import {BillingDetailsViewComponent} from "./modules/layout/components/billing-details/billing-details-view.component";
import {NotFoundComponent} from "./modules/layout/components/404/not-found.component";
import {LayoutModule} from "./modules/layout/layout.module";
import {HomeComponent} from "./modules/layout/components/home/home.component";
import {UserPageComponent} from "./modules/layout/components/user-page/user-page.component";
import {AdminPageComponent} from "./modules/layout/components/admin-page/admin-page.component";
import {NewsPageComponent} from "./modules/layout/components/news/news.component";
import {UserService} from "./services/user.service";
import {APIInterceptor} from "./interceptors/api-interceptor";

const appRoutes: Routes = [
  {path: "", component: HomeComponent},
  {path: "home", component: HomeComponent},
  {path: "userPage", component: UserPageComponent},
  {path: "adminPage", component: AdminPageComponent},
  {path: "news", component: NewsPageComponent},
  {path: "billing-details/:id", component: BillingDetailsViewComponent},
  {path: "**", component: NotFoundComponent},
];

@NgModule({
  declarations: [
    AppComponent,

  ],
  imports: [
    BrowserModule,
    FormsModule,
    LayoutModule,
    HttpClientModule,
    Ng4LoadingSpinnerModule.forRoot(),
    BsDropdownModule.forRoot(),
    TooltipModule.forRoot(),
    ModalModule.forRoot(),
    RouterModule.forRoot(appRoutes)
  ],
  providers: [UserService, APIInterceptor, {
    provide: HTTP_INTERCEPTORS,
    useClass: APIInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
