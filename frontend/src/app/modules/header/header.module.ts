import { NgModule } from "@angular/core";
import {HeaderComponent} from "./components/header.component";
import {WindowComponent} from "./components/pop_up_window/window.component";
import {CommonModule} from "@angular/common";
import {SharedModule} from "../shared/shared.module";


@NgModule({
  declarations: [
    HeaderComponent,
    WindowComponent
  ],
  imports: [
    CommonModule,
    SharedModule
  ],
  providers: [],
  exports: [HeaderComponent,
            WindowComponent
  ]
})
export class HeaderModule {}
