

export type Door = {
    id: number;
    open: boolean;
  }

  export type Window = {
    id: number;
    open: boolean;
  }

  export type Light = {
    id: number;
    on: boolean;
  }

  export type Fan = {
    id: number;
    on: boolean;
  }

  export type TemperaturData = {
    id: number;
    temperatureValue: number;
    timestamp: Date;
  }

  export type PeopleData = {
    id: number;
    count: number;
    timestamp: Date;
  }

  export type Co2SensorData = {
    id: number;
    cO2value: number;
    timestamp: Date;
  }

  export type Room = {
    id: number;
    name: string;
    size: number;

    doors: Door[];
    roomWindows: Window[];
    peopleData: PeopleData[];

    lights: Light[];
    fans: Fan[];

    temperaturData: TemperaturData[];
    co2SensorData: Co2SensorData[];
  }





export const emptyRoom: Room = {
  id: 0,
  name: '',
  size: 0,
  doors: [],
  roomWindows: [],
  peopleData: [],
  lights: [],
  fans: [],
  temperaturData: [],
  co2SensorData: []
}

export const emptyDoor: Door = {
  id: 0,
  open: true
}

export const emptyWindow: Window = {
  id: 0,
  open: true
}

export const emptyLight: Light = {
  id: 0,
  on: true
}

export const emptyFan: Fan = {
  id: 0,
  on: true
}
