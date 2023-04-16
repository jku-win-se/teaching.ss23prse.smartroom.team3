import {Component} from '@angular/core';
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
  Window
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
  public window: Window = emptyWindow;

  public light: Light = emptyLight;
  public fan: Fan = emptyFan;

  constructor(private roomService: RoomService, private router: Router) {
  }


  saveRoom() {
    console.log(this.room);
    this.roomService.addRoom(this.room).subscribe((room) => {
      this.router.navigate(['room-list']);
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
