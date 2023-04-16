

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
    cO2value: number;
    timestamp: Date;
  }
  
  export type Co2SensorData = {
    id: number;
    temperaturValue: number;
    timestamp: Date;
  }
  
  
  export type Room = {
    id: number;
    name: string;
    size: number;
  
    door: Door[];
    windows: Window[];
  
    lights: Light[];
    fans: Fan[];
  
    temperaturData: TemperaturData[];
    co2SensorData: Co2SensorData[];
  }