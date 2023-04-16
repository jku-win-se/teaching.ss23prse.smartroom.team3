import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { RoomsComponent } from './room/rooms/rooms.component';
import { DevicesComponent } from './devices/devices.component';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import { RoomService } from './room.service';
import { FormsModule } from '@angular/forms';
import { RoomListComponent } from './room/room-list/room-list.component';
import { AddRoomComponent } from './room/add-room/add-room.component';
import { RoomDetailsComponent } from './room/room-details/room-details.component';
import { UpdateRoomComponent } from './room/update-room/update-room.component';


@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    RoomsComponent,
    DevicesComponent,
    RoomListComponent,
    AddRoomComponent,
    RoomDetailsComponent,
    UpdateRoomComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    HttpClientModule,
    RoomService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
