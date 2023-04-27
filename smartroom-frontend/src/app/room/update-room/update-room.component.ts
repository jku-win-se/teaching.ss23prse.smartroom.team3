import { Component } from '@angular/core';
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
import {ActivatedRoute, Router} from "@angular/router";
import {RoomService} from "../../room.service";

@Component({
  selector: 'app-update-room',
  templateUrl: './update-room.component.html',
  styleUrls: ['./update-room.component.scss']
})
export class UpdateRoomComponent {

  public id: number = 0;
  public room: Room = emptyRoom;

  public door: Door = emptyDoor;
  public window: Window = emptyWindow;

  public light: Light = emptyLight;
  public fan: Fan = emptyFan;

  constructor(private route: ActivatedRoute, private roomService: RoomService, private router: Router) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.id = +params['id'];
      this.roomService.getRoom(this.id).subscribe((room) => {
        this.room = room;
      });
    });
  }

  public updateRoom() {
    console.log(this.room);
    this.roomService.updateRoom(this.room).subscribe((data) => {
      this.router.navigate(['room-details/', this.id]);
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
      isOn: this.light.isOn
    });
    this.light = emptyLight;
  }

  addFan() {
    this.room.fans.push({
      id: this.fan.id,
      isOn: this.fan.isOn
    });
    this.fan = emptyFan;
  }

}
