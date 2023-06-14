import { Component, OnChanges } from '@angular/core';
import {Room} from "../../entities/entity";
import {RoomService} from "../../room.service";
import { RoomsComponent } from '../rooms/rooms.component';
import { RoomDetailsComponent } from '../room-details/room-details.component';

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrls: ['./room-list.component.scss']
})
export class RoomListComponent{

  public rooms: Room[] = [];

  constructor(private roomService: RoomService) {
  }

  ngOnInit() {
    this.loadRooms();
  }

  private loadRooms() {
    this.roomService.getRooms().subscribe((rooms) => {
      if (this.areRoomsDifferent(this.rooms, rooms)) {
        this.rooms = rooms;
        // Perform additional actions here
        console.log('Rooms have been updated:', this.rooms);
      }
    });
  }

  public updateRoomNameSize(room: Room, newName: string, newSize: number){
    room.name = newName;
    room.size = newSize;
    
    this.roomService.updateRoom(room).subscribe((data) => {});
    console.log(room);
  }



  public deleteAllRoom(Room: Room){
    this.roomService.getRooms().subscribe((rooms) => {
      if (this.areRoomsDifferent(this.rooms, rooms)) {
        this.rooms = rooms;
      }
    });
    this.rooms.forEach(element => {
      this.roomService.removeRoom(element.id).subscribe((data) => {});});
  }

  public deleteRoom(Room: Room){
    this.roomService.removeRoom(Room.id).subscribe((data) => {});
    this.loadRooms();
  }
  
  private areRoomsDifferent(oldRooms: Room[], newRooms: Room[]): boolean {
    // Compare the length of the arrays
    if (oldRooms.length !== newRooms.length) {
      return true;
    }

    // Compare each room in the arrays
    for (let i = 0; i < oldRooms.length; i++) {
      if (oldRooms[i].id !== newRooms[i].id || oldRooms[i].name !== newRooms[i].name) {
        return true;
      }
    }

    return false;
  }

  public editRoomVisible: boolean = false;
  public detailsTabVisible: boolean = false;
  public mainTabVisible: boolean = true;


  //Navigate the room tab
  public goToMainTab(Site: String) {
    if(Site == "editRoom"){
      this.editRoomVisible = true;
      this.detailsTabVisible = false;
      this.mainTabVisible = false;
    }
    if(Site == "detailsTab"){
      this.editRoomVisible = false;
      this.detailsTabVisible = true;
      this.mainTabVisible = false;
    }
    if(Site == "mainTab"){
      this.editRoomVisible = false;
      this.detailsTabVisible = false;
      this.mainTabVisible = true;
    }

    
  }

}
