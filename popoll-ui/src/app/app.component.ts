import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';

import { PopollApiService } from './popoll-api.service';
import { Poll, GeneratePollMock } from './poll.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: [ 'app.component.css' ]
})
export class AppComponent implements OnInit, OnDestroy {

  // Doughnut datas
  public isDataAvailable = false;
  public poll = GeneratePollMock(true); // Empty mock for chart init

  // Allow chart resizing
  public doughnutOptions: any = {
    responsive: true,
    maintainAspectRatio: false
  };

  // Define doughnut colors
  // Add [colors]="doughnutColors" on doughnut
  public defaultColors = [ '#FF7360', '#6FC8CE', '#B9E8E0', '#FAFFF2', '#FFFCC4' ];
  public adobeGenColors = [ '#930CE8', '#FF0000', '#EB790C', '#125BFF', '#FFE012' ];
  public doughnutColors: any[] = [{
      backgroundColor: this.adobeGenColors
  }];

  constructor(
    private activatedRoute: ActivatedRoute,
    private popollService: PopollApiService
  ) {}

  // Angular events
  ngOnInit(): void {
    this.activatedRoute.queryParams
    .subscribe(
      (params: Params) => {
        this.popollService.getPoll(params['ppid'])
        .then((res: Poll) => {
          this.poll = res;
          this.isDataAvailable = true;
        })
        .catch((err: any) => console.error(err));
      },
      (err: any) => console.error(err)
    );
  }

  ngOnDestroy(): void {} // In order to unsubscribe automatically to queryParams

  // Doughnut events
  public chartClicked(e: any): void {
    console.log(e);
  }
}
