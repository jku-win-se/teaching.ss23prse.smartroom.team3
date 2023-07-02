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
  Fenster
} from "../../entities/entity";
import { ActivatedRoute, Router } from "@angular/router";
import { RoomService } from "../../room.service";

@Component({
  selector: 'app-update-room',
  templateUrl: './update-room.component.html',
  styleUrls: ['./update-room.component.scss']
})
export class UpdateRoomComponent {

  public id: number = 0;
  public room: Room = emptyRoom;

  public door: Door = emptyDoor;
  public window: Fenster = emptyWindow;

  public light: Light = emptyLight;
  public fan: Fan = emptyFan;

  public nextFanId: number = 0;
  public nextLightId: number = 0;
  public nextDoorId: number = 0;
  public nextWindowId: number = 0;

  constructor(private route: ActivatedRoute, private roomService: RoomService, private router: Router) {}

  ngOnInit() {
    // Load Room by ID
    this.route.params.subscribe(params => {
      this.id = +params['id'];
      this.roomService.getRoom(this.id).subscribe((room) => {
        this.room = room;
      });
    });
  }

  public updateRoom() {
    // Update the room
   // console.log(this.room);
    this.roomService.updateRoom(this.room).subscribe((data) => {
      this.router.navigate(['room-details/', this.id]);
    });
  }

  addDoor() {
    // Add a door to the room
    this.room.doors.push({
      id: this.door.id,
      open: this.door.open
    });
    this.door = emptyDoor;
  }

  addWindow() {
    // Add a window to the room
    this.room.roomWindows.push({
      id: this.window.id,
      open: this.window.open
    });
    this.window = emptyWindow;
  }

  addLight() {
    // Add a light to the room
    this.room.lights.push({
      id: this.light.id,
      on: this.light.on
    });
    this.light = emptyLight;
  }

  addFan() {
    // Add a fan to the room
    this.room.fans.push({
      id: this.fan.id,
      on: this.fan.on
    });
    this.fan = emptyFan;
  }

  public getNextID() {
    // Get the next available IDs for doors, windows, lights, and fans
    this.roomService.getRooms().subscribe((data) => {
      const doors: number[] = [];
      const roomWindows: number[] = [];
      const lights: number[] = [];
      const fans: number[] = [];

      data.forEach((room) => {
        doors.push(...room.doors.map((door) => door.id));
        roomWindows.push(...room.roomWindows.map((window) => window.id));
        lights.push(...room.lights.map((light) => light.id));
        fans.push(...room.fans.map((fan) => fan.id));
      });

      this.nextFanId = Math.max(...fans) + 1;
      console.log("Next Fan ID: ", this.nextFanId);
      this.nextLightId = Math.max(...lights) + 1;
      console.log("Next Light ID: ", this.nextLightId);
      this.nextDoorId = Math.max(...doors) + 1;
      console.log("Next Door ID: ", this.nextDoorId);
      this.nextWindowId = Math.max(...roomWindows) + 1;
      console.log("Next Window ID: ", this.nextWindowId);

      if (this.nextFanId < 0) {
        this.nextFanId = 0;
      }
      if (this.nextLightId < 0) {
        this.nextLightId = 0;
      }
      if (this.nextDoorId < 0) {
        this.nextDoorId = 0;
      }
      if (this.nextWindowId < 0) {
        this.nextWindowId = 0;
      }
    });
  }

  public addFanButton() {
    // Add a fan to the room and update the room
    this.getNextID();
    this.room.fans.push({
      id: this.nextFanId,
      on: false
    });
    this.fan = emptyFan;
    this.roomService.updateRoom(this.room).subscribe((data) => {
      this.router.navigate(['room-details/' + this.room.id]);
    });
    this.updateRoom();
    console.log(this.room);
  }

  public addDoorButton() {
    // Add a door to the room and update the room
    this.getNextID();
    this.room.doors.push({
      id: this.nextDoorId,
      open: false
    });
    this.door = emptyDoor;
    this.roomService.updateRoom(this.room).subscribe((data) => {
      this.router.navigate(['room-details/' + this.room.id]);
    });
    console.log(this.room);
    this.updateRoom();
  }

  public addWindowButton() {
    // Add a window to the room and update the room
    this.getNextID();
    this.room.roomWindows.push({
      id: this.nextWindowId,
      open: false
    });
    this.window = emptyWindow;
    this.roomService.updateRoom(this.room).subscribe((data) => {
      this.router.navigate(['room-details/' + this.room.id]);
    });
    console.log(this.room);
    this.updateRoom();
  }

  public addLightButton() {
    // Add a light to the room and update the room
    this.updateRoom();
    this.roomService.updateRoom(this.room).subscribe((data) => {
      console.log(this.room);
      this.router.navigate(['room-details/' + this.room.id]);
    });

    this.getNextID();
    this.room.lights.push({
      id: this.nextLightId,
      on: false
    });
    this.light = emptyLight;

    this.updateRoom();
    this.roomService.updateRoom(this.room).subscribe((data) => {
      console.log(this.room);
      this.router.navigate(['room-details/' + this.room.id]);
    });
  }

}
