package com.esgi.popoll.slackbot.polls;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
class Poll implements Serializable {
	private final String question;
	private final List<String> answers;
	private final String author;
}
