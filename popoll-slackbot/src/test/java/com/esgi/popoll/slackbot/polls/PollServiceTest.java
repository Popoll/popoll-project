package com.esgi.popoll.slackbot.polls;

import io.fries.slack.webhook.message.Message;
import io.fries.slack.webhook.trigger.Trigger;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PollServiceTest {
	
	private static final String TRIGGER_TEXT = "\"Question\" \"Answer 1\" \"Answer 2\" \"Answer 3\"";
	private static final String AUTHOR = "The Author";
	
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
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowOnCreatePollFromNull() {
		pollService.createPollFromTrigger(null);
	}
	
	@Test
	public void shouldCreateMessageFromPoll() {
		final Poll poll = Poll.builder().author(AUTHOR).question("Question").answers(Arrays.asList(
			"Answer 1", "Answer 2", "Answer 3"
		)).build();
		final Message message = pollService.createMessageFromPoll(poll);
		
		assertThat(message.getAttachments()).hasSize(1);
		assertThat(message.getAttachments().get(0).getCallbackId()).isEqualTo("static_callback_id");
		assertThat(message.getAttachments().get(0).getFallback()).isEqualTo(poll.getQuestion());
		assertThat(message.getAttachments().get(0).getTitle()).isEqualTo(poll.getQuestion());
		assertThat(message.getAttachments().get(0).getAuthorName()).isEqualTo(poll.getAuthor());
		assertThat(message.getAttachments().get(0).getActions()).hasSize(poll.getAnswers().size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowOnCreateMessageFromNull() {
		pollService.createMessageFromPoll(null);
	}
}