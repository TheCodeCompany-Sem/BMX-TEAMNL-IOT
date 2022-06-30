import {TrackTimeRecords} from "./trackTimeRecords";

export class Athlete{
  private _id: number;
  private _firstName: string;
  private _surname: string;
  private _trackTimeRecords?: TrackTimeRecords;

  constructor(id?: number, firstName?: string, surname?: string, trackTimeRecords?: TrackTimeRecords) {
    this._id = id!;
    this._firstName = firstName!;
    this._surname = surname!;
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

  get trackTimeRecords(): TrackTimeRecords {
    return this._trackTimeRecords!;
  }

  set trackTimeRecords(value: TrackTimeRecords) {
    this._trackTimeRecords = value;
  }
  public toString =() : string =>{
    return `id : ${this._id}, firstName: ${this._firstName}, lastName: ${this._surname}, trackTimeRecords${this._trackTimeRecords}`;
  }

}


