import { Component, OnInit } from '@angular/core';
import {SplashScreenStateService} from "../../services/splash-screen.service";

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  constructor(
    private splashScreenStateService: SplashScreenStateService
  ) {
  }

  ngOnInit(): void {
    setTimeout(() => {
      this.splashScreenStateService.stop();
    }, 5000);
  }

}

