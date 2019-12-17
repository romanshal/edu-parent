import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {PublicationComponent} from "./components/publication/publication.component";
import {AddPostComponent} from "./components/post/add-post.component";
import {FormsModule} from "@angular/forms";
import {PhotoModalComponent} from "./components/photo-modal/photo-modal.component";
import {CommentModalComponent} from "./components/comment-modal/comment-madal.component";
import {RegButtonComponent} from "./components/reg-button/reg-button.component";
// import {ButtonComponent} from "./components/button.component/button.component";

@NgModule({
  declarations: [
    PublicationComponent,
    AddPostComponent,
    PhotoModalComponent,
    CommentModalComponent,
    RegButtonComponent
    // ButtonComponent
  ],
  imports: [
    CommonModule,
    FormsModule
  ],
  providers: [],
  exports: [
    PublicationComponent,
    AddPostComponent,
    PhotoModalComponent,
    CommentModalComponent,
    RegButtonComponent
    // ButtonComponent
  ]
})
export class SharedModule {
}
