import {ErrorHandler, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {TrackTimeRecords} from "../models/trackTimeRecords";
import {map, Observable} from "rxjs";
import {Athlete} from "../models/athlete";
import {environment} from "../../environments/environment";


@Injectable({
  providedIn: 'root'
})
export class TimeService {
  private tracktimerecord : TrackTimeRecords[] = []
  constructor(private httpClient: HttpClient) {
    this.restGetTrackTime().subscribe(
      responseData => this.tracktimerecord = responseData,
      error => ErrorHandler.apply(error) )

  }
  //Work in progress
  restGetTrackTime(): Observable<TrackTimeRecords[]>{
    return this.httpClient.get<TrackTimeRecords[]>(`${environment.apiUrl}TrackTimeRecord`)
      .pipe(
        map(responseData => {
          const trackArray: TrackTimeRecords[] = [];
          for(const key in responseData){
            trackArray.push(responseData[key])
          }
          console.log("initial list", trackArray)
          return trackArray
        })
      );
  }
  findAll(): TrackTimeRecords[]{
    return this.tracktimerecord;
  }
}


