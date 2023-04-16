import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Room} from "./entities/entity";
import { NumberSymbol } from '@angular/common';

@Injectable({
  providedIn: 'root'
})

export class RoomService {

  private readonly baseurl = 'http://localhost:8080/';

  constructor(private http: HttpClient) {
  }

  public getRooms(): Observable<Room[]> {
    return this.http.get<Room[]>(this.baseurl + 'room');
  }

  public addRoom(room: Room): Observable<Room> {
    return this.http.post<Room>(this.baseurl + 'room', room);
  }

  public getRoom(id: number): Observable<Room> {
    return this.http.get<Room>(this.baseurl + 'room/'+id);
  }

  //Saves Room
  public updateRoom(room: Room): Observable<Room> {
    return this.http.put<Room>(this.baseurl + 'room', room);
  }
}
