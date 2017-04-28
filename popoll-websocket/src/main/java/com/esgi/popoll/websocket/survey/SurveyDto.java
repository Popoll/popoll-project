package com.esgi.popoll.websocket.survey;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Data
public class SurveyDto {
	
	private Long id;
	
	@NotNull
	private String question;
	
	@NotNull
	private String author;
	
	@NotNull
	private String channel;
	
	@NotNull
	private List<String> answers;
	
	private List<Integer> votes;
}
