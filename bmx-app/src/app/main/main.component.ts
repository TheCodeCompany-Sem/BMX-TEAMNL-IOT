import { Component, OnInit } from '@angular/core';
import {AthleteService} from "../services/athlete.service";
import {Athlete} from "../models/athlete";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  constructor(private athleteService: AthleteService) { }

  get athletes(): Athlete[] {
    return this.athleteService.findAll();
  }

  ngOnInit(): void {
  }

}
