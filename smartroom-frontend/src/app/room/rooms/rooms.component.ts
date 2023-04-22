import {Component, ElementRef } from '@angular/core';
import {Room} from "../../entities/entity";
import {RoomService} from "../../room.service";
@Component({
  selector: 'app-rooms',
  templateUrl: './rooms.component.html',
  styleUrls: ['./rooms.component.scss'],
})

export class RoomsComponent {

  
  public rooms: Room[] = [room1, room2, room3];

  constructor(private roomService: RoomService) {}

  ngOnInit() {
    this.roomService.getRooms().subscribe((rooms) => {
      this.rooms = rooms;
      console.log("Room: " + this.rooms);
    })
  }
  
  onClick(event: MouseEvent) {
    event.stopPropagation();
    console.log('Load details clicked');
  }

  /* Change the screens for the room name element */
  switchRoomView(event: MouseEvent, selectedView: string) {

    /* mainTab, detailsTab, editRoom*/


    const roomTabElement = (event.target as HTMLElement).closest('.roomTab');
    console.log(roomTabElement);
    if (roomTabElement != null) {
      console.log('Parent element with class roomTab found:', roomTabElement);
      const editRoom = roomTabElement.querySelector(".editRoom");
      const detailsTab = roomTabElement.querySelector(".detailsTab");
      const mainTab = roomTabElement.querySelector(".mainTab");
      const addNewRoom = roomTabElement.querySelector(".addNewRoom");

      if(editRoom && !editRoom.classList.contains(selectedView)){editRoom.classList.add('hide');}
      if(detailsTab && !detailsTab.classList.contains(selectedView)){detailsTab.classList.add('hide');}
      if(mainTab && !mainTab.classList.contains(selectedView)){mainTab.classList.add('hide');}
      if(addNewRoom && !addNewRoom.classList.contains(selectedView)){addNewRoom.classList.add('hide');}
      

      console.log('Query SElect : ' + roomTabElement.querySelector("."+selectedView)?.classList);
      roomTabElement.querySelector("."+selectedView)?.classList.remove("hide");
    } else {
      console.log('Parent element with class roomTab not found');
    }



  }

  editRoom(event: MouseEvent, room: Room): void {

    const roomTabElement = (event.target as HTMLElement).closest('.roomTab');
    console.log(roomTabElement);
    if (roomTabElement != null) {
      console.log('Parent element with class roomTab found:', roomTabElement);
      const roomName = roomTabElement.querySelector(".roomName");
      const roomSize = roomTabElement.querySelector(".roomSize");
      const roomDoors = roomTabElement.querySelector(".roomDoors");

      if(roomName && (roomName as HTMLInputElement).value.trim() !== ''){
        room.name = (roomName as HTMLInputElement).value.trim();
      }

      if(roomSize && (roomSize as HTMLInputElement).value.trim() !== ''){
        const sizeValue = parseInt((roomSize as HTMLInputElement).value.trim(), 10);
        if(!isNaN(sizeValue)){
          room.size = sizeValue;
        }
      }

      if(roomDoors && (roomDoors as HTMLInputElement).value.trim() !== ''){
        const doorsValue = parseInt((roomDoors as HTMLInputElement).value.trim(), 10);
        if(!isNaN(doorsValue)){
          room.doors = [];
          for(let i = 0; i < doorsValue; i++){
            room.doors.push({id: i+1, open: false});
          }
        }
      }

      this.switchRoomView(event, "mainTab");
    }

   /*
    roomName
    roomSize
    roomDoors
    */
  }

  saveNewRoom(event: MouseEvent): void {

    const room: Room = 
    {
      id: 1,
      name: "Undefined",
      size: 250, 
      doors: [],
      roomWindows: [],
      lights: [],
      fans: [],
      temperaturData: [],
    co2SensorData: []
  };

    const roomTabElement = (event.target as HTMLElement).closest('.editRoom');
    console.log(roomTabElement);
    if (roomTabElement != null) {
      console.log('Parent element with class roomTab found:', roomTabElement);
      const roomName = roomTabElement.querySelector(".roomName");
      const roomSize = roomTabElement.querySelector(".roomSize");
      const roomDoors = roomTabElement.querySelector(".roomDoors");

      if(roomName && (roomName as HTMLInputElement).value.trim() !== ''){
        room.name = (roomName as HTMLInputElement).value.trim();
      }

      if(roomSize && (roomSize as HTMLInputElement).value.trim() !== ''){
        const sizeValue = parseInt((roomSize as HTMLInputElement).value.trim(), 10);
        if(!isNaN(sizeValue)){
          room.size = sizeValue;
        }
      }

      if(roomDoors && (roomDoors as HTMLInputElement).value.trim() !== ''){
        const doorsValue = parseInt((roomDoors as HTMLInputElement).value.trim(), 10);
        if(!isNaN(doorsValue)){
          room.doors = [];
          for(let i = 0; i < doorsValue; i++){
            room.doors.push({id: i+1, open: false});
          }
        }
      }

      this.switchRoomView(event, "mainTab");
    }

    console.log(room);


    /* Should be saved with Service and not like this!!!!!  */
    this.rooms.push(room);
    /*
    this.rooms.addRoom(this.room).subscribe((room) => {
      this.router.navigate(['room-list']);
    });
*/
    this.switchRoomView(event, "addNewRoom");
  }

  deleteRoom(event: MouseEvent, room: Room): void {
    const index = this.rooms.indexOf(room);
    if (index !== -1) {
      this.rooms.splice(index, 1);
    }
  }

  /*
  loadDetails(event: MouseEvent) {
    console.log('Button clicked!', event);

   const roomTabElement = event.target.closest('.roomTab');
    if (roomTabElement) {
      console.log('Parent element with class roomTab found:', roomTabElement);
    } else {
      console.log('Parent element with class roomTab not found');
    }
  }*/

  /*
   @ViewChild('roomTab', { static: false }) roomTab!: ElementRef;

  loadDetails(roomTab: ElementRef) {

    // Get references to the detailsTab and mainTab elements
    const editRoom = roomTab.nativeElement.querySelector('.editRoom');
    const detailsTab = roomTab.nativeElement.querySelector('.detailsTab');
    const mainTab = roomTab.nativeElement.querySelector('.mainTab');
   
    // Show the detailsTab and hide the mainTab
    editRoom.style.display = 'none';
    detailsTab.style.display = 'block';
    mainTab.style.display = 'none';

    
    console.log("Works loadDetails")
  }


  loadEditData(roomTab: ElementRef) {
    // Get references to the detailsTab and mainTab elements
    const editRoom = roomTab.nativeElement.querySelector('.editRoom');
    const detailsTab = roomTab.nativeElement.querySelector('.detailsTab');
    const mainTab = roomTab.nativeElement.querySelector('.mainTab');

     
    // Show the detailsTab and hide the mainTab
    editRoom.style.display = 'block';
    detailsTab.style.display = 'none';
    mainTab.style.display = 'none';

    console.log("Works loadEdit")
  }*/
}


const room1: Room = {
  id: 1,
  name: "Living Room",
  size: 250,

  doors: [
        {
          id: 1,
          open: false
        },
        {
          id: 2,
          open: false
        },
      ],
    roomWindows: [
    {
      id: 1,
      open: true
    },
    {
      id: 2,
      open: true
    }
  ],

  lights: [
    {
      id: 1,
      on: true
    },
    {
      id: 2,
      on: false
    }
  ],
  fans: [
    {
      id: 1,
      on: true
    }
  ],

  temperaturData: [
    {
      id: 1,
      cO2value: 25,
      timestamp: new Date()
    },
    {
      id: 2,
      cO2value: 26,
      timestamp: new Date()
    }
  ],
  co2SensorData: [
    {
      id: 1,
      temperaturValue: 22,
      timestamp: new Date()
    },
    {
      id: 2,
      temperaturValue: 23,
      timestamp: new Date()
    }
  ]
};

const room2: Room = {
  id: 2,
  name: "Kitchen",
  size: 100,

  doors: [
    {
      id: 1,
      open: true
    }
  ],
  roomWindows: [
    {
      id: 1,
      open: false
    },
    {
      id: 2,
      open: true
    }
  ],

  lights: [
    {
      id: 1,
      on: true
    },
    {
      id: 2,
      on: true
    }
  ],
  fans: [
    {
      id: 1,
      on: false
    }
  ],

  temperaturData: [
    {
      id: 1,
      cO2value: 27,
      timestamp: new Date()
    },
    {
      id: 2,
      cO2value: 26,
      timestamp: new Date()
    }
  ],
  co2SensorData: [
    {
      id: 1,
      temperaturValue: 21,
      timestamp: new Date()
    },
    {
      id: 2,
      temperaturValue: 22,
      timestamp: new Date()
    }
  ]
};

const room3: Room = {
  id: 3,
  name: "Bathroom",
  size: 50,

  doors: [
    {
      id: 1,
      open: false
    }
  ],
  roomWindows: [
    {
      id: 1,
      open: false
    }
  ],

  lights: [
    {
      id: 1,
      on: true
    }
  ],
  fans: [
    {
      id: 1,
      on: false
    }
  ],

  temperaturData: [
    {
      id: 1,
      cO2value: 28,
      timestamp: new Date()
    },
    {
      id: 2,
      cO2value: 29,
      timestamp: new Date()
    }
  ],
  co2SensorData: [
    {
      id: 1,
      temperaturValue: 22,
      timestamp: new Date()
    },
    {
      id: 2,
      temperaturValue: 23,
      timestamp: new Date()
    }
  ]
};


