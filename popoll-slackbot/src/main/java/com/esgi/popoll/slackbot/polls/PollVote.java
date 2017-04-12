package com.esgi.popoll.slackbot.polls;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
class PollVote {
	private final String surveyId;
	private final String userId;
	private final String answer;
}
