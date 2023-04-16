import {Component} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {RoomService} from "../../room.service";
import {Room} from "../../entities/entity";

const emptyRoom: Room = {
  id: 0,
  name: '',
  size: 0,
  doors: [],
  roomWindows: [],
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
export class RoomDetailsComponent {

  public id: number = 0;
  public room: Room = emptyRoom;

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

  public deleteRoom() {
    this.roomService.removeRoom(this.id).subscribe((data) => {
      this.router.navigate(['room-list']);
    });
  }

  public update() {
      this.router.navigate(['update-room/', this.id]);
  }

}
