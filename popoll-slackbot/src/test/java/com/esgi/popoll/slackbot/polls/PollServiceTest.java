package com.esgi.popoll.slackbot.polls;

import io.fries.slack.webhook.message.Action;
import io.fries.slack.webhook.message.Message;
import io.fries.slack.webhook.trigger.ActionPayload;
import io.fries.slack.webhook.trigger.Trigger;
import io.fries.slack.webhook.trigger.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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
		assertThat(message.getAttachments().get(0).getCallbackId()).isNull();
		assertThat(message.getAttachments().get(0).getFallback()).isEqualTo(poll.getQuestion());
		assertThat(message.getAttachments().get(0).getTitle()).isEqualTo(poll.getQuestion());
		assertThat(message.getAttachments().get(0).getAuthorName()).isEqualTo(poll.getAuthor());
		assertThat(message.getAttachments().get(0).getActions()).hasSize(poll.getAnswers().size());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowOnCreateMessageFromNull() {
		pollService.createMessageFromPoll(null);
	}

	@Test
	public void shouldCreatePollVoteFromActionPayload() {
		final String SURVEY_ID = "1234";
		final String USER_ID = "123ABC";
		final String ANSWER = "Oui";

		final ActionPayload actionPayload = ActionPayload.builder()
			.callbackId(SURVEY_ID)
			.user(User.builder().id(USER_ID).build())
			.actions(Collections.singletonList(
				Action.builder().value(ANSWER).build()
			))
			.build();

		final PollVote pollVote = pollService.createPollVoteFromActionPayload(actionPayload);

		assertThat(pollVote.getSurveyId()).isEqualTo(SURVEY_ID);
		assertThat(pollVote.getUserId()).isEqualTo(USER_ID);
		assertThat(pollVote.getAnswer()).isEqualTo(ANSWER);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowWhenCreatingFromNull() {
		pollService.createPollVoteFromActionPayload(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowWhenActionPayloadIsNull() {
		final ActionPayload actionPayload = ActionPayload.builder()
			.actions(null)
			.build();

		pollService.createPollVoteFromActionPayload(actionPayload);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowWhenActionPayloadIsEmpty() {
		final ActionPayload actionPayload = ActionPayload.builder()
			.actions(new ArrayList<>())
			.build();

		pollService.createPollVoteFromActionPayload(actionPayload);
	}
}