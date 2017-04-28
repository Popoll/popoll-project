import { GeneratePollMock } from './poll.model';

describe('Function: GeneratePollMock', () => {
  const emptyMock = {
    authorName: '',
    question: '',
    channel: '',
    answers: [],
    votes: []
  };
  const fetchedMock = {
    authorName: 'Vincent Milano',
    question: 'Is that a good question ?',
    channel: '#TestChan',
    answers: [ 'Yep', 'Nope', 'Maybe' ],
    votes: [ 42, 1, 12 ]
  };

  it('should return a defined Poll and never null', () => {
    const generatedMock = GeneratePollMock();

    expect(generatedMock).toBeDefined();
    expect(generatedMock).not.toBeNull();
    expect(generatedMock.authorName).toBeDefined();
    expect(generatedMock.question).toBeDefined();
    expect(generatedMock.channel).toBeDefined();
    expect(generatedMock.created).toBeDefined();
    expect(generatedMock.answers).toBeDefined();
    expect(generatedMock.votes).toBeDefined();
  });

  it('should return a fetched mock when called without params', () => {
    const generatedMock = GeneratePollMock();

    expect(generatedMock.authorName).toBe(fetchedMock.authorName);
    expect(generatedMock.question).toBe(fetchedMock.question);
    expect(generatedMock.channel).toBe(fetchedMock.channel);
    // Test date is nonsense so we don't test it
    expect(generatedMock.answers).toEqual(fetchedMock.answers);
    expect(generatedMock.votes).toEqual(fetchedMock.votes);
  });

  it('should return a fetched mock when called with empty param set at false', () => {
    const generatedMock = GeneratePollMock(false);

    expect(generatedMock.authorName).toBe(fetchedMock.authorName);
    expect(generatedMock.question).toBe(fetchedMock.question);
    expect(generatedMock.channel).toBe(fetchedMock.channel);
    // Test date is nonsense so we don't test it
    expect(generatedMock.answers).toEqual(fetchedMock.answers);
    expect(generatedMock.votes).toEqual(fetchedMock.votes);
  });

  it('should return an empty mock when called with empty param set at true', () => {
    const generatedMock = GeneratePollMock(true);

    expect(generatedMock.authorName).toBe(emptyMock.authorName);
    expect(generatedMock.question).toBe(emptyMock.question);
    expect(generatedMock.channel).toBe(emptyMock.channel);
    // Test date is nonsense so we don't test it
    expect(generatedMock.answers).toEqual(emptyMock.answers);
    expect(generatedMock.votes).toEqual(emptyMock.votes);
  });
});
