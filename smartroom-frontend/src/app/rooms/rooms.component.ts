import { Component } from '@angular/core';
import { RoomService } from '../room.service';
import { Door, Room } from '../entities/entity';

const emptyRoom: Room = {
  id: 0,
  name: '',
  size:  0,
  door: [],
  windows: [],
  lights: [],
  fans: [],
  temperaturData: [],
  co2SensorData: []
}


const emptyDoor: Door = {
    id: 0,
    open: true
}



@Component({
  selector: 'app-rooms',
  templateUrl: './rooms.component.html',
  styleUrls: ['./rooms.component.scss']
})
export class RoomsComponent {
  
  public room: Room = emptyRoom;
  public door: Door = emptyDoor;

  constructor(private roomService: RoomService) {
  }


  saveRoom(){
    
    console.log(this.room);
   //Saves room
     this.roomService.addRoom(this.room);
   
  // -> Change Room Data
   this.roomService.getRooms().subscribe((data) => {
      console.log(data)
    });
  
   }

  addDoor(){
    this.room.door.push({
      id: this.door.id,
      open: this.door.open
    });
    this.door = emptyDoor;
    console.log(this.room.door);
  }

}

