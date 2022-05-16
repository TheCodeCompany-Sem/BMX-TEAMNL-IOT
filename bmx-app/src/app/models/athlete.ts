import {TrackTimeRecord} from "./tracktimerecord";

export class Athlete{
  private _id: number;
  private _firstName: string;
  private _surname: string;
  private _trackTimeRecords?: TrackTimeRecord;


  constructor(id: number, firstName: string, surname: string, trackTimeRecords: TrackTimeRecord) {
    this._id = id;
    this._firstName = firstName;
    this._surname = surname;
    this._trackTimeRecords = trackTimeRecords;
  }

  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get firstName(): string {
    return this._firstName;
  }

  set firstName(value: string) {
    this._firstName = value;
  }

  get surname(): string {
    return this._surname;
  }

  set surname(value: string) {
    this._surname = value;
  }

  get trackTimeRecords(): TrackTimeRecord {
    return this._trackTimeRecords;
  }

  set trackTimeRecords(value: TrackTimeRecord) {
    this._trackTimeRecords = value;
  }
}


