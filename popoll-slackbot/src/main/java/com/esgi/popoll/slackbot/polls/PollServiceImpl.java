package com.esgi.popoll.slackbot.polls;

import io.fries.slack.webhook.message.Action;
import io.fries.slack.webhook.message.Attachment;
import io.fries.slack.webhook.message.Message;
import io.fries.slack.webhook.trigger.ActionPayload;
import io.fries.slack.webhook.trigger.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RefreshScope
class PollServiceImpl implements PollService {

	private final RestTemplate restTemplate;

	@Value("${info.slack.verification-token}")
	private String slackVerificationToken;
	@Value("${info.services.survey}")
	private String surveyService;

	@Autowired
	public PollServiceImpl(final RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
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
			.author(trigger.getUserName())
			.channel(trigger.getChannelName());
		
		final List<String> answers = new ArrayList<>();
		answers.addAll(Arrays.asList(triggerParameters).subList(1, triggerParameters.length));
		poll.answers(answers);
		
		return poll.build();
	}

	@Override
	public Poll persistPoll(final Poll poll) {
		if(surveyService.isEmpty())
			throw new IllegalStateException("Survey service is unreachable");
		else if(poll == null)
			throw new IllegalArgumentException("poll cannot be null");

		final String surveyUrl = surveyService + "/surveys";
		final Poll persistedPoll = restTemplate.postForObject(surveyUrl, poll, Poll.class);

		return persistedPoll;
	}
	
	@Override
	public Message createMessageFromPoll(final Poll poll) {
		final String ACTION_TYPE = "button";
		final String ATTACHMENT_COLOR = "#3AA3E3";
		
		if(poll == null)
			throw new IllegalArgumentException("poll cannot be null");
		
		return Message.builder()
			.attachments(Collections.singletonList(
				Attachment.builder()
					.callbackId(poll.getId())
					.color(ATTACHMENT_COLOR)
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

	@Override
	public PollVote createPollVoteFromActionPayload(final ActionPayload actionPayload) {
		if(actionPayload == null)
			throw new IllegalArgumentException("actionPayload cannot be null");
		else if(actionPayload.getActions() == null)
			throw new IllegalArgumentException("actionPayload actions is null");
		else if(actionPayload.getActions().isEmpty())
			throw new IllegalArgumentException("actionPayload actions cannot be empty");

		return PollVote.builder()
			.surveyId(actionPayload.getCallbackId())
			.userId(actionPayload.getUser().getId())
			.answer(actionPayload.getActions().get(0).getValue())
			.build();
	}
	
	@Override
	public Boolean validateActionPayloadToken(final ActionPayload actionPayload) {
		return actionPayload.getToken().equals(slackVerificationToken);
	}

	@Override
	public Message persistPollVote(final PollVote pollVote) {
		if(surveyService.isEmpty())
			throw new IllegalStateException("Survey service is unreachable");
		else if(pollVote == null)
			throw new IllegalArgumentException("pollVote cannot be null");
		else if(pollVote.getSurveyId() == null || pollVote.getSurveyId().isEmpty())
			throw new IllegalArgumentException("Survey ID cannot be null or empty");

		final String surveyUrl = surveyService + "/surveys/" + pollVote.getSurveyId() + "/vote";
		final String resultUrl = restTemplate.postForObject(surveyUrl, pollVote, String.class);

		return Message.builder().text(resultUrl).build();
	}
}
