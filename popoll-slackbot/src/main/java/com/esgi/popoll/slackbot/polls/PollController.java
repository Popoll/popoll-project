package com.esgi.popoll.slackbot.polls;

import com.esgi.popoll.slackbot.config.ActionPayloadAdapter;
import com.esgi.popoll.slackbot.config.TriggerAdapter;
import io.fries.slack.webhook.WebHook;
import io.fries.slack.webhook.message.Action;
import io.fries.slack.webhook.message.Attachment;
import io.fries.slack.webhook.message.Message;
import io.fries.slack.webhook.trigger.ActionPayload;
import io.fries.slack.webhook.trigger.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import java.util.Collections;
import java.util.stream.Collectors;

import static io.fries.slack.webhook.message.Message.ResponseType.EPHEMERAL;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
class PollController {
	
	private final PollService pollService;
	private final TriggerAdapter triggerAdapter;
	private final ActionPayloadAdapter actionPayloadAdapter;
	
	public PollController(
		final PollService pollService,
		final TriggerAdapter triggerAdapter,
		final ActionPayloadAdapter actionPayloadAdapter
	) {
		this.pollService = pollService;
		this.triggerAdapter = triggerAdapter;
		this.actionPayloadAdapter = actionPayloadAdapter;
	}
	
	@PostMapping(path = "/")
	public Message appRoot() {
		return Message.builder().mrkdwn(true).text("Greetings from the *Popoll* API!").build();
	}
	
	@PostMapping(path = "/polls")
	public Message createNewPoll(@RequestBody final String urlencodedData) {
		final Trigger trigger = triggerAdapter.toTrigger(urlencodedData);

		if(!pollService.validateTriggerToken(trigger))
			throw new InvalidTokenException();
		
		final Poll poll = pollService.createPollFromTrigger(trigger);
		
		try {
			final Poll persistedPoll = pollService.persistPoll(poll);
			return pollService.createMessageFromPoll(persistedPoll);
		}
		catch(RestClientException e) {
			return Message.builder()
				.attachments(Collections.singletonList(
					Attachment.builder()
						.color("#ff0000")
						.text("An unexpected error occured: " + e.getMessage())
						.build()
				)).build();
		}
	}
	
	@PostMapping(path = "/polls/vote")
	public Message handlePollVote(@RequestBody final String urlencodedData) {
		final ActionPayload actionPayload = actionPayloadAdapter.toActionPayload(urlencodedData);
		
		if(!pollService.validateActionPayloadToken(actionPayload))
			throw new InvalidTokenException();
		
		final PollVote pollVote = pollService.createPollVoteFromActionPayload(actionPayload);
		
		try {
			return pollService.persistPollVote(pollVote);
		}
		catch(RestClientException e) {
			return Message.builder()
				.attachments(Collections.singletonList(
					Attachment.builder()
						.color("#ff0000")
						.text("An unexpected error occured: " + e.getMessage())
						.build()
				)).build();
		}
	}
	
	@ResponseStatus(BAD_REQUEST)
	private class InvalidTokenException extends RuntimeException {}
}
