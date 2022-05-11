import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomepageComponent } from './page/homepage/homepage.component';
import { SplashComponent } from './components/splash/splash.component';
import {SplashScreenStateService} from "./services/splash-screen.service";
import { MainComponent } from './main/main.component';
import { NavbarComponent } from './generic/navbar/navbar.component';
import { FooterComponent } from './generic/footer/footer.component';
import { HeaderComponent } from './generic/header/header.component';

@NgModule({
  declarations: [
    AppComponent,
    MainComponent,
    NavbarComponent,
    FooterComponent,
    HeaderComponent,
    SplashComponent,
    HomepageComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [  SplashScreenStateService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
