package com.esgi.popoll.survey.entity.answer;

import com.esgi.popoll.survey.entity.survey.SurveyAdapter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Component
public class AnswerAdapter {

	private SurveyAdapter surveyAdapter;

	public AnswerDto toAnswerDto(final Answer answer) {
		return answer != null
			? AnswerDto.builder()
			.id(answer.getId())
			.answer(answer.getAnswer())
			.surveyId(surveyAdapter.toSurveyDto(answer.getSurveyId()))
			.build()
		: null;
	}

	public Answer toAnswer(final AnswerDto answerDto) {
		return answerDto != null
			? Answer.builder()
			.id(answerDto.getId())
			.answer(answerDto.getAnswer())
			.surveyId(surveyAdapter.toSurvey(answerDto.getSurveyId()))
			.build()
		: null;
	}
}
