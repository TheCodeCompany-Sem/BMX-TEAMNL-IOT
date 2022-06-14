import { Component, OnInit } from '@angular/core';
import {TrackTimeRecords} from "../../models/trackTimeRecords";
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


  get time(): TrackTimeRecords[] {
    return this.trackTimeService.findAll();
  }
  ngOnInit(): void {
  }

}
