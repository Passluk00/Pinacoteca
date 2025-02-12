import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {AppRoutes} from "./app.routes";
import {FormsModule} from "@angular/forms";
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {HttpClient, HttpClientModule} from "@angular/common/http";


@NgModule({
  declarations: [],

  imports: [
    BrowserModule,
    AppRoutes,
    FormsModule,
    FontAwesomeModule,
    BrowserAnimationsModule,
  ],

  providers: [
    HttpClient,
    HttpClientModule,

  ]

})

export class AppModule {


}

