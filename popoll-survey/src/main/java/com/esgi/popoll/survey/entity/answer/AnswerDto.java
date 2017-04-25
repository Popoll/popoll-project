package com.esgi.popoll.survey.entity.answer;

import com.esgi.popoll.survey.entity.survey.SurveyDto;
import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {

	private Long id;

	@NotNull
	private String answer;

	@NotNull
	private SurveyDto surveyId;
}
