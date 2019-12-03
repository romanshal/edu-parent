import { Component, OnInit } from "@angular/core";
import {HeaderService} from "../../../../services/header.service";

@Component({
  selector: "app-home",
  templateUrl: "./home.component.html"
})
export class HomeComponent implements OnInit {

  constructor(
    // public headerService: HeaderService
  ) {}

  ngOnInit() {
// this.headerService.showRegButton=true;
  }
}
