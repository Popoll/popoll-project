package com.esgi.popoll.slackbot.polls;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
class PollVote {
	private String surveyId;
	private String userId;
	private String answer;
}
