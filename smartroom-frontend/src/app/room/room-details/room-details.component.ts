import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RoomService } from '../../room.service';
import { Fan, Room } from '../../entities/entity';
import { BarChartComponent } from 'src/app/bar-chart/bar-chart.component';
import { LineChartComponent } from 'src/app/line-chart/line-chart.component';
import { Co2LineChartComponent } from 'src/app/line-chart-co2/line-chart-co2.component';
import { RoomListComponent } from '../room-list/room-list.component';

var emptyRoom: Room = {
  id: 100000,
  name: '_',
  size: 0,
  doors: [],
  roomWindows: [],
  peopleData: [],
  lights: [],
  fans: [],
  temperatureData: [],
  co2SensorData: []
};

@Component({
  selector: 'app-room-details',
  templateUrl: './room-details.component.html',
  styleUrls: ['./room-details.component.scss']
})
export class RoomDetailsComponent implements OnInit {
  @ViewChild(BarChartComponent) barChartComponent!: BarChartComponent;
  @ViewChild(LineChartComponent) lineChartComponent!: LineChartComponent;
  @ViewChild(Co2LineChartComponent) co2lineChartComponent!: Co2LineChartComponent;

  public id: number = 0;
  public room!: Room;

  constructor(
    private route: ActivatedRoute,
    private roomService: RoomService,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Fetch room details from the server
    this.route.params.subscribe(params => {
      this.id = +params['id'];
      this.roomService.getRoom(this.id).subscribe((room) => {
        this.room = room;
      });
    });

    /* Updates room information - necessary for charts */
    setInterval(() => {
      this.updateRoomLoop();
    }, 1000);
  }

  public deleteRoom() {
    console.log("Log ID: ");
    console.log(this.id);

    // Create an instance of RoomListComponent to access its methods
    const roomList = new RoomListComponent(this.roomService, this.router);
    roomList.deleteRoom(this.room);
    roomList.deleteRoom(this.room);
    roomList.loadRooms();

    /* this.roomService.removeRoom(this.id).subscribe((data) => {
      this.router.navigate(['room-list']);
    }); */
  }

  public update() {
    // Navigate to the update room page
    this.router.navigate(['update-room/', this.id]);
  }

  public addValues() {
    // Add values to the room
    this.roomService.addValues(this.id).subscribe((room) => {
      this.router.navigate(['dashboard']);
    });
  }

  updateDoorState(door: any) {
    console.log('Door state changed:', door.open);
    this.updateRoom();
  }

  updateFanState(fan: any) {
    console.log('Fan state changed:', fan.on);
    this.updateRoom();
  }

  updateLightState(light: any) {
    console.log('Light state changed:', light.on);
    this.updateRoom();
  }

  updateWindowState(window: any) {
    console.log('Window state changed:', window.open);
    this.updateRoom();
  }

  public removeFanFromRoom(device: Fan) {
    const index = this.room.fans.findIndex(d => d.id === device.id);
    if (index !== -1) {
      // Remove the fan from the room's fan list
      this.room.fans.splice(index, 1);
      this.roomService.updateRoom(this.room).subscribe((data) => {
        this.router.navigate(['room-details/' + this.room.id]);
      });
      console.log(this.room);
      this.updateRoom();
    }
  }

  public updateRoom() {
    // Update the room data on the server
    this.roomService.updateRoom(this.room).subscribe((data) => {
      this.room = data;
    });

    // Update the charts
    this.barChartComponent.updateChartData();
    this.lineChartComponent.updateChart();
    this.co2lineChartComponent.updateChart();
  }

  public updateRoomLoop() {
    // Continuously update the room data on the server
    this.roomService.updateRoom(this.room).subscribe((data) => {
      this.room = data;
    });

    // Update the charts
    this.lineChartComponent.updateChart();
    this.co2lineChartComponent.updateChart();
  }
}
