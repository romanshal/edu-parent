import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {PublicationComponent} from "./components/publication/publication.component";
import {RegistrationFormComponent} from "./components/registration-form/registration-form.component";
import {AddPostComponent} from "./components/post/add-post.component";
import {FormsModule} from "@angular/forms";
import {PhotoModalComponent} from "./components/photo-modal/photo-modal.component";


@NgModule({
  declarations: [
    PublicationComponent,
    RegistrationFormComponent,
    AddPostComponent,
    PhotoModalComponent
  ],
  imports: [
    CommonModule,
    FormsModule
  ],
  providers: [],
  exports: [
    PublicationComponent,
    RegistrationFormComponent,
    AddPostComponent,
    PhotoModalComponent
  ]
})
export class SharedModule {
}
