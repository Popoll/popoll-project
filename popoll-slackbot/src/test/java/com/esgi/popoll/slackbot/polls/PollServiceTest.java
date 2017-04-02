package com.esgi.popoll.slackbot.polls;

import io.fries.slack.webhook.trigger.Trigger;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PollServiceTest {
	
	private static final String TRIGGER_TEXT = "\"Question\" \"Answer 1\" \"Answer 2\" \"Answer 3\"";
	public static final String AUTHOR = "The Author";
	
	private PollService pollService;
	
	@Before
	public void initPollService() {
		this.pollService = new PollServiceImpl();
	}
	
	@Test
	public void shouldCreatePollFromTrigger() {
		final Trigger trigger = Trigger.builder().text(TRIGGER_TEXT).userName(AUTHOR).build();
		
		final Poll poll = pollService.createPollFromTrigger(trigger);
		
		assertThat(poll.getQuestion()).isEqualTo("Question");
		assertThat(poll.getAnswers()).containsExactlyInAnyOrder(
			"Answer 1",
			"Answer 2",
			"Answer 3"
		);
		assertThat(poll.getAuthor()).isEqualTo(AUTHOR);
	}
}