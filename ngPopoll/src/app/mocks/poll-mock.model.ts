export interface PollMock {
  authorName: string;
  question: string;
  channel: string;
  created: Date;
  answers: string[];
  votes: number[];
}

export function GeneratePollMock(mAnswers?: string[], mVotes?: number[]): PollMock {
  const mock = {
    authorName: "Vincent Milano",
    question: "Test question",
    channel: "#testChan",
    created: new Date(),
    answers: mAnswers || [ 'Download Sales', 'In-Store Sales', 'Mail-Order Sales' ],
    votes: mVotes || [ 350, 450, 100 ]
  };

  console.warn("Generated PollMock: ");
  console.warn(mock);

  return mock;
}
