package com.esgi.popoll.slackbot.polls;

import io.fries.slack.webhook.message.Action;
import io.fries.slack.webhook.message.Attachment;
import io.fries.slack.webhook.message.Message;
import io.fries.slack.webhook.trigger.Trigger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
class PollServiceImpl implements PollService {
	
	@Value("${info.slack.verification-token}")
	private String slackVerificationToken;
	
	@Override
	public Boolean validateTriggerToken(final Trigger trigger) {
		return trigger.getToken().equals(slackVerificationToken);
	}
	
	@Override
	public Poll createPollFromTrigger(final Trigger trigger) {
		if(trigger == null)
			throw new IllegalArgumentException("trigger cannot be null");
		
		final Integer QUESTION_PARAM_INDEX = 0;
		
		final String triggerText = trigger.getText();
		final String[] triggerParameters = triggerText.substring(1, triggerText.length() - 1).split("\" \"");
		
		final Poll.PollBuilder poll = Poll.builder()
			.question(triggerParameters[QUESTION_PARAM_INDEX])
			.author(trigger.getUserName());
		
		final List<String> answers = new ArrayList<>();
		answers.addAll(Arrays.asList(triggerParameters).subList(1, triggerParameters.length));
		poll.answers(answers);
		
		return poll.build();
	}
	
	@Override
	public Message createMessageFromPoll(Poll poll) {
		final String ACTION_TYPE = "button";
		
		if(poll == null)
			throw new IllegalArgumentException("poll cannot be null");
		
		return Message.builder()
			.attachments(Collections.singletonList(
				Attachment.builder()
					.callbackId("static_callback_id") // FIXME: use the persisted poll ID as callback ID
					.color("#3AA3E3")
					.fallback(poll.getQuestion())
					.title(poll.getQuestion())
					.authorName(poll.getAuthor())
					.actions(
						poll.getAnswers().stream()
							.map(answer -> Action.builder()
								.name(answer)
								.text(answer)
								.value(answer)
								.type(ACTION_TYPE)
								.build()
							).collect(Collectors.toList())
					).build()
			)).build();
	}
}
