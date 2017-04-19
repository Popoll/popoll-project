import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ChartsModule } from 'ng2-charts/ng2-charts';

import { AppComponent } from './app.component';
import { PopollApiService } from "./popoll-api.service";

@NgModule({
  declarations: [ AppComponent ],
  imports: [
    BrowserModule,
    ChartsModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot([])
  ],
  providers: [ PopollApiService ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
