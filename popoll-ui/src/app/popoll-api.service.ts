import { Injectable } from '@angular/core';
import { Http } from '@angular/http'

import { Poll, GeneratePollMock } from "./models/poll.model";

import 'rxjs/add/operator/toPromise';

@Injectable()
export class PopollApiService {

  private apiUrl = "";

  constructor(private http: Http) {}

  public getPoll(ppid: number): Promise<Poll> {
    if (undefined === ppid) {
      return Promise.resolve(GeneratePollMock());
    }

    return this.http.get(this.apiUrl)
               .toPromise()
               .then((res: any) => res.json() as Poll)
               .catch((err: any) => {
                 console.error(err);
                 Promise.resolve(GeneratePollMock());
               });
  }
}