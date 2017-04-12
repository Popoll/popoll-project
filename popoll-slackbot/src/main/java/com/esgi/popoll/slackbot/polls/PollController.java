package com.esgi.popoll.slackbot.polls;

import com.esgi.popoll.slackbot.config.ActionPayloadAdapter;
import com.esgi.popoll.slackbot.config.TriggerAdapter;
import io.fries.slack.webhook.WebHook;
import io.fries.slack.webhook.message.Message;
import io.fries.slack.webhook.trigger.ActionPayload;
import io.fries.slack.webhook.trigger.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static io.fries.slack.webhook.message.Message.ResponseType.EPHEMERAL;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
class PollController {

	private static final Logger logger = LoggerFactory.getLogger(PollController.class);
	
	private final PollService pollService;
	private final WebHook slackWebHook;
	private final TriggerAdapter triggerAdapter;
	private final ActionPayloadAdapter actionPayloadAdapter;
	
	public PollController(
		final PollService pollService, final WebHook slackWebHook,
		final TriggerAdapter triggerAdapter, final ActionPayloadAdapter actionPayloadAdapter
	) {
		this.pollService = pollService;
		this.slackWebHook = slackWebHook;
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
		final Poll persistedPoll = pollService.persistPoll(poll);
		final Message message = pollService.createMessageFromPoll(persistedPoll);
		
		return message;
	}
	
	@PostMapping(path = "/polls/vote")
	public Message handlePollVote(@RequestBody final String urlencodedData) {
		final ActionPayload actionPayload = actionPayloadAdapter.toActionPayload(urlencodedData);
		logger.info("POST /polls/vote " + actionPayload);
		
		return Message.builder().responseType(EPHEMERAL).text("OK").build();
	}
	
	@ResponseStatus(BAD_REQUEST)
	private class InvalidTokenException extends RuntimeException {}
}
