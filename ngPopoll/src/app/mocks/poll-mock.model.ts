export interface PollMock {
  authorName: string;
  question: string;
  channel: string;
  created: Date;
  answers: string[];
  votes: number[];
}

export function GeneratePollMock(pAnswers?: string[], pVotes?: number[]): PollMock {
  const mock = {
    authorName: "Vincent Milano",
    question: "Is that a good question ?",
    channel: "#testChan",
    created: new Date(),
    answers: pAnswers || [ 'Yep', 'Nope', 'Maybe..' ],
    votes: pVotes || [ 42, 1, 12 ]
  };

  console.warn("Generated PollMock: ");
  console.warn(mock);

  return mock;
}
