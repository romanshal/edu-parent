import { NgModule } from "@angular/core";
import {HeaderComponent} from "./components/header.component";
import {WindowComponent} from "./components/pop_up_window/window.component";
import {CommonModule} from "@angular/common";


@NgModule({
  declarations: [
    HeaderComponent,
    WindowComponent
  ],
  imports: [
    CommonModule
  ],
  providers: [],
  exports: [HeaderComponent,
            WindowComponent
  ]
})
export class HeaderModule {}
