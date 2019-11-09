import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {PublicationComponent} from "./components/publication/publication.component";
import {RegistrationFormComponent} from "./components/registration-form/registration-form.component";


@NgModule({
  declarations: [
    PublicationComponent,
    RegistrationFormComponent
  ],
  imports: [
    CommonModule
  ],
  providers: [],
  exports: [
    PublicationComponent,
    RegistrationFormComponent
  ]
})
export class SharedModule {
}
