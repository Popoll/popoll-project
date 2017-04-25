package com.esgi.popoll.survey.entity.answer;

import com.esgi.popoll.survey.entity.survey.SurveyAdapter;

public class AnswerAdapter {

	public static AnswerDto toAnswerDto(final Answer answer) {
		return answer != null
			? AnswerDto.builder()
			.id(answer.getId())
			.answer(answer.getAnswer())
			.surveyId(SurveyAdapter.toSurveyDto(answer.getSurveyId()))
			.build()
		: null;
	}

	public static Answer toAnswer(final AnswerDto answerDto) {
		return answerDto != null
			? Answer.builder()
			.id(answerDto.getId())
			.answer(answerDto.getAnswer())
			.surveyId(SurveyAdapter.toSurvey(answerDto.getSurveyId()))
			.build()
		: null;
	}
}
