import { Component, OnInit } from '@angular/core';
import {TrackTimeRecord} from "../../models/tracktimerecord";
import {Athlete} from "../../models/athlete";
import {TimeService} from "../../services/time.service";
import {AthleteService} from "../../services/athlete.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(
    private trackTimeService : TimeService
  ) { }


  get time(): TrackTimeRecord[] {
    return this.trackTimeService.findAll();
  }
  ngOnInit(): void {
  }

}
