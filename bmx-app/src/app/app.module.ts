import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule, routingComponent } from './app-routing.module';
import { AppComponent} from './app.component';
import { SplashComponent } from './components/splash/splash.component';
import {SplashScreenStateService} from "./services/splash-screen.service";
import { NavbarComponent } from './generic/navbar/navbar.component';
import { FooterComponent } from './generic/footer/footer.component';
import { HeaderComponent } from './generic/header/header.component';
import { HttpClientModule } from '@angular/common/http';
import {ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    HeaderComponent,
    SplashComponent,
    routingComponent

  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,


  ],
  providers: [  SplashScreenStateService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
