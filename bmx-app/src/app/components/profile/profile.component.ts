import { Component, OnInit } from '@angular/core';
import {TrackTimeRecord} from "../../models/tracktimerecord";
import {Athlete} from "../../models/athlete";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(

    private trackTimeService : TrackTimeRecord
  ) { }


  get time(): TrackTimeRecord[] {
    return this.trackTimeService.findAll();
  }

  ngOnInit(): void {
  }

}
