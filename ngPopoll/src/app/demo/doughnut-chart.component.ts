import { Component } from '@angular/core';

@Component({
  selector: 'doughnut-chart-demo',
  templateUrl: './doughnut-chart.component.html'
})
export class DoughnutChartDemoComponent {
  // Doughnut
  public doughnutChartLabels: string[] = [
    'Download Sales',
    'In-Store Sales',
    'Mail-Order Sales'
  ];
  public doughnutChartData: number[] = [ 350, 450, 100 ];
  public doughnutChartType: string = 'doughnut';

  // Events
  public chartClicked(e: any): void {
    // console.log(e);
  }

  public chartHovered(e: any): void {
    // console.log(e);
  }
}