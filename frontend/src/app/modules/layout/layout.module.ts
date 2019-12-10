import { NgModule } from "@angular/core";
import {NotFoundComponent} from "./components/404/not-found.component";
import {HomeComponent} from "./components/home/home.component";
import {HeaderModule} from "../header/header.module";
import {RouterModule} from "@angular/router";
import {UserPageComponent} from "./components/user-page/user-page.component";
import {SharedModule} from "../shared/shared.module";
import {FooterModule} from "../footer/footer.module";
import {AdminPageComponent} from "./components/admin-page/admin-page.component";
import {CommonModule} from "@angular/common";
import {PostService} from "../../services/post.service";
import {NewsPageComponent} from "./components/news/news.component";
import {FormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    HomeComponent,
    UserPageComponent,
    NotFoundComponent,
    AdminPageComponent,
    NewsPageComponent
  ],
  imports: [
    HeaderModule,
    RouterModule,
    SharedModule,
    FooterModule,
    CommonModule,
    FormsModule
  ],
  providers: [PostService],
  exports: [HomeComponent, NotFoundComponent, UserPageComponent,AdminPageComponent,NewsPageComponent]
})
export class LayoutModule {}
