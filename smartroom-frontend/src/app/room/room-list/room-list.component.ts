import { Component } from '@angular/core';
import {Room} from "../../entities/entity";
import {RoomService} from "../../room.service";

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrls: ['./room-list.component.scss']
})
export class RoomListComponent {

  public rooms: Room[] = [room1, room2, room3];

  constructor(private roomService: RoomService) {
  }

  ngOnInit() {
    this.roomService.getRooms().subscribe((rooms) => {
      this.rooms = rooms;
      console.log(this.rooms);
    })
  }

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