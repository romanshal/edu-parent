import { NgModule } from "@angular/core";
import {HeaderComponent} from "./components/header.component";
import {WindowComponent} from "./components/pop_up_window/window.component";


@NgModule({
  declarations: [
    HeaderComponent,
    WindowComponent
  ],
  imports: [

  ],
  providers: [],
  exports: [HeaderComponent,
            WindowComponent
  ]
})
export class HeaderModule {}
