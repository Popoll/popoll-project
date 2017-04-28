package com.esgi.popoll.slackbot.polls;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
class Poll implements Serializable {
	private String id;
	private String question;
	private List<PollAnswer> answers;
	private String author;
	private String channel;
}
