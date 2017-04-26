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
  pollDto.answers.forEach(answer => answers.push(answer.answer));

  let votes: number[] = [answers.length];
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