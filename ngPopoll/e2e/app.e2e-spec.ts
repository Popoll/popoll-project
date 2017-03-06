import { NgPopollPage } from './app.po';

describe('ng-popoll App', () => {
  let page: NgPopollPage;

  beforeEach(() => {
    page = new NgPopollPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
