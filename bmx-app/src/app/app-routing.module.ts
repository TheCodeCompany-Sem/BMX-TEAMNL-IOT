import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomepageComponent } from './page/homepage/homepage.component';
import {ProfileComponent} from "./components/profile/profile.component";
const routes: Routes = [
  {
    path: 'homepage',
    component: HomepageComponent,
    children:[

      {
        path:'',
        redirectTo: 'homepage',
        pathMatch: 'full'

      },
      {

        path:'profile',
        component: ProfileComponent
      }
    ]
  },
  {
    path: '',
    redirectTo: 'homepage',
    pathMatch: 'full'
  }
// ,
//   {
//     path:'homepage',
//     redirectTo:'profile',
//     component: ProfileComponent
//
//   }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
