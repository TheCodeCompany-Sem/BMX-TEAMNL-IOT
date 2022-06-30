import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomepageComponent } from './page/homepage/homepage.component';
import {ProfileComponent} from "./components/profile/profile.component";
import {SplashComponent} from "./components/splash/splash.component";
const routes: Routes = [

  {path: '' , component: SplashComponent },


  { path: 'homepage' , component: HomepageComponent },

  { path: 'profile' , component: ProfileComponent},

  {path: 'profile/id', component: ProfileComponent}



];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

export const routingComponent = [HomepageComponent, ProfileComponent, SplashComponent]
