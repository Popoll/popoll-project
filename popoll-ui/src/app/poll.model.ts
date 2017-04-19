export interface Poll {
  authorName: string;
  question: string;
  channel: string;
  created: Date;
  answers: string[];
  votes: number[];
}

export function GeneratePollMock(empty?: boolean): Poll {
  if (empty) {
    return {
      authorName: '',
      question: '',
      channel: '',
      created: new Date(),
      answers: [],
      votes: []
    };
  }
  else {
    return {
      authorName: 'Vincent Milano',
      question: 'Is that a good question ?',
      channel: '#TestChan',
      created: new Date(),
      answers: [ 'Yep', 'Nope', 'Maybe' ],
      votes: [ 42, 1, 12 ]
    };
  }
}
