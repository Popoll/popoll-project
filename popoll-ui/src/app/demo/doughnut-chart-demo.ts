import { Component } from '@angular/core';

import { GeneratePollMock } from '../mocks/poll-mock.model';

@Component({
  selector: 'app-doughnut-chart-demo',
  templateUrl: './doughnut-chart-demo.html',
  styleUrls: [ 'doughnut-chart-demo.css' ]
})
export class DoughnutChartDemoComponent {
  // Doughnut datas
  public mock = GeneratePollMock();

  // Allow chart resizing
  public doughnutOptions: any = {
    responsive: true,
    maintainAspectRatio: false
  };

  // Define doughnut colors
  // Add [colors]="doughnutColors" on doughnut
  public doughnutColors: Array<any> = [{
      backgroundColor: [
        '#FF7360',
        '#6FC8CE',
        '#B9E8E0',
        '#FAFFF2',
        '#FFFCC4'
      ]
  }];

  // Events
  public chartClicked(e: any): void {
    console.log(e);
  }

  public chartHovered(e: any): void {
    console.log(e);
  }
}
