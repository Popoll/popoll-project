import { Component } from '@angular/core';

import { GeneratePollMock } from "../mocks/poll-mock.model";

@Component({
  selector: 'app-doughnut-chart-demo',
  templateUrl: './doughnut-chart-demo.html'
})
export class DoughnutChartDemoComponent {
  // Doughnut
  public mock = GeneratePollMock();

  // Events
  public chartClicked(e: any): void {
    console.log(e);
  }

  public chartHovered(e: any): void {
    console.log(e);
  }
}
