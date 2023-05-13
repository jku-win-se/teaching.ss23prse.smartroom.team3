import { Component, OnInit, Input, SimpleChange	 } from '@angular/core';
//import Chart from 'chart.js/auto';
import Chart, { ChartDataset } from 'chart.js/auto';
import {Room, Fan, Light, Window, Door} from "../entities/entity";


interface DataPoint {
  x: string;
  y: number;
}

@Component({
  selector: 'app-bar-chart',
  templateUrl: './bar-chart.component.html',
  styleUrls: ['./bar-chart.component.scss']
})

export class BarChartComponent {
  //@Input() data: 
  
   data: Room = {
    id: 1,
    name: "test",
    size: 345,
    doors: [
      { id: 5, open: false },
      { id: 1, open: true },
      { id: 2, open: false },
      { id: 3, open: true },
      { id: 4, open: true },
      { id: 6, open: true }
    ],
    fans: [
      { id: 1, isOn: true },
      { id: 2, isOn: true },
      { id: 3, isOn: true },
      { id: 4, isOn: true },
      { id: 5, isOn: true },
      { id: 6, isOn: true },
      { id: 7, isOn: true }
    ],
    lights: [
      { id: 5, isOn: true },
      { id: 4, isOn: true },
      { id: 1, isOn: true },
      { id: 2, isOn: true },
      { id: 3, isOn: true },
      { id: 6, isOn: true },
      { id: 7, isOn: true },
      { id: 8, isOn: true },
      { id: 9, isOn: true }
    ],
    roomWindows: [
      { id: 1, open: true },
      { id: 2, open: true },
      { id: 3, open: true }
    ],
    temperaturData: [
      { timestamp: new Date('2023-05-13'), id: 4, cO2value: 0.7279176088704543 },
      { timestamp: new Date('2023-05-13'), id: 2, cO2value: 0.4714015434047626 },
      { timestamp: new Date('2023-05-13'), id: 1, cO2value: 0.8875773972712395 },
      { timestamp: new Date('2023-05-13'), id: 3, cO2value: 0.2600731460792005 },
      { timestamp: new Date('2023-05-13'), id: 5, cO2value: 0.4013382359328772 }
    ],
    co2SensorData: [
      { timestamp: new Date('2023-05-13'), id: 4, temperaturValue: 0.23486555138978604 },
      { timestamp: new Date('2023-05-13'), id: 2, temperaturValue: 0.16547511492408717 },
      { timestamp: new Date('2023-05-13'), id: 1, temperaturValue: 0.03832830914101948 },
      { timestamp: new Date('2023-05-13'), id: 3, temperaturValue: 0.34226200109755855 },
      { timestamp: new Date('2023-05-13'), id: 5, temperaturValue: 0.17730462355374066 }
    ]
  };

//  @Input() data: Room = {} as Room;

  public chart: any;
  public readonly yAxisMin = 0;
  public readonly yAxisMax = 1;
  public readonly yStep = 1;

  ngOnInit(): void {
    console.log('OnInit - data:', this.data);

    //light/fan/window/door.
    this.createChart();
    this.processData();
    //setInterval(() => this.updateChart(), 100000);
  }

  constructor(){
  }


  ngOnChanges(changes: { [propName: string]: SimpleChange }) {
    if (changes['data']) {
      const change = changes['data'];
      const currentValue = change.currentValue;
      const previousValue = change.previousValue;
      const isFirstChange = change.firstChange;
  
      //this.processData();

      console.log(`'data' property changed. Current value: ${currentValue}, Previous value: ${previousValue}. First change? ${isFirstChange}`);
  
    }
  }



  /*createChart(){
     
    const colors = {
      purple: {
        default: "rgba(149, 76, 233, 1)",
        half: "rgba(149, 76, 233, 0.5)",
        quarter: "rgba(149, 76, 233, 0.25)",
        zero: "rgba(149, 76, 233, 0)"
      },
      indigo: {
        default: "rgba(80, 102, 120, 1)",
        quarter: "rgba(80, 102, 120, 0.25)"
      }
    };
  
    this.chart = new Chart("MyChart", {
      type: 'line', 
      data: {
        datasets: [{
            tension: 0.3,
            label: "Temperature",
            borderColor: "white",
            backgroundColor: "white",
            
            fill: false,
            data: [
              { x: '2022-05-10', y: 22 },
              { x: '2022-05-11', y: 25 },
              { x: '2022-05-12', y: 24 },
              { x: '2022-05-13', y: 23 },
              { x: '2022-05-14', y: 25 },
              { x: '2022-05-15', y: 27 },
              { x: '2022-05-16', y: 26 },
              { x: '2022-05-17', y: 24 },
            ],
            data: this.temperatureData,
            borderWidth: 2,
            pointRadius: 0,
          }
        ]
      },
      options: {
        plugins: {
          title: {
            display: true,
            text: "Room Temperature",
            font: {
              family: "HelveticaNeueExtended",
              weight: "bold",
              size: 32,
           
            },
            position: "top",
            align: "start",  
            color: 'white',
          },
          legend: {
            labels: {
              color: 'White',
              padding: 10,
              font: {
                family: "HelveticaNeueExtended",
                size: 12
              },
            }
          }
        },
        scales: {
          y:{
            ticks: {
              //stepSize: 1,
              maxTicksLimit: 8,
              color: 'White',
              padding: 10,
              font: {
                family: "HelveticaNeueExtended",
                size: 12
              },
              callback: function(value, index, values) {
                return value + "°C";
              }
            },
            grid: {
              color: "rgba(255,255,255,0.15)"
            }
          },
          x: {
              afterBuildTicks: (a) => (a.ticks = [
              {
                value: 2
              }, {
                value: 5
              }, {
                value: 8
              }, {
                value: 11
              }]),
              ticks: {
                count: 5,
                autoSkip: false,
                stepSize: 5,
                color: 'White',
                padding: 10,
                font: {
                  family: "HelveticaNeueExtended",
                  size: 12
                }
              }
            /*ticks: {
              autoSkip: false,
              stepSize: 5,
              color: 'White',
              padding: 10,
              font: {
                family: "HelveticaNeueExtended",
                size: 12
              }
            }
              ,
              callback: function(value, index, values) {
                return value + "am";
              }
            
          },
        },
        animation: {
          duration: 0,
          easing: 'linear',
        }
      }
    });
  }*/

  createChart(){
  
    this.chart = new Chart("MyBarChart", {
      type: 'bar', //this denotes tha type of chart

      data: {// values on X-Axis
        labels: [], 
	       datasets: [
          {
            label: "",
            data: [],
            backgroundColor: 'white',
            borderColor: "white",
            barThickness: 1
          },

        ]
      },
      options: {
        plugins: {
          title: {
            display: true,
            text: "Active Devices",
            font: {
              family: "HelveticaNeueExtended",
              weight: "bold",
              size: 32,
           
            },
            position: "top",
            align: "start",  
            color: 'white',
          },
          legend: {
            display: false,
            labels: {
              color: 'White',
              padding: 10,
              font: {
                family: "HelveticaNeueExtended",
                size: 12
              },
            }
          }
        }, 
        elements: {
     
        },
        scales: {
          y: {
            min: 0,
            max: 1,
            ticks: {
              stepSize: 0.2,
              color: 'White',
              padding: 10,
              font: {
                family: "HelveticaNeueExtended",
                size: 12
              }
            },
            grid: {
              color: "rgba(255,255,255,0.15)"
            },
            suggestedMin: 0,
            suggestedMax: 1,
            position: 'left',
            type: 'linear',
            display: true,
            title: {
              display: true,
              text: 'Value',
              color: 'White',
              font: {
                family: "HelveticaNeueExtended",
                size: 12,
                weight: 'bold'
              },
              padding: {
                top: 12,
                bottom: 12
              }
            }
          }, 
          x: {
            ticks: {
              count: 5,
              autoSkip: false,
              stepSize: 5,
              color: 'White',
              padding: 10,
              font: {
                family: "HelveticaNeueExtended",
                size: 12
              }
            }
          }, 
        }
      }
    });
    this.chart.canvas.parentNode.style.height = "300px";
  }
  

  fanDataPoints: { label: string, value: boolean }[] = [];
  lightDataPoints: { label: string, value: boolean }[] = [];
  windowDataPoints: { label: string, value: boolean }[] = [];
  doorDataPoints: { label: string, value: boolean }[] = [];


  private processData() {
    // Process fan data points
    this.data.fans.forEach((fan: Fan) => {
      const existingDataPointIndex = this.fanDataPoints.findIndex(dataPoint => dataPoint.label === "Fan_" + fan.id);

      if (existingDataPointIndex !== -1) {
        this.fanDataPoints[existingDataPointIndex].value = fan.isOn;

        const dataset = this.chart.data.datasets[0];
        const labelIndex = this.chart.dataset.labels.indexOf("Fan_"+fan.id);
        this.chart.dataset.data[labelIndex] = [fan.isOn ? 1:0];
      } else {
       // this.fanDataPoints.push({ label: "Fan_"+fan.id, value: fan.isOn });
        this.chart.data.datasets[0].data.push([fan.isOn ? 1:0]);
        this.chart.data.labels.push("Fan_"+fan.id);
      }
    });

    // Process light data points
    this.data.lights.forEach((light: Light) => {
      const existingDataPointIndex = this.lightDataPoints.findIndex(dataPoint => dataPoint.label === "Light_" + light.id);

      if (existingDataPointIndex !== -1) {
        this.lightDataPoints[existingDataPointIndex].value = light.isOn;

        const dataset = this.chart.data.datasets[0];
        const labelIndex = this.chart.dataset.labels.indexOf("Light_"+light.id);
        this.chart.dataset.data[labelIndex] = [light.isOn ? 1:0];
      } else {
       // this.fanDataPoints.push({ label: "Fan_"+fan.id, value: fan.isOn });
        this.chart.data.datasets[0].data.push([light.isOn ? 1:0]);
        this.chart.data.labels.push("Light_"+light.id);
      }
    });


    this.data.roomWindows.forEach((window: Window) => {
      const existingDataPointIndex = this.windowDataPoints.findIndex(dataPoint => dataPoint.label === "Window_" + window.id);

      if (existingDataPointIndex !== -1) {
        this.windowDataPoints[existingDataPointIndex].value = window.open;

        const dataset = this.chart.data.datasets[0];
        const labelIndex = this.chart.dataset.labels.indexOf("Window_"+window.id);
        this.chart.dataset.data[labelIndex] = [window.open ? 1:0];
      } else {
       // this.fanDataPoints.push({ label: "Fan_"+fan.id, value: fan.isOn });
        this.chart.data.datasets[0].data.push([window.open ? 1:0]);
        this.chart.data.labels.push("Window_"+window.id);
      }
    });

    this.data.doors.forEach((door: Door) => {
      const existingDataPointIndex = this.doorDataPoints.findIndex(dataPoint => dataPoint.label === "Door_" + door.id);

      if (existingDataPointIndex !== -1) {
        this.doorDataPoints[existingDataPointIndex].value = door.open;

        const dataset = this.chart.data.datasets[0];
        const labelIndex = this.chart.dataset.labels.indexOf("Door_"+door.id);
        this.chart.dataset.data[labelIndex] = [door.open ? 1:0];
      } else {
       // this.fanDataPoints.push({ label: "Fan_"+fan.id, value: fan.isOn });
        this.chart.data.datasets[0].data.push([door.open ? 1:0]);
        this.chart.data.labels.push("Door_"+door.id);
      }
    });


    // Update the graph with the new data points
    this.updateChart();
  }


  private updateChart() {
    console.log('Fan Data Points:', this.fanDataPoints);
    console.log('Light Data Points:', this.lightDataPoints);

  

  this.chart.data.datasets[0].data.push('1');
  this.chart.data.labels.push("Window");

    /*if (this.chart.data.datasets[0].data.length > 13) {
      this.chart.data.datasets[0].data.shift();
      this.chart.data.labels.shift();
    }*/
  
    this.chart.update();
  }
  

  /*private getRandomTemperature() {
  
    return Math.floor(Math.random() * 8 + 15);
  }*/

}