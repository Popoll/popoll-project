package com.esgi.popoll.survey.entity.survey;

import com.esgi.popoll.survey.entity.answer.AnswerAdapter;
import com.esgi.popoll.survey.entity.vote.VoteAdapter;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

public class SurveyAdapter {

    public static SurveyDto toSurveyDto(final Survey survey) {
        return survey != null
            ? SurveyDto.builder()
            .id(survey.getId())
            .votes(survey.getVotes().stream().map(VoteAdapter::toVoteDto).collect(toList()))
            .question(survey.getQuestion())
            .answers(survey.getAnswers().stream().map(AnswerAdapter::toAnswerDto).collect(toList()))
            .author(survey.getAuthor())
            .channel(survey.getChannel()).build()
        : null;
    }

    public static Survey toSurvey(final SurveyDto surveyDto) {
        return surveyDto != null
            ? Survey.builder()
            .id(surveyDto.getId())
            .votes(new ArrayList())
            .question(surveyDto.getQuestion())
            .answers(surveyDto.getAnswers().stream().map(AnswerAdapter::toAnswer).collect(toList()))
            .author(surveyDto.getAuthor())
            .channel(surveyDto.getChannel()).build()
        : null;
    }
}
