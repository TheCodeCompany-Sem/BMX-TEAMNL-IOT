import {Athlete} from "./athlete";

export class TrackTimeRecord{
  private _id: number;
  private _recordedTime: Date;
  private _recordedValue: number;
  private _windSpeed: number;

  constructor(id: number, recordedTime: Date, recordedValue: number, windSpeed: number) {
    this._id = id;
    this._recordedTime = recordedTime;
    this._recordedValue = recordedValue;
    this._windSpeed = windSpeed;
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

  get recordedValue(): number {
    return this._recordedValue;
  }

  set recordedValue(value: number) {
    this._recordedValue = value;
  }

  get windSpeed(): number {
    return this._windSpeed;
  }

  set windSpeed(value: number) {
    this._windSpeed = value;
  }

  public toString = () : string =>{
    return `id: ${this._id}, recordedTime: ${this._recordedTime}, recordedValue: ${this.recordedValue}, windSpeed : ${this.windSpeed}`
  }
}
