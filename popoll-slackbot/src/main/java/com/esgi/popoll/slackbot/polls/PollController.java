package com.esgi.popoll.slackbot.polls;

import com.esgi.popoll.slackbot.config.TriggerAdapter;
import io.fries.slack.webhook.WebHook;
import io.fries.slack.webhook.message.Message;
import io.fries.slack.webhook.trigger.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
class PollController {

	private static final Logger logger = LoggerFactory.getLogger(PollController.class);
	
	private final PollService pollService;
	private final WebHook slackWebHook;
	private final TriggerAdapter triggerAdapter;
	
	public PollController(final PollService pollService, final WebHook slackWebHook, final TriggerAdapter triggerAdapter) {
		this.pollService = pollService;
		this.slackWebHook = slackWebHook;
		this.triggerAdapter = triggerAdapter;
	}
	
	@PostMapping(path = "/")
	public Message appRoot(@RequestBody String urlencodedData) {
		final Trigger trigger = triggerAdapter.toTrigger(urlencodedData);
		logger.info("POST / " + trigger);
		
		return Message.builder().mrkdwn(true).text("Greetings from the **Popoll** API!").build();
	}
	
	@PostMapping(path = "/polls")
	public Trigger createNewPoll(@RequestBody String urlencodedData) {
		final Trigger trigger = triggerAdapter.toTrigger(urlencodedData);
		logger.info("POST /polls " + trigger);

		if(!pollService.validateTriggerToken(trigger))
			throw new InvalidTokenException();
		
		final Poll poll = pollService.createPollFromTrigger(trigger);
		
		return trigger;
	}
	
	@ResponseStatus(BAD_REQUEST)
	private class InvalidTokenException extends RuntimeException {}
}
