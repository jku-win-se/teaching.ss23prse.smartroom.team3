import { Component } from '@angular/core';
import {Room} from "../../entities/entity";
import {RoomService} from "../../room.service";

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrls: ['./room-list.component.scss']
})
export class RoomListComponent {

  public rooms: Room[] = [];

  constructor(private roomService: RoomService) {
  }

  ngOnInit() {
    this.roomService.getRooms().subscribe((rooms) => {
      this.rooms = rooms;
      console.log(this.rooms);
    })
  }

}
