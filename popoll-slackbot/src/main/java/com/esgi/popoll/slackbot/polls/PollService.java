package com.esgi.popoll.slackbot.polls;

import io.fries.slack.webhook.message.Message;
import io.fries.slack.webhook.trigger.ActionPayload;
import io.fries.slack.webhook.trigger.Trigger;

interface PollService {
	Boolean validateTriggerToken(final Trigger trigger);
	Poll createPollFromTrigger(final Trigger trigger);
	Poll persistPoll(final Poll poll);
	Message createMessageFromPoll(final Poll poll);

	PollVote createPollVoteFromActionPayload(final ActionPayload actionPayload);
	Message persistPollVote(final PollVote pollVote);
}
