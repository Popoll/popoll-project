import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { NgModule } from '@angular/core';

import { ChartsModule } from 'ng2-charts/ng2-charts';

import { AppComponent } from './app.component';

// Demo
import { DoughnutChartDemoComponent } from './demo/doughnut-chart.component';

@NgModule({
  declarations: [
    AppComponent,
    // Demo
    DoughnutChartDemoComponent
  ],
  imports: [
    BrowserModule,
    ChartsModule,
    FormsModule,
    HttpModule
  ],
  providers: [],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
