package com.esgi.popoll.slackbot.polls;

import io.fries.slack.webhook.trigger.Trigger;

interface PollService {
	Boolean validateTriggerToken(final Trigger trigger);
	Poll createPollFromTrigger(final Trigger trigger);
}
