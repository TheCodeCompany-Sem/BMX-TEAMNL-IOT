import {ErrorHandler, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {TrackTimeRecord} from "../models/tracktimerecord";
import {map, Observable} from "rxjs";
import {Athlete} from "../models/athlete";

@Injectable({
  providedIn: 'root'
})
export class TimeService {

  private tracktimerecord : TrackTimeRecord[] = []

  constructor(private httpClient: HttpClient) {

    this.restGetTrackTime().subscribe(
      responseData => this.tracktimerecord = responseData,
      error => ErrorHandler.apply(error) )

  }

  //Work in progress
  restGetTrackTime(): Observable<TrackTimeRecord[]>{
    return this.httpClient.get<TrackTimeRecord[]>(`http://localhost:808/TrackTimeRecord`)
      .pipe(
        map(responseData => {
          const trackArray: TrackTimeRecord[] = [];
          for(const key in responseData){
            responseData[key].recordedTime;
            trackArray.push(responseData[key])
          }
          console.log("initial list", trackArray)
          return trackArray
        })
      );
  }
  findAll(): TrackTimeRecord[]{
    return this.tracktimerecord;
  }
}


