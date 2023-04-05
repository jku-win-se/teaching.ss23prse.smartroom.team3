import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { RoomsComponent } from './rooms/rooms.component';
import { DevicesComponent } from './devices/devices.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    RoomsComponent,
    DevicesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
