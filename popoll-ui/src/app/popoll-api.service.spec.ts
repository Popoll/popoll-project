import { async, getTestBed, TestBed } from '@angular/core/testing';
import { BaseRequestOptions, Http, Response, ResponseOptions, XHRBackend } from '@angular/http';
import { MockBackend, MockConnection } from '@angular/http/testing';

import { PopollApiService } from './popoll-api.service';
import { Poll } from './poll.model';

describe('Service: PopollApiService', () => {
  let backend: MockBackend;
  let sutService: PopollApiService;

  const emptyPoll = {
    authorName: '',
    question: '',
    channel: '',
    created: new Date(),
    answers: [],
    votes: []
  };

  const fetchedPoll = {
    authorName: 'test',
    question: 'test',
    channel: 'test',
    created: new Date(),
    answers: [ 't', 'e', 's', 't' ],
    votes: [ 1, 2, 3, 4 ]
  };

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      providers: [
        BaseRequestOptions,
        MockBackend,
        PopollApiService,
        {
          deps: [
            MockBackend,
            BaseRequestOptions
          ],
          provide: Http,
          useFactory: (aBackend: XHRBackend, defaultOptions: BaseRequestOptions) => {
            return new Http(aBackend, defaultOptions);
          }
        }
      ]
    });

    const testBed = getTestBed();
    backend = testBed.get(MockBackend);
    sutService = testBed.get(PopollApiService);
  }));

  function setupConnections(mockBackend: MockBackend, options: any) {
    mockBackend.connections.subscribe((connection: MockConnection) => {
      const responseOptions = new ResponseOptions(options);
      const response = new Response(responseOptions);

      connection.mockRespond(response);
    });
  }

  it('#getPoll should return a defined mock when called with undefined', () => {
    setupConnections(backend, {
      body: emptyPoll,
      status: 200
    });

    sutService.getPoll(undefined).then((data: Poll) => {
      expect(data).toBeDefined();
    });
  });

  it('#getPoll should return a defined mock and log an error when request does not work', () => {
    setupConnections(backend, {
      body: emptyPoll,
      status: 400
    });

    sutService.getPoll(1)
    .catch((err: any) => {
      expect(err).toBeDefined();
    })
    .then((data: Poll) => {
      expect(data).toBeDefined();
    });
  });

  it('#getPoll should return a defined Poll when request succeeds', () => {
    setupConnections(backend, {
      body: fetchedPoll,
      status: 200
    });

    sutService.getPoll(1)
    .then((data: Poll) => {
      expect(data).toBeDefined();

      expect(data.authorName).toBe(fetchedPoll.authorName);
      expect(data.question).toBe(fetchedPoll.question);
      expect(data.channel).toBe(fetchedPoll.channel);
      expect(data.created).toBe(fetchedPoll.created);
      expect(data.answers).toBe(fetchedPoll.answers);
      expect(data.votes).toBe(fetchedPoll.votes);
    });
  });
});
