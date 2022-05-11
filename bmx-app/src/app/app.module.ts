import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomepageComponent } from './page/homepage/homepage.component';
import {SplashScreenStateService} from "./services/splash-screen.service";
import { SplashComponent } from './components/splash/splash.component';

@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    SplashComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [  SplashScreenStateService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
