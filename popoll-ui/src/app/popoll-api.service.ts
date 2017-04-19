import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

import { environment } from '../environments/environment';
import { Poll, GeneratePollMock } from './poll.model';

import 'rxjs/add/operator/toPromise';

@Injectable()
export class PopollApiService {

  private apiUrl = '';

  constructor(private http: Http) {}

  public getPoll(ppid: number | undefined): Promise<Poll> {
    // No ppid when first load on app.component Observable - Do not log error
    if (undefined === ppid) return Promise.resolve(GeneratePollMock());

    if (environment.enableDebug) console.log('ppid: ' + ppid);

    return this.http.get(this.apiUrl + '/' + ppid)
          .toPromise()
          .then((res: any) => res.json() as Poll)
          .catch((err: any) => this.handleError(err));
  }

  private handleError(err: any): Promise<Poll> {
    if (environment.enableDebug) console.error(err);

    return Promise.resolve(GeneratePollMock());
  }
}
