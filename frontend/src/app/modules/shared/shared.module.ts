import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {PublicationComponent} from "./components/publication/publication.component";
import {RegistrationFormComponent} from "./components/registration-form/registration-form.component";
import {AddPostComponent} from "./components/post/add-post.component";
import {FormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    PublicationComponent,
    RegistrationFormComponent,
    AddPostComponent
  ],
  imports: [
    CommonModule,
    FormsModule
  ],
  providers: [],
  exports: [
    PublicationComponent,
    RegistrationFormComponent,
    AddPostComponent
  ]
})
export class SharedModule {
}
