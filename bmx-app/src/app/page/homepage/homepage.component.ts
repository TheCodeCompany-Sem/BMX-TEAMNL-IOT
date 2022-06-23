import { Component, OnInit } from '@angular/core';
import { SplashScreenStateService } from "../../services/splash-screen.service";
import { AthleteService } from "../../services/athlete.service";
import { Athlete } from "../../models/athlete";
import {Router} from "@angular/router";
import {getLocaleId} from "@angular/common";

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  constructor(
    private splashScreenStateService: SplashScreenStateService,
    private athleteService: AthleteService,
    private router: Router
  ) {


  }
    get athletes(): Athlete[] {
      // let trackTimeRecords = this.athleteService.findAll().map(obj => ({humidity: obj.trackTimeRecords.humidity}));
      // console.log(trackTimeRecords)
      return this.athleteService.findAll();
    }

    ngOnInit(): void {
      setTimeout(() => {
        this.splashScreenStateService.stop();
      }, 5000);
    }

    get athletesId(): Athlete{

    return this.athleteService.findById(1);
    }


  goToPage(pageName: string) : void {
    this.router.navigate([`${pageName}`])

  }
}

