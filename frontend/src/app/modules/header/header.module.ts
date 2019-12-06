import {NgModule} from "@angular/core";
import {HeaderComponent} from "./components/header.component";
import {WindowComponent} from "./components/pop_up_window/window.component";
import {CommonModule} from "@angular/common";
import {SharedModule} from "../shared/shared.module";
import {FormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    HeaderComponent,
    WindowComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    FormsModule
  ],
  providers: [],
  exports: [
    HeaderComponent,
    WindowComponent
  ]
})
export class HeaderModule {
}
