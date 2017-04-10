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
	
	private static final String URLENCODED_VOTE_PAYLOAD = "payload=%7B%22actions%22%3A%5B%7B%22name%22%3A%22Oui%22%2C%22type%22%3A%22button%22%2C%22value%22%3A%22Oui%22%7D%5D%2C%22callback_id%22%3A%22static_callback_id%22%2C%22team%22%3A%7B%22id%22%3A%22T33M0A58R%22%2C%22domain%22%3A%22esgi-4al%22%7D%2C%22channel%22%3A%7B%22id%22%3A%22D33L5K7AL%22%2C%22name%22%3A%22directmessage%22%7D%2C%22user%22%3A%7B%22id%22%3A%22U34E31XM3%22%2C%22name%22%3A%22mrkloan%22%7D%2C%22action_ts%22%3A%221491147259.820588%22%2C%22message_ts%22%3A%221491147254.000016%22%2C%22attachment_id%22%3A%221%22%2C%22token%22%3A%22xtZSVP1GVN7ewp54Jzs0Mzv6%22%2C%22is_app_unfurl%22%3Afalse%2C%22response_url%22%3A%22https%3A%5C%2F%5C%2Fhooks.slack.com%5C%2Factions%5C%2FT33M0A58R%5C%2F163286607394%5C%2FoOsPmH8JGgumw0c8PsLxTgkL%22%7D";
	
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