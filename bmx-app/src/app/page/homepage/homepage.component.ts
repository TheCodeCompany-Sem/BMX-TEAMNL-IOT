import { Component, OnInit } from '@angular/core';
import { SplashScreenStateService } from "../../services/splash-screen.service";
import { AthleteService } from "../../services/athlete.service";
import { Athlete } from "../../models/athlete";

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  constructor(
    private splashScreenStateService: SplashScreenStateService,
    private athleteService: AthleteService
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
}

