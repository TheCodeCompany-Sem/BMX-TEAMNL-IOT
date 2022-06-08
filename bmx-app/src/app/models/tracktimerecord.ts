import {Athlete} from "./athlete";

export class TrackTimeRecord{
  private _id: number;
  private _recordedTime: Date;
  private _humidity: number;
  private _windSpeed: number;
  private _windDirection: string;
  private _temperature: number;


  constructor(id: number, recordedTime: Date, humidity: number, windSpeed: number, windDirection: string, temperature: number) {
    this._id = id;
    this._recordedTime = recordedTime;
    this._humidity = humidity;
    this._windSpeed = windSpeed;
    this._windDirection = windDirection;
    this._temperature = temperature;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get recordedTime(): Date {
    return this._recordedTime;
  }

  set recordedTime(value: Date) {
    this._recordedTime = value;
  }
  get windSpeed(): number {
    return this._windSpeed;
  }

  set windSpeed(value: number) {
    this._windSpeed = value;
  }


  get humidity(): number {
    return this._humidity;
  }

  set humidity(value: number) {
    this._humidity = value;
  }

  get windDirection(): string {
    return this._windDirection;
  }

  set windDirection(value: string) {
    this._windDirection = value;
  }

  get temperature(): number {
    return this._temperature;
  }

  set temperature(value: number) {
    this._temperature = value;
  }


}
