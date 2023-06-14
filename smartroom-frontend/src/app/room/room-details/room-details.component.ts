import {Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {RoomService} from "../../room.service";
import {Fan, Room} from "../../entities/entity";
import { BarChartComponent } from 'src/app/bar-chart/bar-chart.component';
import { LineChartComponent } from 'src/app/line-chart/line-chart.component';
import { Co2LineChartComponent } from 'src/app/line-chart-co2/line-chart-co2.component';


var emptyRoom: Room = {
  id: 100000,
  name: '_',
  size: 0,
  doors: [],
  roomWindows: [],
  peopleData: [],
  lights: [],
  fans: [],
  temperaturData: [],
  co2SensorData: []
}

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

  constructor(private route: ActivatedRoute, private roomService: RoomService, private router: Router) {
  }

  ngOnInit() : void {
    this.route.params.subscribe(params => {
      this.id = +params['id'];
      this.roomService.getRoom(this.id).subscribe((room) => {
        this.room = room;
      });
    });
    

    /* Updates room information - necessary for charts*/
    setInterval(() => {
      this.updateRoomLoop();
    }, 1000);
  }

  public deleteRoom() {
    this.roomService.removeRoom(this.id).subscribe((data) => {
      this.router.navigate(['room-list']);
    });
  }

  public update() {
      this.router.navigate(['update-room/', this.id]);
  }

  public addValues() {
    this.roomService.addValues(this.id).subscribe((room) => {
      this.router.navigate(['dashboard'])
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
      this.room.fans.splice(index, 1);
      this.roomService.updateRoom(this.room).subscribe((data) => {
        this.router.navigate(['room-details/' + this.room.id]);
      });
      console.log(this.room);
      this.updateRoom();
    }
  }


  public updateRoom() {
    // Get current room dqta
    this.roomService.updateRoom(this.room).subscribe((data) => {
      this.room = data;
    });


    //Shoot updates for each chart
    this.barChartComponent.updateChartData();
    this.lineChartComponent.updateChart();
    this.co2lineChartComponent.updateChart();
  }

  public updateRoomLoop() {
    this.roomService.updateRoom(this.room).subscribe((data) => {
      this.room = data;
    });

    console.log("CO2 Push: " + this.room.co2SensorData[this.room.co2SensorData.length -1].cO2value);
    console.log("Temp Push: " + this.room.temperaturData[this.room.temperaturData.length -1].temperatureValue);


    this.lineChartComponent.updateChart();
    this.co2lineChartComponent.updateChart();
  }


}
