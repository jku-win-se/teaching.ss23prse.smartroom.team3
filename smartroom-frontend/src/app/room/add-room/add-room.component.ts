import {Component, EventEmitter, Output} from '@angular/core';
import {
  Door,
  emptyDoor,
  emptyFan,
  emptyLight,
  emptyRoom,
  emptyWindow,
  Fan,
  Light,
  Room,
  Fenster
} from "../../entities/entity";
import {RoomService} from "../../room.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-room',
  templateUrl: './add-room.component.html',
  styleUrls: ['./add-room.component.scss']
})


export class AddRoomComponent {

  public room: Room = emptyRoom;

  public door: Door = emptyDoor;
  public window: Fenster = emptyWindow;

  public light: Light = emptyLight;
  public fan: Fan = emptyFan;

  newWindow: any = {};

  constructor(private roomService: RoomService, private router: Router) {
  }

  @Output() roomAdded: EventEmitter<void> = new EventEmitter<void>();


  saveRoom() {
    console.log(this.room);
    this.roomService.addRoom(this.room).subscribe((room) => {
      this.roomAdded.emit();
      this.router.navigate(['room-details/' + room.id]);
     // window.location.reload();
    });
  }

  addDoor() {
    this.room.doors.push({
      id: this.door.id,
      open: this.door.open
    });
    this.door = emptyDoor;
  }

  addWindow() {
    this.room.roomWindows.push({
      id: this.window.id,
      open: this.window.open
    });
    this.window = emptyWindow;
    this.newWindow = {};
  }

  addLight() {
    this.room.lights.push({
      id: this.light.id,
      on: this.light.on
    });
    this.light = emptyLight;
  }

  addFan() {
    this.room.fans.push({
      id: this.fan.id,
      on: this.fan.on
    });
    this.fan = emptyFan;
  }

}
