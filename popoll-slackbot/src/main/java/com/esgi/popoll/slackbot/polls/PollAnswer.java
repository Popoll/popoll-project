package com.esgi.popoll.slackbot.polls;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
class PollAnswer implements Serializable {
	public String answer;
}
