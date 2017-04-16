export interface PollMock {
  authorName: string;
  question: string;
  channel: string;
  created: Date;
  answers: string[];
  votes: number[];
}
