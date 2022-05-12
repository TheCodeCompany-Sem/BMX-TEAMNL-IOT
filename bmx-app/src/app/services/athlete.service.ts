import {ErrorHandler, Injectable} from '@angular/core';
import {Athlete} from "../models/athlete";
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AthleteService {
  private athletes: Athlete[] = []
  constructor(private httpClient: HttpClient) {
    this.restGetAthletes().subscribe(
      responseData => this.athletes = responseData,
      error => ErrorHandler.apply(error)
    )
  }

  //Work in progress
  restGetAthletes(): Observable<Athlete[]>{
    return this.httpClient.get<Athlete[]>(`http://localhost:80/athlete`)
      .pipe(
        map(responseData => {
        const athleteArray: Athlete[] = [];
        for(const key in responseData){
          //Need to work on this
          responseData[key].trackTimeRecords = JSON.parse(JSON.stringify(responseData[key]["trackTimeRecords"]));
          athleteArray.push(responseData[key])
          console.log(responseData[key].trackTimeRecords = JSON.parse(JSON.stringify(responseData[key]["trackTimeRecords"])))
        }
        //Need to work on this
        console.log("initial list", athleteArray)
        return athleteArray
      })
      );
  }

  findAll(): Athlete[]{
    return this.athletes;
  }


}
