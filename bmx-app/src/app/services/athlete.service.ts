import {ErrorHandler, Injectable} from '@angular/core';
import {Athlete} from "../models/athlete";
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {keyframes} from "@angular/animations";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AthleteService {
  private athletes: Athlete[] = []
  constructor(private httpClient: HttpClient) {
    this.restGetAthletes().subscribe(
      responseData => this.athletes = responseData
    )
  }

  //Work in progress
  restGetAthletes(): Observable<Athlete[]>{
    return this.httpClient.get<Athlete[]>(`${environment.apiUrl}/athlete`)
      .pipe(
        map(responseData => {
        const athleteArray: Athlete[] = [];
        for(const key in responseData){
          responseData[key].trackTimeRecords = responseData[key]["trackTimeRecords"];
          athleteArray.push(responseData[key])
        }
        console.log("initial list", athleteArray)
        return athleteArray
      })
      );
  }

  findAll(): Athlete[]{
    return this.athletes;
  }

  findById(id: number): Athlete {
    console.log(id)
    return this.athletes.find(athlete => athlete.id == id)
  }

}
