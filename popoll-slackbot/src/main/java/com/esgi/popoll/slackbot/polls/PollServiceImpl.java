package com.esgi.popoll.slackbot.polls;

import io.fries.slack.webhook.trigger.Trigger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
}
