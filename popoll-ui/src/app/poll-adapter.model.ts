import { Poll } from './poll.model';

export interface PollAdapter {
  author: string;
  question: string;
  channel: string;
  answers: any[];
  votes: any[];
}

export function toPoll(pollDto: PollAdapter): Poll {
  let author = pollDto.author;
  let question = pollDto.question;
  let channel = pollDto.channel;
  let answers: string[] = [];
  let votes: number[] = [];

  pollDto.answers.forEach(answer => {
    answers.push(answer.answer);
    votes.push(0);
  });

  pollDto.votes.forEach(vote => votes[answers.indexOf(vote.answer)]++);

  return {
    authorName: author,
    question,
    channel,
    created: new Date(),
    answers,
    votes
  };
}